package com.allephnogueira.whatsapp.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.allephnogueira.whatsapp.R
import com.allephnogueira.whatsapp.adapters.MensagensAdapter
import com.allephnogueira.whatsapp.databinding.ActivityMensagensBinding
import com.allephnogueira.whatsapp.model.Conversa
import com.allephnogueira.whatsapp.model.Mensagem
import com.allephnogueira.whatsapp.model.Usuario
import com.allephnogueira.whatsapp.utils.Constantes
import com.allephnogueira.whatsapp.utils.exibirMensagem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.squareup.picasso.Picasso


class MensagensActivity : AppCompatActivity() {


    private val binding by lazy { ActivityMensagensBinding.inflate(layoutInflater) }


    private var dadosDestinatario: Usuario? = null
    private var dadosUsuarioRemetente: Usuario? = null

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private lateinit var listenerRegistration: ListenerRegistration

    private lateinit var conversasAdapter : MensagensAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ContextCompat.getColor(this, R.color.primaria).also { this.window.statusBarColor = it }

        recuperarDadosUsuarios()
        inicializarToolbar()
        inicializarEventosDeClique()
        inicializarRecyclerView()
        inicializarListeners()

    }

    private fun inicializarRecyclerView() {
        with(binding) {
            conversasAdapter = MensagensAdapter()
            rvMensagens.adapter = conversasAdapter
            rvMensagens.layoutManager = LinearLayoutManager(applicationContext)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listenerRegistration.remove() // Isso serve para quando o usuario sair da tela, ele nao ficar atualizando os dados.
    }

    private fun inicializarListeners() {

        val idUsuarioRemetente =
            firebaseAuth.currentUser?.uid // Usuario que esta logado e mandando as mensagens.
        val idUsuarioDestinatario =
            dadosDestinatario?.id // Usuario que esta recebendo as mensagens.

        if (idUsuarioRemetente != null && idUsuarioDestinatario != null) {
            listenerRegistration = firebaseFirestore
                .collection(Constantes.MENSAGENS)
                .document(idUsuarioRemetente) // Id do usuario que esta enviando
                .collection(idUsuarioDestinatario)  // Pegando também as conversas
                .orderBy("data", Query.Direction.ASCENDING) // Estamos ordenando pela data.
                .addSnapshotListener { querySnapshot, erro ->
                    if (erro != null) {
                        exibirMensagem("Erro ao recuperar mensagem")
                    }

                    val listaDeMensagens = mutableListOf<Mensagem>()
                    val documentos = querySnapshot?.documents

                    documentos?.forEach { documentSnapshot ->

                        val mensagem = documentSnapshot.toObject( Mensagem::class.java )
                        if (mensagem != null){
                            listaDeMensagens.add(mensagem)
                            Log.i("exibicao_mensagens", mensagem.mensagem)
                        }
                    }

                    // Se a lista realmente existe e tiver algo dentro dela
                    // Podemos carregar esses dados dentro de uma recyclerView
                    if (listaDeMensagens.isNotEmpty()) {
                        // Carregar os dados no Adapter
                        conversasAdapter.adicionarLista(listaDeMensagens)
                    }

                }
        }

    }

    private fun inicializarEventosDeClique() {

        binding.fabEnviarMensagem.setOnClickListener {
            val mensagem = binding.editMensagem.text.toString()
            salvarMensagem(mensagem)

        }

    }

    private fun salvarMensagem(textoMensagem: String) {

        if (textoMensagem.isNotEmpty()) {
            val idUsuarioRemetente =
                firebaseAuth.currentUser?.uid // Usuario que esta logado e mandando as mensagens.
            val idUsuarioDestinatario =
                dadosDestinatario?.id // Usuario que esta recebendo as mensagens.

            if (idUsuarioRemetente != null && idUsuarioDestinatario != null) {
                val mensagem = Mensagem(
                    idUsuarioRemetente, textoMensagem
                )

                // Precisamos salvar essa mensagem tanto para o remetende quanto  para o destinatario
                // Salvar para o remetende
                salvarMensagemFirestore(
                    idUsuarioRemetente, idUsuarioDestinatario, mensagem
                )

                val conversaRemetende = Conversa(
                    idUsuarioRemetente, idUsuarioDestinatario,
                    dadosDestinatario!!.foto,  dadosDestinatario!!.nome,
                    textoMensagem
                )

                salvarConversaFirestore( conversaRemetende )

                // Salvar para o destinatario
                salvarMensagemFirestore(
                    idUsuarioDestinatario, idUsuarioRemetente, mensagem
                )

                val conversaDestinatario = Conversa(
                    idUsuarioDestinatario, idUsuarioRemetente,
                    dadosUsuarioRemetente!!.foto, dadosUsuarioRemetente!!.nome,
                    textoMensagem
                )

                salvarConversaFirestore( conversaDestinatario )

                binding.editMensagem.setText("")


            }
        }


    }

    private fun salvarConversaFirestore(conversa: Conversa) {

        firebaseFirestore
            .collection(Constantes.CONVERSAS)
            .document(conversa.idUsuarioRemetente)
            .collection(Constantes.ULTIMAS_CONVERSAS)
            .document(conversa.idUsuarioDestinatario)
            .set(conversa)
            .addOnFailureListener {
                exibirMensagem("Erro ao salvar conversa.")
            }
    }

    private fun salvarMensagemFirestore(
        idUsuarioRemetente: String,
        idUsuarioDestinatario: String,
        mensagem: Mensagem
    ) {
        firebaseFirestore
            .collection(Constantes.MENSAGENS)
            .document(idUsuarioRemetente)
            .collection(idUsuarioDestinatario)
            .add(mensagem)
            .addOnFailureListener {
                exibirMensagem("Erro ao enviar mensagem.")
                exibirMensagem("Tente enviar novamente.")
            }
    }


    private fun recuperarDadosUsuarios() {

        val idUsuarioLogado = firebaseAuth.currentUser?.uid.toString()


        // Recuperando dados do usuario logado
        firebaseFirestore
            .collection(Constantes.USUARIOS)
            .document(idUsuarioLogado)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val usuario = documentSnapshot.toObject(Usuario::class.java) // Convertendo esse documento para objeto
                if (usuario != null) {
                    dadosUsuarioRemetente = usuario


                }

            }


        // Recuperando dados do destinatario
        val extras = intent.extras
        if (extras != null) {


            dadosDestinatario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                extras.getParcelable("dadosDestinatario", Usuario::class.java)
            } else {
                extras.getParcelable("dadosDestinatario")
            }


        }
    }

    private fun inicializarToolbar() {

        val toolbar = binding.tbMensagens
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = ""
            if (dadosDestinatario != null) {
                binding.textNome.text = dadosDestinatario!!.nome
                Picasso.get()
                    .load(dadosDestinatario!!.foto)
                    .into(binding.imageFotoPerfil)
            }

            setDisplayHomeAsUpEnabled(true)
        }

    }


}