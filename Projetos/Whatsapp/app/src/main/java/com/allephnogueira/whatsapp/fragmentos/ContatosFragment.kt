package com.allephnogueira.whatsapp.fragmentos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.allephnogueira.whatsapp.activities.MensagensActivity
import com.allephnogueira.whatsapp.adapters.ContatosAdapter
import com.allephnogueira.whatsapp.databinding.FragmentContatosBinding
import com.allephnogueira.whatsapp.model.Usuario
import com.allephnogueira.whatsapp.utils.Constantes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class ContatosFragment : Fragment() {

    private lateinit var binding: FragmentContatosBinding
    private lateinit var eventoSnapshot: ListenerRegistration
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val firebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private lateinit var contatosAdapter: ContatosAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContatosBinding.inflate(
            inflater, container, false
        )

        contatosAdapter = ContatosAdapter{ usuario ->
            val intent = Intent(context, MensagensActivity::class.java)
            intent.putExtra("dadosDestinatario", usuario)
            intent.putExtra("origem", Constantes.ORIGEM_CONTATO)
            startActivity( intent )
        }
        binding.rvContatos.adapter = contatosAdapter
        binding.rvContatos.layoutManager = LinearLayoutManager(context)
        binding.rvContatos.addItemDecoration(
            DividerItemDecoration(
                context, LinearLayoutManager.VERTICAL
            )
        )

        return binding.root

    }

    override fun onStart() {
        super.onStart()
        adiconarListenerContatos()
    }

    override fun onDestroy() {
        super.onDestroy()
        /** Esse metodo vai parar de executar essa lista
         * Imagina que se não tivesse esse evento, quando voce saisse da tela, ele iria ficar executando o tempo todo
         * Agora quando voce sair da tela, ele vai remover esse evento.
         *
         * Resumindo: uma vez que você nao esta mais na tela, não faz sentido voce ficar recuperando os dados que estao no servidor.
         */
        eventoSnapshot.remove()
    }


    private fun adiconarListenerContatos() {
        eventoSnapshot = firebaseFirestore.collection("Usuarios")
            .addSnapshotListener { querySnapshot, _ -> // Recuperar a lista de documentos

                val listaContatos = mutableListOf<Usuario>()
                val documentos = querySnapshot?.documents

                // Agora dentro de documento, temos todos os documentos.
                documentos?.forEach { documentSnapshot ->

                    // Dessa forma vamos converter os dados para um objeto Usuario
                    // Imagina se tivessemos que pegar item por item, com chave a valor.
                    // Lembrar que la dentro de usuario, vamos colocar um valor padrao
                    // Porque o firebase se nao encontrar esse valor que esta no servidor, ele não vai saber oque colocar la dentro
                    // Então ja deixamos um valor padrao.
                    val usuario = documentSnapshot.toObject(Usuario::class.java)
                    val idContato = firebaseAuth.currentUser?.uid

                    if (usuario != null && idContato != null) {

                        if (idContato != usuario.id) {
                            // Estamos fazendo isso porque não queremos adicionar nos contatos o nosso proprio usuario
                            // Então vamos adicionar todos, menos você.
                            listaContatos.add(usuario)
                            Log.i(
                                "fragmento_contatos",
                                "nome: ${usuario.nome} - e-mail: ${usuario.email} "
                            )
                        }
                    }
                }
                // Agora ja temos uma lista de contatos.
                // Agora com essa lista de contatos, vamos utilizar para atualizar o RecyclerView
                if (listaContatos.isNotEmpty()) {
                    contatosAdapter.adicionarLista( listaContatos )
                }



            }
    }


}