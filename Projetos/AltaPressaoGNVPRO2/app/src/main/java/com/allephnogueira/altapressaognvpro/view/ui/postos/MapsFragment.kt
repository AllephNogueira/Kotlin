package com.allephnogueira.altapressaognvpro.view.ui.postos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.allephnogueira.altapressaognvpro.R
import com.allephnogueira.altapressaognvpro.constantes.Constantes
import com.allephnogueira.altapressaognvpro.databinding.FragmentMapsBinding
import com.allephnogueira.altapressaognvpro.utils.LocationPermissionHelper
import com.allephnogueira.altapressaognvpro.utils.LocationService
import com.allephnogueira.altapressaognvpro.utils.MapHelper
import com.google.android.gms.maps.SupportMapFragment
import com.allephnogueira.altapressaognvpro.databinding.AdiconarPostosBinding
import com.allephnogueira.altapressaognvpro.model.Local
import com.allephnogueira.altapressaognvpro.model.Usuario
import com.allephnogueira.altapressaognvpro.viewmodel.Temas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MapsFragment : Fragment() {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var locationService: LocationService
    private lateinit var permissionHelper: LocationPermissionHelper
    private lateinit var mapHelper: MapHelper
    private var usuario: Usuario = Usuario()
    private var local: Local = Local()
    private var firebaseAuth = FirebaseAuth.getInstance()
    private var bancoDeDados = FirebaseFirestore.getInstance()
    private var listenerRegistration: ListenerRegistration? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        locationService = LocationService(requireContext())
        permissionHelper = LocationPermissionHelper(this) {
            ativarLocalizacao()
        }

        mapFragment.getMapAsync { map ->
            Toast.makeText(requireContext(), "Mapa carregado com sucesso", Toast.LENGTH_SHORT).show()
            mapHelper = MapHelper(map)
            permissionHelper.verificarOuSolicitarPermissao()
            Temas.temaEscuro(requireContext(), map)
            //carregarPostosNoMapa() // Movido para resolver o problema de carregar o mapa antes da localização


        }

        CoroutineScope(Dispatchers.IO).launch {
            usuario.id = firebaseAuth.currentUser?.uid.toString()
            usuario = recuperarUsuarioLogado(usuario.id)


            withContext(Dispatchers.Main) {
                binding.ftAdicionarPosto.setOnClickListener {
                    inflarLayoutParaEscolherBandeiraDoPosto()
                }
            }

        }


    }



    private fun carregarPostosNoMapa() {
        val numeroDoDocumento = local.latitude.toString().substring(0,5)
        Log.i("MAPA", "carregarPostosNoMapa: numeroDoDocumento $numeroDoDocumento ")

        // Cancelar listener anterior se existir (bom para evitar múltiplos listeners)
        listenerRegistration?.remove()

        listenerRegistration = bancoDeDados.collection(Constantes.COLECAO_SERVICO_POSTOGNV)
            .document(numeroDoDocumento)
            .collection("locais")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Toast.makeText(requireContext(), "Erro ao carregar postos: ${error.message}", Toast.LENGTH_LONG).show()
                    Log.e("MAPA", "Erro ao carregar postos", error)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    // Limpa marcadores antigos antes de adicionar os novos
//                    mapHelper.limparMarcadores()

                    for (localDoc in snapshot.documents) {
                        val latitude = localDoc.getString(Constantes.LATITUDE)?.toDoubleOrNull()
                        val longitude = localDoc.getString(Constantes.LONGITUDE)?.toDoubleOrNull()
                        val nomePosto = localDoc.getString(Constantes.NOME_DO_POSTO)
                        val avaliacao = localDoc.getString(Constantes.QUANTIADE_AVALIACOES) ?: "0"

                        if (latitude != null && longitude != null && nomePosto != null) {
                            val localMarcador = Local()
                            localMarcador.latitude = latitude.toString()
                            localMarcador.longitude = longitude.toString()
                            localMarcador.nomePosto = nomePosto
                            localMarcador.avaliacao = avaliacao

                            mapHelper.adicionarMarcador(requireContext(), latitude, longitude, nomePosto, localMarcador)
                        }
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Remover o listener para evitar vazamentos de memória
        listenerRegistration?.remove()
    }


    private suspend fun recuperarUsuarioLogado(id: String): Usuario {
        return try {
            val referenciaUsuario =
                bancoDeDados.collection(Constantes.COLECAO_USUARIOS).document(id)

            val documentoSnapshot = referenciaUsuario.get().await()
            documentoSnapshot.toObject(Usuario::class.java) ?: Usuario("Desconhecido")
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Erro ao recuperar usuario", Toast.LENGTH_SHORT).show()
            Usuario("Desconhecido")
        }
    }


    private fun ativarLocalizacao() {
        mapHelper.ativarLocalizacao(requireContext())

        locationService.getUltimaLocalizacao { latLng ->
            latLng?.let {
                mapHelper.moverCameraPara(it)

                // SALVANDO A LOCALIZAÇÃO NO OBJETO LOCAL
                local.latitude = it.latitude.toString()
                local.longitude = it.longitude.toString()
                carregarPostosNoMapa()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHelper.tratarResultadoPermissao(requestCode, grantResults)
    }

    private fun inflarLayoutParaEscolherBandeiraDoPosto() {
        val container = binding.containerSelecionarBandeira
        val containerSelecionarBandeiraDoPosto =
            AdiconarPostosBinding.inflate(layoutInflater, container, false)
        container.addView(containerSelecionarBandeiraDoPosto.root)

        containerSelecionarBandeiraDoPosto.btnFechar.setOnClickListener {
            container.removeAllViews() // Fechar
        }

        containerSelecionarBandeiraDoPosto.btnSalvar.setOnClickListener {
            var bandeiraSelecionada = when {
                containerSelecionarBandeiraDoPosto.rbBR.isChecked -> "BR"
                containerSelecionarBandeiraDoPosto.rbShell.isChecked -> "Shell"
                containerSelecionarBandeiraDoPosto.rbIpiranga.isChecked -> "Ipiranga"
                else -> "Outros"
            }

            // CAPTURAR BANDEIRA, USUARIO, LOCALIZAÇÃO
            if (bandeiraSelecionada.isNotEmpty() && usuario.id.isNotEmpty() && local.latitude != null) {
                local.nomePosto = bandeiraSelecionada


                // Se os dados nao estao vazios, vamos salvar no banco de dados.
                CoroutineScope(Dispatchers.IO).launch {
                    salvarPostoNoBancoDeDados(usuario, local)


                }

            }

        }


    }

    private suspend fun salvarPostoNoBancoDeDados(usuario: Usuario, local: Local) {
        val idDoLocal = local.latitude!!.substring(0, 5)

        val colunas = mapOf(
            Constantes.LATITUDE to local.latitude,
            Constantes.LONGITUDE to local.longitude,
            Constantes.NOME_DO_POSTO to local.nomePosto,
            Constantes.NOME_USUARIO to usuario.nome,
            Constantes.QUANTIADE_AVALIACOES to local.avaliacao,
            "data" to FieldValue.serverTimestamp()

        )

        bancoDeDados.collection(Constantes.COLECAO_SERVICO_POSTOGNV).document(idDoLocal)
            .collection("locais").add(colunas).addOnSuccessListener {
                Toast.makeText(requireContext(), "Obrigado.", Toast.LENGTH_SHORT).show()
            }
    }
}


/*private val callback = OnMapReadyCallback { googleMap ->
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera.
//     * In this case, we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to
//     * install it inside the SupportMapFragment. This method will only be triggered once the
//     * user has installed Google Play services and returned to the app.
//     */
//    val sydney = LatLng(-34.0, 151.0)
//    googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//    googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//} */