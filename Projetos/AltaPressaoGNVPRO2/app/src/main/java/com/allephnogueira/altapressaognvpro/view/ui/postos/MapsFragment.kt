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
import com.allephnogueira.altapressaognvpro.databinding.AdiconarPostosBinding
import com.allephnogueira.altapressaognvpro.databinding.FragmentMapsBinding
import com.allephnogueira.altapressaognvpro.model.Local
import com.allephnogueira.altapressaognvpro.model.Usuario
import com.allephnogueira.altapressaognvpro.utils.LocationPermissionHelper
import com.allephnogueira.altapressaognvpro.utils.LocationService
import com.allephnogueira.altapressaognvpro.utils.MapHelper
import com.allephnogueira.altapressaognvpro.viewmodel.Temas
import com.google.android.gms.maps.SupportMapFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Locale

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
            Toast.makeText(requireContext(), "Mapa carregado com sucesso", Toast.LENGTH_SHORT)
                .show()
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
                    // Atualizar sempre a localização quando o usuario clicar no botao
                    atualizarLocalizacao()

                    inflarLayoutParaEscolherBandeiraDoPosto()

                    binding.ftAdicionarPosto.hide()

                }
            }

        }


    }

    private fun atualizarLocalizacao() {
        locationService.getUltimaLocalizacao { novaLocalizacao ->
            novaLocalizacao?.let {
                local.latitude = it.latitude.toString()
                local.longitude = it.longitude.toString()
            }
        }
    }


    private fun carregarPostosNoMapa() {
        val numeroDoDocumento = local.latitude.toString().substring(0, 5)
        Log.i("MAPA", "carregarPostosNoMapa: numeroDoDocumento $numeroDoDocumento ")

        // Cancelar listener anterior se existir (bom para evitar múltiplos listeners)
        listenerRegistration?.remove()

        listenerRegistration = bancoDeDados.collection(Constantes.COLECAO_SERVICO_POSTOGNV)
            .document(numeroDoDocumento)
            .collection("locais")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Toast.makeText(
                        requireContext(),
                        "Erro ao carregar postos: ${error.message}",
                        Toast.LENGTH_LONG
                    ).show()
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
                        val mediaDasAvaliacoes =
                            localDoc.getLong(Constantes.MEDIA_DAS_AVALIACOES)?.toString() ?: "0"
                        val somaTotalDasAvaliacoes =
                            localDoc.getLong(Constantes.SOMA_TOTAL_DAS_AVALIACOES)?.toString()
                                ?: "0"
                        val quantidadeDeAvaliadores =
                            localDoc.getLong(Constantes.QUANTIDADE_DE_AVALIADORES)?.toString()
                                ?: "0"


                        if (latitude != null && longitude != null && nomePosto != null) {
                            val localMarcador = Local()
                            localMarcador.latitude = latitude.toString()
                            localMarcador.longitude = longitude.toString()
                            localMarcador.nomePosto = nomePosto
                            localMarcador.mediaDasAvaliacoes = mediaDasAvaliacoes
                            localMarcador.somaTotalDasAvaliacoes = somaTotalDasAvaliacoes
                            localMarcador.quantidadeDeAvaliadores = quantidadeDeAvaliadores

                            mapHelper.adicionarMarcador(
                                requireContext(),
                                latitude,
                                longitude,
                                nomePosto,
                                localMarcador
                            )
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

    @Deprecated("Deprecated in Java")
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
            exibirFloatBotao()
        }

        containerSelecionarBandeiraDoPosto.btnSalvar.setOnClickListener {

            var bandeiraSelecionada = when {
                containerSelecionarBandeiraDoPosto.rbBR.isChecked -> "BR"
                containerSelecionarBandeiraDoPosto.rbShell.isChecked -> "Shell"
                containerSelecionarBandeiraDoPosto.rbIpiranga.isChecked -> "Ipiranga"
                containerSelecionarBandeiraDoPosto.rbOutros.isChecked -> "Outros"
                else -> ""
            }

            val checkboxMarcados = listOf(
                containerSelecionarBandeiraDoPosto.cbGNV,
                containerSelecionarBandeiraDoPosto.cbGasolina,
                containerSelecionarBandeiraDoPosto.cbEletrico,
                containerSelecionarBandeiraDoPosto.cbCalibrador,
                containerSelecionarBandeiraDoPosto.cbMecanico,
                containerSelecionarBandeiraDoPosto.cbDucha
            )

            val quantidadeDeCheckboxSelecionado = checkboxMarcados.count { it.isChecked }
            val checkBoxQueOUsuarioSelecionouFoi = checkboxMarcados.filter { it.isChecked }.map {
                it.text.toString().lowercase(
                    Locale.ROOT
                )
            }

            Log.i("Checkbox_Selecionados", "O usuario selecionou o $checkBoxQueOUsuarioSelecionouFoi ")
            Log.i("Checkbox_Selecionados", "Quantidade de servicos selecionados $quantidadeDeCheckboxSelecionado ")
            /* Explicação do codigo:
            Quanto o usuario seleciona uma bandeira de posto ele é obrigado a selecionar um tipo de serviço:
                GNV, GASOLINA, ELETRICO, CALIBRADOR
            Quando seleciona Mecanico ou Ducha, nao precisa selecionar a bandeira do posto.
             */
            if (
                (bandeiraSelecionada.isNotEmpty() && quantidadeDeCheckboxSelecionado >= 1 || bandeiraSelecionada.isEmpty() && (checkBoxQueOUsuarioSelecionouFoi.contains("mecanico") || checkBoxQueOUsuarioSelecionouFoi.contains("ducha"))  )
                && usuario.id.isNotEmpty() && local.latitude != null
            ) {
                local.nomePosto = bandeiraSelecionada

                /*
                Agora aqui vamos capturar o tipo de serviço que o usuario selecionou e vamos salvar no banco de dados
                 */
                CoroutineScope(Dispatchers.IO).launch {
                    for (i in 0 until quantidadeDeCheckboxSelecionado) {
                        Log.i("Checkbox_Selecionados", "Salvando ${checkBoxQueOUsuarioSelecionouFoi[i]}")
                        salvarPostoNoBancoDeDados(checkBoxQueOUsuarioSelecionouFoi[i], usuario, local)
                    }

                }

                exibirFloatBotao()

            } else {
                Toast.makeText(
                    requireContext(),
                    "Você deve selecionar a bandeira e/ou tipo de serviço",
                    Toast.LENGTH_LONG
                ).show()
            }


        }


    }

    private fun exibirFloatBotao() {
        binding.ftAdicionarPosto.show()
    }

    private suspend fun salvarPostoNoBancoDeDados(tipodeServico: String ,usuario: Usuario, local: Local) {
        val idDoLocal = local.latitude!!.substring(0, 5)

        val colunas = mapOf(
            Constantes.LATITUDE to local.latitude,
            Constantes.LONGITUDE to local.longitude,
            Constantes.NOME_DO_POSTO to local.nomePosto,
            Constantes.NOME_USUARIO to usuario.nome,
            Constantes.MEDIA_DAS_AVALIACOES to 0.0,
            Constantes.SOMA_TOTAL_DAS_AVALIACOES to 0,
            Constantes.QUANTIDADE_DE_AVALIADORES to 0,

            "data" to FieldValue.serverTimestamp()

        )

        bancoDeDados.collection(tipodeServico).document(idDoLocal)
            .collection("locais").add(colunas).addOnSuccessListener {
                Toast.makeText(requireContext(), "Obrigado.", Toast.LENGTH_SHORT).show()


                // Fechar o container quando o usuario salvar
                val container = binding.containerSelecionarBandeira

                container.removeAllViews() // Fechar
            }
    }
}
