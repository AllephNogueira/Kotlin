package com.allephnogueira.whatsapp

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.whatsapp.databinding.ActivityPerfilBinding
import com.allephnogueira.whatsapp.utils.exibirMensagem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class PerfilActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPerfilBinding.inflate(layoutInflater) }

    private var temPermissaoCamera = false
    private var temPermissaoGaleria = false

    private val gerenciadorDeGaleria = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            binding.imagePerfil.setImageURI(uri) // Aqui ja temos a imagem (URI) é a imagem
            uploadImagemStorage(uri)
        } else {
            exibirMensagem("Nenhuma imagem selecionada.")
        }
    }

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val firebaseStorage by lazy { FirebaseStorage.getInstance() }
    private val firestore by lazy { FirebaseFirestore.getInstance() }


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

        iniciarToolbar()
        soliciarPermissoes()
        inicializarEventosDeClique()

    }

    override fun onStart() {
        super.onStart()
        recuperarDadosIniciasDoUsuario()
    }

    private fun recuperarDadosIniciasDoUsuario() {
        val idUsuarioLogado = recuperarIdUsuario()

        firestore
            .collection("Usuarios")
            .document(idUsuarioLogado)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val dados =
                    documentSnapshot?.data

                if (dados != null) {
                    val nome = dados["nome"] as String
                    val fotoUrl = dados["foto"] as String

                    binding.editNomePerfil.setText(nome)

                    if (fotoUrl.isNotEmpty()) {
                        Picasso.get()
                            .load(fotoUrl)
                            .into(binding.imagePerfil)
                    }
                }
            }


    }

    private fun uploadImagemStorage(uri: Uri) {

        val idUsuario = recuperarIdUsuario()

        if (idUsuario.isNotEmpty()) {
            // fotos
            // usuarios
            // idUsuario
            // perfil.jpg
            firebaseStorage.getReference("fotos")
                .child("usuarios") //pasta
                .child(idUsuario)   // pasta 2
                .child("perfil.jpg") // pasta 3
                .putFile(uri)
                .addOnSuccessListener { task ->

                    exibirMensagem("Sucesso ao fazer upload da imagem.")
                    task.metadata
                        ?.reference
                        ?.downloadUrl
                        ?.addOnSuccessListener { uri ->
                            // Aqui vamos salvar o link da imagem do usuario

                            val dados = mapOf(
                                "foto" to uri.toString()
                            )

                            atualizarDadosPerfil(idUsuario, dados)

                        }


                }
                .addOnFailureListener {
                    exibirMensagem("Erro ao fazer upload na imagem")
                    exibirMensagem("Faça seu login novamente.")
                    firebaseAuth.signOut()
                }
        }

    }

    private fun atualizarDadosPerfil(idUsuario: String, dados: Map<String, String>) {
        firestore
            .collection("Usuarios")
            .document(idUsuario)
            .update(dados)
            .addOnSuccessListener {
                exibirMensagem("Sucesso ao atualizar perfil.")
            }
            .addOnFailureListener {
                exibirMensagem("Erro ao atualizar seu perfil")
            }
    }

    private fun recuperarIdUsuario(): String {
        val idUsuario = firebaseAuth.currentUser?.uid
        if (idUsuario != null) {
            return idUsuario
        } else {
            firebaseAuth.signOut()
            return ""
        }

    }

    private fun inicializarEventosDeClique() {
        binding.fabSelecionar.setOnClickListener {
            /** Usuario vai clicar para selecionar uma imagem na galeria
             * Mas vamos primeiro verificar se ele tem a permissao para abrir galeria
             *
             * Se tem permissao, vamos selecionar a imagem
             * Aqui é o tipo de item que vamos selecionar e a extensão que no caso pode ser qualquer uma
             *
             * Se nao tem permissao vamos da um erro e pedir para o usuario a permissao novamente.
             */
            if (temPermissaoGaleria) {
                gerenciadorDeGaleria.launch("image/*")
            } else {
                exibirMensagem("Você não tem permissão para acessar galeria")
                soliciarPermissoes()
            }

        }


        binding.btnAtualizarPerfil.setOnClickListener {
            // Recuperar idUsuario

            val idUsuario = recuperarIdUsuario()

            // Atualizar nome do usuario
            val nomeUsuario = binding.editNomePerfil.text.toString()

            // Verificar se usuario digitou o nome

            if (nomeUsuario.isNotEmpty()) {
                // Fazendo o map do dados que vamos atualizar
                val dados = mapOf(
                    "nome" to nomeUsuario
                )

                // Salvar nome de usuario no firebaseStore
                salvarDadosDoUsuario(idUsuario, dados)
            } else {
                exibirMensagem("Digite o seu nome.")
            }

        }
    }

    private fun salvarDadosDoUsuario(idUsuario: String, dados: Map<String, String>) {
        firestore
            .collection("Usuarios")
            .document(idUsuario)
            .update(dados)
            .addOnSuccessListener { exibirMensagem("Sucesso ao atualizar nome.") }
            .addOnFailureListener { exibirMensagem("Falha ao atualizar nome.") }


    }

    private fun soliciarPermissoes() {
        /** Devemos inciar a permissao de galeria, logo quando o usuario clicar em perfil */

        /* 1 Verificar se o usuario já tem a permissao.
        Adicionamos 2 atributos para isso.
            private var temPermissaoCamera = false
            private var temPermissaoGaleria = false

          Aqui vamos verificar, se o usuario tem a permisao ou não
          O == verifica se a permissao é igual a true.
         */
        temPermissaoCamera = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        temPermissaoGaleria = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED

        /* Sabemos que na primeira execução o usuario não vai ter essas permissoes
        Então vamos criar uma lista de permissoes negadas.
         */

        val listaPermissoesNegadas = mutableListOf<String>()
        if (!temPermissaoCamera) {
            // Não tem permissao da camera? então adicionamos ela na lista
            listaPermissoesNegadas.add(Manifest.permission.CAMERA)
        }
        if (!temPermissaoGaleria) {
            // Não tem permissao da camera? então adicionamos ela na lista
            listaPermissoesNegadas.add(Manifest.permission.READ_MEDIA_IMAGES)
        }

        if (listaPermissoesNegadas.isNotEmpty()) {
            // Sempre quando uma permissao é negada, adicionamos essa permissao dentro dessa lista
            // Agora se a lista NÃO ESTIVER VAZIA é sinal que tem permissao negada la dentro
            // AI sim vamos solicitar essas permissoes negadas novamente.


            // SOLICITAR MULTIPLAS PERMISSOES
            val gerenciadorDePermissoes = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissoes ->
                // Aqui vai ser o seguinte, ?: se existir a permissao e for verdadeira, vamos configurar o valor aqui
                // Se for falso, adicionamos o falso que ja esta configurado dentro de temPermissaoCamera
                temPermissaoCamera = permissoes[Manifest.permission.CAMERA] ?: temPermissaoCamera
                temPermissaoGaleria =
                    permissoes[Manifest.permission.READ_MEDIA_IMAGES] ?: temPermissaoCamera
            }
            gerenciadorDePermissoes.launch(listaPermissoesNegadas.toTypedArray())
        }


    }

    private fun iniciarToolbar() {
        val toolbar = binding.includeToolbarPerfil.tbPrincipal
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Perfil"

            setDisplayHomeAsUpEnabled(true)
        }
    }


}