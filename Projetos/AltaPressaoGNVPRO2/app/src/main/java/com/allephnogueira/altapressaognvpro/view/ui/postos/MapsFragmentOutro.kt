//package com.allephnogueira.altapressaognvpro.view.ui.postos
//
//import android.Manifest
//import android.app.Activity
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.location.Location
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import com.allephnogueira.altapressaognvpro.R
//import com.allephnogueira.altapressaognvpro.constantes.Constantes
//import com.allephnogueira.altapressaognvpro.databinding.AdiconarPostosBinding
//import com.allephnogueira.altapressaognvpro.databinding.FragmentMapsBinding
//import com.allephnogueira.altapressaognvpro.model.Local
//import com.allephnogueira.altapressaognvpro.model.Usuario
//import com.allephnogueira.altapressaognvpro.view.LoginActivity
//import com.allephnogueira.altapressaognvpro.viewmodel.Temas
//import com.allephnogueira.altapressaognvpro.viewmodel.ExibirMensagem
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.LatLng
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FieldValue
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.tasks.await
//import kotlinx.coroutines.withContext
//
//class MapsFragmentOutro : Fragment(), OnMapReadyCallback {
//
//    // Inicialização das variáveis
//    private lateinit var binding: FragmentMapsBinding
//    private val autenticador by lazy { FirebaseAuth.getInstance() }
//    private lateinit var mapa: GoogleMap
//    private lateinit var clienteLocalizacao: FusedLocationProviderClient
//    private lateinit var latitudeELongitudeAtual : LatLng
//    private val CODIGO_SOLICITACAO_PERMISSAO_LOCALIZACAO = 1
//    private var usuario: Usuario? = null
//    private val bancoDeDados by lazy { FirebaseFirestore.getInstance() }
//    private val exibirMensagem: (String) -> Unit by lazy {
//        { mensagem: String ->
//            ExibirMensagem.exibirMensagem(requireContext(), mensagem)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentMapsBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        // Obter o SupportMapFragment e notificar quando o mapa estiver pronto para uso.
//        val fragmentoMapa = childFragmentManager
//            .findFragmentById(R.id.map) as SupportMapFragment
//        fragmentoMapa.getMapAsync(this)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            usuario = recuperarDadosDoUsuarioLogado(recuperarIdUsuario())
//        }
//
//        clienteLocalizacao = LocationServices.getFusedLocationProviderClient(requireContext())
//
//        eventosDeClique()
//
//
//    }
//
//    private fun eventosDeClique() {
//        with(binding) {
//
////            btnSair.setOnClickListener {
////                deslogarUsuario()
////            }
//
//
//            ftAdicionarPosto.setOnClickListener {
//                if (verificarPermissaoLocalizacao()) {
//                    try {
//                        clienteLocalizacao.lastLocation.addOnSuccessListener { localizacao: Location? ->
//                            localizacao?.let {
//                                val latitudeDoUsuario = localizacao.latitude
//                                val numeroDoDocumento = latitudeDoUsuario.toString().substring(0, 5)
//                                val idUsuarioLogado = autenticador.currentUser?.uid
//
//                                if (idUsuarioLogado != null) {
//                                    // RECUPERAR DADOS DO USUÁRIO
//                                    CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
//                                        // Recuperar os dados do usuário
//                                        val usuarioRecuperado =
//                                            recuperarDadosDoUsuarioLogado(idUsuarioLogado)
//
//
//                                        if (usuarioRecuperado != null) {
//                                            usuario = usuarioRecuperado
//
//                                            withContext(kotlinx.coroutines.Dispatchers.Main) {
//                                                inflarLayoutParaPerguntarQualBandeira(
//                                                    numeroDoDocumento,
//                                                    localizacao
//                                                )
//                                            }
//                                        } else {
//                                            android.util.Log.e(
//                                                "RecuperarDados",
//                                                "Não foi possível recuperar os dados do usuário."
//                                            )
//                                        }
//                                    }
//                                }
//                            } ?: run {
//                                android.util.Log.i(
//                                    "ErroLocalizacao",
//                                    "Não foi possível obter a localização."
//                                )
//                            }
//                        }
//                    } catch (e: SecurityException) {
//                        android.util.Log.i(
//                            "ErroPermissao",
//                            "Exceção de segurança ao acessar localização: ${e.message}"
//                        )
//                    }
//                } else {
//                    android.util.Log.i("Permissao", "Permissão de localização não concedida.")
//                }
//            }
//
//        }
//    }
//
//
//    private fun inflarLayoutParaPerguntarQualBandeira(
//        numeroDoDocumento: String,
//        localizacao: Location
//    ) {
//        val container = binding.container
//        val bindingNovoLayout = AdiconarPostosBinding.inflate(layoutInflater, container, false)
//
//
//
//        bindingNovoLayout.btnSalvar.setOnClickListener {
//
//            // Capturando o texto do RadioGroup
//
//            var bandeiraDoPosto = ""
//
//            with(bindingNovoLayout) {
//                bandeiraDoPosto = when {
//                    rbBR.isChecked -> "BR"
//                    rbShell.isChecked -> "Shell"
//                    rbIpiranga.isChecked -> "Ipiranga"
//                    else -> "Outros"
//                }
//            }
//
//
//            if (bandeiraDoPosto.isNotEmpty() && usuario != null) {
//
//                val novaLocalizacao = Local().apply {
//                    idDoLocal = numeroDoDocumento
//                    latitude = localizacao.latitude.toString()
//                    longitude = localizacao.longitude.toString()
//                    nomePosto = bandeiraDoPosto
//                }
//
//                salvarDadosNoBanco(
//                    numeroDoDocumento,
//                    novaLocalizacao,
//                    usuario!!,
//                    Constantes.COLECAO_SERVICO_POSTOGNV
//                )
//
//
//            } else {
//                exibirMensagem("Selecione a bandeira do posto")
//            }
//
//            container.removeView(bindingNovoLayout.root)  // Remove o layout da view
//        }
//
//        bindingNovoLayout.btnFechar.setOnClickListener {
//            container.removeView(bindingNovoLayout.root)  // Remove o layout da view
//        }
//
//        container.addView(bindingNovoLayout.root)
//    }
//
//
//    suspend fun recuperarDadosDoUsuarioLogado(idUsuarioLogado: String): Usuario? {
//        return try {
//            val referenciaUsuario = bancoDeDados
//                .collection(Constantes.COLECAO_USUARIOS)
//                .document(idUsuarioLogado)
//
//            val documentSnapshot = referenciaUsuario.get().await() // Usando coroutines
//            documentSnapshot.toObject(Usuario::class.java)
//        } catch (e: Exception) {
//            exibirMensagem("Erro ao recuperar usuário: ${e.message}")
//            null
//        }
//    }
//
//
//    private fun deslogarUsuario() {
//        autenticador.signOut()
//        startActivity(Intent(requireContext(), LoginActivity::class.java))
//    }
//
//    private fun salvarDadosNoBanco(
//        idDoLocal: String,
//        local: Local,
//        usuario: Usuario, // CORRIGIR DEPOIS
//        tipoDeServico: String
//    ) {
//
//        val colunas = mapOf(
//            "latitude" to local.latitude,
//            "longitude" to local.longitude,
//            "nomePosto" to local.nomePosto,
//            "nomeUsuario" to usuario.nome,
//            "data" to FieldValue.serverTimestamp()
//
//        )
//        // Criamos uma coleçao com o nome do serviço postosGNV
//        // Pegamos o ID do local onde o usuario esta
//        // Agora criamos uma outra coleçao para ele guarda os dados do posto.
//        // COM ID ALEATORIO;
//
//        bancoDeDados
//            .collection(tipoDeServico)
//            .document(idDoLocal)
//            .collection("locais")
//            .add(colunas)
//            .addOnSuccessListener {
//                exibirMensagem("Posto salvo com sucesso!")
//            }
//            .addOnFailureListener {
//                exibirMensagem("ERRO: Não conseguimos salvar seu posto!")
//            }
//    }
//
//    private fun recuperarIdUsuario(): String {
//        val idUsuario = autenticador.currentUser?.uid
//        if (idUsuario != null) {
//            return idUsuario
//        } else {
//            exibirMensagem("ERRO: Id usuario nao encontrado.")
//            //finish() Encerrar aplicativo
//        }
//        return ""
//    }
//
//    override fun onMapReady(googleMap: GoogleMap) {
//        mapa = googleMap
//
//        // Solicitar permissão para acessar a localização do dispositivo
//        if (ContextCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                requireContext() as Activity,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                CODIGO_SOLICITACAO_PERMISSAO_LOCALIZACAO
//
//            )
//
//
//        } else {
//            // Se a permissão já foi concedida, obter a localização atual
//            mapa.isMyLocationEnabled =
//                true // Pega a localização do aplicativo e tambem ativa o botao de "mira" para ir para seu local.
//            obterLocalizacaoDispositivo()
//        }
//
//
//        exibirMarcadores(latitudeELongitudeAtual)
//
//        //mapa.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//
//        // Adicionar tema
//        Temas.temaEscuro(requireContext(), googleMap)
//    }
//
//    private fun exibirMarcadores(latitudeELongitudeAtual: LatLng) {
//        Log.i("exibirMarcadores", "exibirMarcadores: ${latitudeELongitudeAtual.latitude} ")
//
//    }
//
//
//    // Método para obter a localização do dispositivo
//    private fun obterLocalizacaoDispositivo() {
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        clienteLocalizacao.lastLocation.addOnSuccessListener { localizacao: Location? ->
//            localizacao?.let {
//                latitudeELongitudeAtual = LatLng(localizacao.latitude, localizacao.longitude)
//                mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudeELongitudeAtual, 15f))
//            }
//        }
//    }
//
//
//    // Metodo para verificar se o usuario deu a permissao de localização
//    private fun verificarPermissaoLocalizacao(): Boolean {
//        return ActivityCompat.checkSelfPermission(
//            requireContext(),
//            Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED
//    }
//
//
//    // Método para lidar com a resposta da solicitação de permissões
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == CODIGO_SOLICITACAO_PERMISSAO_LOCALIZACAO && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.checkSelfPermission(
//                    requireContext(),
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                    requireContext(),
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return
//            }
//            mapa.isMyLocationEnabled = true
//            obterLocalizacaoDispositivo()
//        }
//    }
//}
//
