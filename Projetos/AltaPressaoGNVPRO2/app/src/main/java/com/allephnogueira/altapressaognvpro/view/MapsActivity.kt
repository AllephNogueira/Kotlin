package com.allephnogueira.altapressaognvpro.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.allephnogueira.altapressaognvpro.R
import com.allephnogueira.altapressaognvpro.constantes.Constantes
import com.allephnogueira.altapressaognvpro.databinding.ActivityMapsBinding
import com.allephnogueira.altapressaognvpro.databinding.AdiconarPostosBinding
import com.allephnogueira.altapressaognvpro.databinding.LayoutLateralBinding
import com.allephnogueira.altapressaognvpro.model.DadosDaLocalizacao
import com.allephnogueira.altapressaognvpro.model.Usuario
import com.allephnogueira.altapressaognvpro.viewmodel.ExibirMensagem
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    // Inicialização das variáveis
    private val binding by lazy { ActivityMapsBinding.inflate(layoutInflater) }
    private val autenticador by lazy { FirebaseAuth.getInstance() }
    private lateinit var mapa: GoogleMap
    private lateinit var clienteLocalizacao: FusedLocationProviderClient
    private val CODIGO_SOLICITACAO_PERMISSAO_LOCALIZACAO = 1
    private var usuario: Usuario? = null
    private var dadosDaLocalizacao: DadosDaLocalizacao? = null
    private val bancoDeDados by lazy { FirebaseFirestore.getInstance() }
    private val exibirMensagem: (String) -> Unit by lazy {
        { mensagem: String ->
            ExibirMensagem.exibirMensagem(this, mensagem)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Obter o SupportMapFragment e notificar quando o mapa estiver pronto para uso.
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync(this)

        CoroutineScope(Dispatchers.IO).launch {
            usuario = recuperarDadosDoUsuarioLogado(recuperarIdUsuario())
        }

        clienteLocalizacao = LocationServices.getFusedLocationProviderClient(this)
        eventosDeClique()

    }


    private fun eventosDeClique() {
        with(binding) {

            btnSair.setOnClickListener {
                deslogarUsuario()
            }


            btnInflar.setOnClickListener {
                inflarContainerLateral()

            }

            ftAdicionarPosto.setOnClickListener {
                if (verificarPermissaoLocalizacao()) {
                    try {
                        clienteLocalizacao.lastLocation.addOnSuccessListener { localizacao: Location? ->
                            localizacao?.let {
                                val latitudeDoUsuario = localizacao.latitude
                                val numeroDoDocumento = latitudeDoUsuario.toString().substring(0, 5)
                                val idUsuarioLogado = autenticador.currentUser?.uid

                                if (idUsuarioLogado != null) {
                                    // RECUPERAR DADOS DO USUÁRIO
                                    CoroutineScope(Dispatchers.IO).launch {
                                        // Recuperar os dados do usuário
                                        val usuarioRecuperado =
                                            recuperarDadosDoUsuarioLogado(idUsuarioLogado)


                                        if (usuarioRecuperado != null) {
                                            usuario = usuarioRecuperado

                                            withContext(Dispatchers.Main) {
                                                inflarLayoutParaPerguntarQualBandeira(
                                                    numeroDoDocumento,
                                                    localizacao
                                                )
                                            }
                                        } else {
                                            Log.e(
                                                "RecuperarDados",
                                                "Não foi possível recuperar os dados do usuário."
                                            )
                                        }
                                    }
                                }
                            } ?: run {
                                Log.i("ErroLocalizacao", "Não foi possível obter a localização.")
                            }
                        }
                    } catch (e: SecurityException) {
                        Log.i(
                            "ErroPermissao",
                            "Exceção de segurança ao acessar localização: ${e.message}"
                        )
                    }
                } else {
                    Log.i("Permissao", "Permissão de localização não concedida.")
                }
            }

        }
    }

    private fun inflarContainerLateral() {

        val container = binding.container
        val bindingContainerLateral = LayoutLateralBinding.inflate(layoutInflater, container, false)


        bindingContainerLateral.textNomeUsuario.text = usuario?.nome ?: "Nome não encontrado."

        bindingContainerLateral.btnFecharBarraLateral.setOnClickListener {
            container.removeView(bindingContainerLateral.root)  // Remove o layout da view
        }

        container.addView(bindingContainerLateral.root)
    }

    private fun inflarLayoutParaPerguntarQualBandeira(
        numeroDoDocumento: String,
        localizacao: Location
    ) {
        val container = binding.container
        val bindingNovoLayout = AdiconarPostosBinding.inflate(layoutInflater, container, false)



        bindingNovoLayout.btnSalvar.setOnClickListener {

            // Capturando o texto do RadioGroup

            var bandeiraDoPosto = ""

            with(bindingNovoLayout) {
                bandeiraDoPosto = when {
                    rbBR.isChecked -> "BR"
                    rbShell.isChecked -> "Shell"
                    rbIpiranga.isChecked -> "Ipiranga"
                    else -> "Outros"
                }
            }


            if (bandeiraDoPosto.isNotEmpty() && usuario != null) {

                val novaLocalizacao = DadosDaLocalizacao().apply {
                    idDoLocal = numeroDoDocumento
                    latitude = localizacao.latitude.toString()
                    longitude = localizacao.longitude.toString()
                    nomePosto = bandeiraDoPosto
                }

                salvarDadosNoBanco(
                    numeroDoDocumento,
                    novaLocalizacao,
                    usuario!!,
                    Constantes.COLECAO_SERVICO_POSTOGNV
                )


            } else {
                exibirMensagem("Selecione a bandeira do posto")
            }

            container.removeView(bindingNovoLayout.root)  // Remove o layout da view
        }

        bindingNovoLayout.btnFechar.setOnClickListener {
            container.removeView(bindingNovoLayout.root)  // Remove o layout da view
        }

        container.addView(bindingNovoLayout.root)
    }


    suspend fun recuperarDadosDoUsuarioLogado(idUsuarioLogado: String): Usuario? {
        return try {
            val referenciaUsuario = bancoDeDados
                .collection(Constantes.COLECAO_USUARIOS)
                .document(idUsuarioLogado)

            val documentSnapshot = referenciaUsuario.get().await() // Usando coroutines
            documentSnapshot.toObject(Usuario::class.java)
        } catch (e: Exception) {
            exibirMensagem("Erro ao recuperar usuário: ${e.message}")
            null
        }
    }

    private fun abrirNovaActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }


    private fun deslogarUsuario() {
        autenticador.signOut()
        startActivity(Intent(applicationContext, LoginActivity::class.java))
    }

    private fun salvarDadosNoBanco(
        idDoLocal: String,
        local: DadosDaLocalizacao,
        usuario: Usuario, // CORRIGIR DEPOIS
        tipoDeServico: String
    ) {

        val colunas = mapOf(
            "latitude" to local.latitude,
            "longetude" to local.longitude,
            "nomePosto" to local.nomePosto,
            "nomeUsuario" to usuario.nome,
            "data" to FieldValue.serverTimestamp()

        )
        // Criamos uma coleçao com o nome do serviço postosGNV
        // Pegamos o ID do local onde o usuario esta
        // Agora criamos uma outra coleçao para ele guarda os dados do posto.
        // COM ID ALEATORIO;

        bancoDeDados
            .collection(tipoDeServico)
            .document(idDoLocal)
            .collection("locais")
            .document()
            .set(colunas)
            .addOnSuccessListener {
                exibirMensagem("Posto salvo com sucesso!")
            }
            .addOnFailureListener {
                exibirMensagem("ERRO: Não conseguimos salvar seu posto!")
            }
    }

    private fun recuperarIdUsuario(): String {
        val idUsuario = autenticador.currentUser?.uid
        if (idUsuario != null) {
            return idUsuario
        } else {
            exibirMensagem("ERRO: Id usuario nao encontrado.")
            finish()
        }
        return ""
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapa = googleMap

        // Solicitar permissão para acessar a localização do dispositivo
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                CODIGO_SOLICITACAO_PERMISSAO_LOCALIZACAO
            )
        } else {
            // Se a permissão já foi concedida, obter a localização atual
            mapa.isMyLocationEnabled =
                true // Pega a localização do aplicativo e tambem ativa o botao de "mira" para ir para seu local.
            obterLocalizacaoDispositivo()
        }


        // Exemplo de como adicionar marcador em Sydney e mover a camera pra la
        val sydney = LatLng(-34.0, 151.0)
        mapa.addMarker(MarkerOptions().position(sydney).title("Marcador em Sydney"))
        mapa.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        // Adicionar tema
        Temas.temaEscuro(this, googleMap)
    }

    // Método para obter a localização do dispositivo
    private fun obterLocalizacaoDispositivo() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        clienteLocalizacao.lastLocation.addOnSuccessListener { localizacao: Location? ->
            localizacao?.let {
                val latLngAtual = LatLng(localizacao.latitude, localizacao.longitude)
                mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngAtual, 15f))
            }
        }
    }

// Metodo para verificar se o usuario deu a permissao de localização

    private fun verificarPermissaoLocalizacao(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }


    // Método para lidar com a resposta da solicitação de permissões
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CODIGO_SOLICITACAO_PERMISSAO_LOCALIZACAO && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            mapa.isMyLocationEnabled = true
            obterLocalizacaoDispositivo()
        }
    }
}
