package com.allephnogueira.aulafirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.aulafirebase.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    private val autenticacao by lazy { FirebaseAuth.getInstance() }


    private val bancoDeDados by lazy {
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

        binding.btnExecutar.setOnClickListener {
            //cadastroDeUsuario()
            //logarUsuario()


            salvarDados()
        }
    }

    private fun salvarDados() {

        /**
         * Lembrar dos passos a passos no Firestore
         *  Primeiro criamos uma coleção (Nome do nosso banco de dados)
         *  Depois adicionamos o ID para o documento....
         */

        /**
         * 1 Criando uma coleção
         * 2 Criando o documento
         * 3 Agora precisamos da chave e valor
         *      Podemos criar um map, nele vamos ter CHAVE E VALOR
         * 4 Simulando como se o usuario estivesse digitando os dados e a gente estivesse capturando seus dados.
         * 5 temos as operações para testar se deu tudo certo ou deu falha, como no FirebaseAuth
         */

        val colunas = mapOf(
            "nome" to "Fernanda",
            "sobrenome" to "Ferreira",
            "nascimento" to 1996
        )

        bancoDeDados
            .collection("usuarios") // Nome do nosso banco de dados
            .document("2") // Documento que identifca o usuario
            .set(colunas) // set espera receber um tipo de dado e qualquer valor
            .addOnSuccessListener { sucesso ->
                exibirMensagem("Usuário salvo com sucesso!")

            }.addOnFailureListener{ exception ->
                Log.i("falha_salvar_dados", exception.message.toString())
            }




    }


    override fun onStart() {
        super.onStart()
        //verificarUsuarioLogado()
    }

    private fun logarUsuario() {
        // Imagina uma tela de login

        val email = "alleph22@gmail.com"
        val senha = "teste12345@nogueira"

        val autenticador = FirebaseAuth.getInstance()

        autenticador.signInWithEmailAndPassword(email, senha)
            .addOnSuccessListener {// Se for sucesso caimos aqui
            authResult ->

                exibirMensagem("Autenticando...")
                startActivity(Intent(this, PrincipalActivity::class.java))
        }.addOnFailureListener {// Se for falha caimos aqui
            exception ->
            val mensagemDeErro = exception.message

            binding.textResultado.text = "Falha: $mensagemDeErro"
            exibirMensagem("$mensagemDeErro")
        }
    }

    private fun cadastroDeUsuario() {
        /* Imagina que estamos capturando os dados que o usuario digitou.
         *
         * O firebase vai verificar essa senha, ele exige que tenha pelo menos 6 caracteres e seja uma senha forte
         * Se a senha for fraca ele nao vai deixar voce cadastrar o usuario.
         *
         * TELA DE CADASTRO DO USUARIO. */

        val email = "alleph22@gmail.com"
        val senha = "teste12345@nogueira"

        /* USANDO AS CLASSES DO FIREBASE PARA CADASTRO
        * Atenção: é recomendado sempre usar a classe especifica para cada recurso que for utilizar
        ***** val autenticador = Firebase.auth // Não recomendado *****/

        val autenticador = FirebaseAuth.getInstance()

        /* Dentro do firebaseAuth vamos ter os metodos para criar o usuario
        Esse metodo serve para criar o usuario com email e senha

        addOnSuccessListener -> Vamos verificar se foi sucesso ou nao ao cadastrar usuario
        Apos a primeira chave vamos tratar o erro
        addOnFailureListener -> Em caso de falha vamos cair nesse outro escopo

        exibirMensagem() -> apenas um toast que vai exibir uma mensagem de sucesso ou falha
         */

        autenticador.createUserWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener { authResult -> // TESTAR O SUCESSO DO CADASTRO
            /* Metodos a baixo para usar se caso o usuario foi cadastrado corretamente */

            // Dentro de authResult temos varias informações
            // Dentro de user vamos ter todos os dados do usuario nesse exemplo capturamos o email apenas
            val email = authResult.user?.email


            // Aqui é para quando o usuario se cadastrar ele vai enviar um email de verificação e só vai ser verificado quando o usuario clicar no link
            val verificarEmail = authResult.user?.sendEmailVerification()

            // Aqui é o identificador do usuario o ID dele
            val idDoUsuario = authResult.user?.uid


            exibirMensagem("Sucesso ao cadastrar usuario $idDoUsuario - $email") // Exibe um toast com os dados
            binding.textResultado.text = "Sucesso: $idDoUsuario - $email"


        }.addOnFailureListener{ exception -> // TESTAR A FALHA DO CADASTRO

            val mensagemErro = exception.message

            exibirMensagem("Falha no cadastro")
            binding.textResultado.text = "FALHA: $mensagemErro"
        }



    }

    private fun exibirMensagem(texto: String) {
        Toast.makeText(applicationContext, texto, Toast.LENGTH_LONG).show()
    }


    private fun verificarUsuarioLogado() {

        //autenticacao.signOut()


        /* Aqui ele vai pegar o usuario atual
        CurrentUser ele vai pegar os dados do ultimo usuario.*/
        val usuario = autenticacao.currentUser

        val idUsuario = usuario?.uid // Pegar o id do usuario que esta logado.


        if (usuario != null) { // Verificar se o usuario esta logado
            exibirMensagem("Usuario esta logado com id: $idUsuario") // Se der verdadeiro quer dizer que existe usuario logado
            val intent = Intent(this, PrincipalActivity::class.java) // Se o usuario estiver logado, vamos jogar ele para a proxima tela
            startActivity(intent)

        }else{
            // Não existe usuario logado se der null
            exibirMensagem("Não tem usuario logado")
        }
    }

}