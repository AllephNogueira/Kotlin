package com.allephnogueira.whatsapp.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.whatsapp.R
import com.allephnogueira.whatsapp.databinding.ActivityMensagensBinding
import com.allephnogueira.whatsapp.model.Mensagem
import com.allephnogueira.whatsapp.model.Usuario
import com.allephnogueira.whatsapp.utils.Constantes
import com.allephnogueira.whatsapp.utils.exibirMensagem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class MensagensActivity : AppCompatActivity() {


    private val binding by lazy { ActivityMensagensBinding.inflate(layoutInflater) }
    private var dadosDestinatario: Usuario? = null

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val firebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }


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

        recuperarDadosUsuarioDestinatario()
        inicializarToolbar()
        inicializarEventosDeClique()

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

                // Salvar para o destinatario
                salvarMensagemFirestore(
                    idUsuarioDestinatario, idUsuarioRemetente, mensagem
                )

                binding.editMensagem.setText("")


            }
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


    private fun recuperarDadosUsuarioDestinatario() {

        val extras = intent.extras
        if (extras != null) {


            val origem = extras.getString("origem")
            if (origem == Constantes.ORIGEM_CONTATO) {

                dadosDestinatario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    extras.getParcelable("dadosDestinatario", Usuario::class.java)
                } else {
                    extras.getParcelable("dadosDestinatario")
                }

            } else if (origem == Constantes.ORIGEM_CONVERSA) {
                // Recuperar os dados da conversa....

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