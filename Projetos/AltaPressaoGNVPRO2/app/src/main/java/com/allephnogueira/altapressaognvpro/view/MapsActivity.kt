package com.allephnogueira.altapressaognvpro.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.allephnogueira.altapressaognvpro.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.allephnogueira.altapressaognvpro.databinding.ActivityMapsBinding
import com.allephnogueira.altapressaognvpro.model.CapturarUsuarioLogado
import com.allephnogueira.altapressaognvpro.model.Localizacoes
import com.allephnogueira.altapressaognvpro.model.Usuario
import com.allephnogueira.altapressaognvpro.viewmodel.ExibirMensagem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    // Inicialização das variáveis
    private val binding by lazy { ActivityMapsBinding.inflate(layoutInflater) }
    private val autenticador by lazy { FirebaseAuth.getInstance() }
    private lateinit var mapa: GoogleMap
    private lateinit var clienteLocalizacao: FusedLocationProviderClient
    private val CODIGO_SOLICITACAO_PERMISSAO_LOCALIZACAO = 1
    private lateinit var usuario: Usuario
    private lateinit var localizacoes: Localizacoes
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

        clienteLocalizacao = LocationServices.getFusedLocationProviderClient(this)

        with(binding) {
            layoutLateral.visibility = View.GONE // Definir como invisível

            btnSair.setOnClickListener {
                autenticador.signOut()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }

            btnInflar.setOnClickListener {
                // Exibir os botões só quando o usuário clicar neles
                // Se estiver visível, esconde o botão; se estiver invisível, ativa o botão.
                if (layoutLateral.visibility == View.VISIBLE) {
                    layoutLateral.visibility = View.GONE
                } else {
                    layoutLateral.visibility = View.VISIBLE
                }
            }

            btnCalculoCombustivel.setOnClickListener {
                startActivity(Intent(applicationContext, CalculoCombustivelActivity::class.java))
            }

            ftAdicionarPosto.setOnClickListener {

                if (verificarPermissaoLocalizacao()) {
                    try {
                        clienteLocalizacao.lastLocation.addOnSuccessListener { localizacao: Location? ->
                            localizacao?.let {
                                val latitudeUsuario = localizacao.latitude
                                Log.i("LatitudeUsuario", "Latitude: $latitudeUsuario")

                                // Usar a latitude
                                val numeroDocumento = latitudeUsuario.toString().substring(0, 5)
                                Log.i("NomeDocumento", "Nome do Documento: $numeroDocumento")


                                /* Pegar o ID do usuario logado */

                                val idUsuario = autenticador.currentUser?.uid

                                /* Pegar dados do usuario se o ID nao for nulo */

                                if (idUsuario != null) {
                                    lifecycleScope.launch {
                                        val usuario = CapturarUsuarioLogado.capturarUsuarioLogado(idUsuario)

                                        if (usuario != null) {
                                            Log.i("UsuarioLogado", "Usuário capturado: ${usuario.nome}")
                                            usuario.email = autenticador.currentUser?.email // Capturando email do usuario

                                            // Capturando os dados quando o usuario clicar no botao
                                            // Aqui depois alguns dados vamos inserir aqui.
                                            val localizacoes = Localizacoes(numeroDocumento,
                                                localizacao.latitude.toString(),
                                                localizacao.longitude.toString(),
                                                "BR")

                                            // Inflar o layout e pegar os dados.

                                            val container = binding.container
                                            val novoLayout = LayoutInflater.from(applicationContext).inflate(R.layout.adiconar_postos, container, false)
                                            container.addView(novoLayout)

                                            // Agora salvando no banco esses dados.

                                            salvarDadosNoBanco(numeroDocumento, localizacoes, usuario, "postoGNV")


                                        } else {
                                            Log.e("UsuarioLogado", "Erro ao capturar o usuário ou usuário não encontrado.")
                                        }
                                    }
                                }
                            } ?: run {
                                Log.i("ErroLocalizacao", "Não foi possível obter a localização.")
                            }
                        }
                    } catch (e: SecurityException) {
                        Log.i("ErroPermissao", "Exceção de segurança ao acessar localização: ${e.message}")
                    }
                } else {
                    Log.i("Permissao", "Permissão de localização não concedida.")
                }
            }


        }

    }

    private fun salvarDadosNoBanco(iddoLocal: String, local: Localizacoes, usuario: Usuario, tipoDeServico: String) {

        val colunas = mapOf(
            "latitude" to local.latitude,
            "longetude" to local.longitude,
            "nomePosto" to local.nomePosto,
            "nomeUsuario" to usuario.nome

        )
        // Criamos uma coleçao com o nome do serviço postosGNV
        // Pegamos o ID do local onde o usuario esta
        // Agora criamos uma outra coleçao para ele guarda os dados do posto.
        // COM ID ALEATORIO;

        bancoDeDados
            .collection(tipoDeServico)
            .document(iddoLocal)
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
