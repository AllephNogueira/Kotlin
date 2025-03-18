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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

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
            //cadastroDeUsuario() // FirebaseAuth
            //logarUsuario() // FirebaseAuth


            //salvarDados() // Firebase - Firestone
            //atualizarRemoverDados()
            //gerandoUmUsuarioComIdUnico()

            //recuperarDadosComGET()

            //recuperarDadosUsuariosAtualizados()

            //listaComTodosOsUsuarios()

            pesquisarDados()


        }
    }

    private fun pesquisarDados() {
        // Acessando a lista de usuarios
        val referenciaUsuarios = bancoDeDados
            .collection("usuarios")
            //.whereEqualTo("nome", "Alleph N") // Onde nome for igual a Alleph (Atenção ele diferencia maiuscula de minuscula)
            //.whereNotEqualTo("nome", "Alleph N") // Agora ele vai pegar todos que nao for igual a Alleph N
            //.whereIn("nome", listOf("Alleph N", "Crixus")) // Pesquisar apenas o valores que estao dentro da lista
            //.whereNotIn("nome", listOf("Alleph N", "Crixus")) // Aqui vamos trazer todos que nao esteja na lista.
            //.whereArrayContains("conhecimentos", "subir na janela") // Criamos um array no banco de dados e trouxemos apenas os usuarios que conhecem kotlin

            // Aplicando filtros com <, >, <=, >=
            //.whereGreaterThan("anoNascimento", "2007") // Nesse exemplo estamos pesquisando onde anoNascimento seja maior que 2007, assim vamos pegar somente os menores de idade
            // Maior ou igual >=
            //.whereGreaterThanOrEqualTo("anoNascimento", "2007") // Maior ou igual

            // Menor que <
            //.whereLessThan("anoNascimento", "2007") // Agora vamos trazer os maiores de idade
            // Menor ou Igual a <=
            //.whereLessThanOrEqualTo("anoNascimento", "2007")  // Menor ou igual


        /* Filtrar entre dois valores maior que > 1994
            Ate menor que < 2007
         */
            .whereGreaterThanOrEqualTo("anoNascimento", "1996") // Maior ou igual
            .whereLessThanOrEqualTo("anoNascimento", "2020") // Menor ou igual
            /* Aqui estamos passando pelo campo que queremos ordenar
            Porque poderia ser puxado mais de um campo, exemplo: Data da compra, ano lancamento produto...
            E também colocamos a ordem que queremos, no caso aqui foi de menor para maior.
             */
            .orderBy("anoNascimento", Query.Direction.ASCENDING)



        // Acessando a lista de documento

        referenciaUsuarios.addSnapshotListener { querySnapshopt, error ->
            val listaDeDocumentos = querySnapshopt?.documents

            // Onde vamos armazenar o resultado dos nomes
            var listaResultadoDocumento = ""

            listaDeDocumentos?.forEach { documentSnapshot ->
                // Percorrendo a lista de documentos.
                val dados = documentSnapshot?.data
                if (dados != null) {
                    val nome = dados["nome"] // Estamos acessando a chave nome, para pegar o valor dela
                    val anoNascimento = dados["anoNascimento"]


                    listaResultadoDocumento += "$nome - $anoNascimento\n" // Aqui estamos acumulando todos os usuarios.


                }
            }
            binding.textResultado.text = listaResultadoDocumento
        }

    }

    private fun listaComTodosOsUsuarios() {
        /* Dessa maneira vamos recuperar uma lista de documentos,
        Depois vamos entrar dentro de cada um deles e vamos recuperar seus dados.

        Atençao reparar que percorremos primeiro o documento
        Depois percorremos oque esta dentro de cada documento
         */

        val referenciaLista = bancoDeDados
            .collection("usuarios")

        referenciaLista.addSnapshotListener { querySnapshot, error ->
            // Reparar que agora estamos trazendo um query e nao um documento
            // Porque estamos trazendo varios documentos

            // Dessa forma vamos trazer uma lista de documentos
            val listaDocuments = querySnapshot?.documents


            // Vamos guardar os dados que percorremos dentro da variavel
            var listaResultado = ""
            listaDocuments?.forEach { documentSnapshot ->
                // percorrer essa lista de documentos
                // Quando entramos dentro de documents, vamos ter as colunas/ DocumentSnapshot
                /* Atenção como estamos percorrendo um documents, agora vamos ter as colunas
                * E ai fazemos a mesma coisa que fizemos nos outros.
                *
                * Resumindo vamos percorrer cada um dos documentos e pegar os dados deles.*/
                val dados = documentSnapshot.data
                if (dados != null) {
                    val nome = dados["nome"]
                    val anoNascimento = dados["anoNascimento"]

                    listaResultado += "nome: $nome - nascimento: $anoNascimento \n"
                }

            }

            binding.textResultado.text = listaResultado

        }
    }

    private fun recuperarDadosUsuariosAtualizados() {
        // dessa forma sempre que tiver alguma atualização os dados vai ser atualizado também.
        val idUsuarioLogado = autenticacao.currentUser?.uid // Pegar id do usuario LOGADO

        if (idUsuarioLogado != null) { // Salvar a referencia do usuario
            val referenciaUsuario = bancoDeDados
                .collection("usuarios")
                .document(idUsuarioLogado)


            // Agora vamos pegar os dados e sempre que esses dados mudarem vamos atualizar
            // O Firebase vai sempre notificar a gente de mudanças.
            referenciaUsuario.addSnapshotListener{documentSnapshot, erro ->
                val dados = documentSnapshot?.data // Precisamos verificar porque os valores/colunas podem nao existir.
                if (dados != null) {
                    val nome = dados["nome"]
                    val anoNascimento = dados["anoNascimento"]

                    val texto = "Seja bem vindo: $nome - $anoNascimento"
                    binding.textResultado.text = texto
                }
            }

        }

    }

    private  fun recuperarDadosComGET(){

        //salvarDadosUsuario("AllephNogueira", "1994")

        /* Aqui vamos recuperar os dados do usuario
        vamos recuperar os dados do usuario que esta logado */

        val idUsuarioLogado = autenticacao.currentUser?.uid

        if (idUsuarioLogado != null) {
            val referenciaUsuario = bancoDeDados
                .collection("usuarios")
                .document(idUsuarioLogado)

            /* Get = pegar
            Vamos pegar os dados do usuario que vai retornar da nossa consulta a cima
            Atençao o GET pegamos esse dado de uma unica vez, como é a data de nascimento e isso não vai ficar sendo alterado
            Podemos recuperar com o GET
             */
            referenciaUsuario
                .get()
                .addOnSuccessListener { documentSnpashot ->
                    /* Aqui dentro se conseguir recuperar os dados, vamos ter um documentSnapshot
                    Snapshot = instantanea ( algo pego na hora )
                    Ou seja ele vai capturar o documento como esta naquele momento.
                     */

                    /* Aqui estamos recuperando o documento na hora
                    o data = os dados que estao la dentro.
                     */
                    val dados = documentSnpashot.data

                    /* Atenção os dados é do tipo map, então ele vai ter chave e valores.
                    Atençao os dados que vem do servidor podem ser nulos, então voce deve verificar também
                     */

                    if (dados != null) {
                        /* Se ele nao retornar null, vamos poder pegar os dados
                        Atençao as colunas devem esta da mesma forma que voce esta inserindo aqui
                        Nesse modelo a baixo voce esta inserindo a chave da e ele vai retornar o valor daquela chave.
                         */

                        val nome = dados["nome"]
                        val anoNascimento = dados["anoNascimento"]

                        /* Agora vamos inserir esses dados na tela

                         */

                        binding.textResultado.text = "Usuario: $nome - nascido em $anoNascimento"


                    }


                }
                .addOnFailureListener {
                    exibirMensagem("Falha em recuperar os dados")
                }

        }


    }

    private fun salvarDadosUsuario(nome: String, anoNascimento: String) {
        /*
        Esse metodo salva os dados do usuario pegando seu proprio ID do autenticador e criando uma coleção e documento com seu proprio ID
         */

        // Pegamos o ID do usuario que esta logado
        val idUsuarioLogado = autenticacao.currentUser?.uid
        // Verificamos se o id e diferente de nulo
        if (idUsuarioLogado != null) {
            // Se for diferente de nulo vamos salvar os dados no Firebase
            // Colunas e dados que vamos salvar dentro delas
            val dados = mapOf(
                // Esses dados aqui tambem podem vir de outra forma, ou seja nos capturando esses dados.
                "nome" to nome,
                "anoNascimento" to anoNascimento
            )

            // Vamos criar a coleção usuarios
            // Nome do documento vai ser ID do usuario
            // Exibir mensagem se for sucesso e tambem se for falha.
            bancoDeDados.collection("usuarios")
                .document("2")
                .set(dados)
                .addOnSuccessListener { Task ->
                    exibirMensagem("Dados salvo no banco de dados")
                }
                .addOnFailureListener { exception ->
                    exibirMensagem("ERRO: ${exception.message}")
                }

        }
    }

    private fun gerandoUmUsuarioComIdUnico() {

        /** Dessa forma seria se voce quisesse pegar o ID do usuario que esta logado
         * Para passar como ID no banco de dados
         */
        val usuarioLogado = FirebaseAuth.getInstance().currentUser?.uid

        val dados = mapOf(
            "nome" to "Crixus",
            "nascimento" to 2017,
            "raça" to "Shitzu",
            "propreitario" to "Alleph"
        )

        val referencia = bancoDeDados.collection("usuarios")

        /**
         * Passando o metodo .add
         * Não precisamos passar o ID ele mesmo vai gerar um identificador para esses dados.
         */
        referencia.add(dados)
            .addOnFailureListener {
                exibirMensagem("Sucesso ao salvar!")
            }
            .addOnFailureListener {
                exibirMensagem("Falha no salvamento!")
            }
    }

    private fun atualizarRemoverDados() {
        /**
         * Para atualizar ou remover precisamos selecionar o item que queremos.
         * Temos o usuario 1 e usuario 2
         * Vamos atualizar o usuario 2 "FERNANDA"
         */

        /* Dados que queremos atualizar, nesse caso colocamos tudo igual, mudamos somente o sobrenome */

        val dados = mapOf(
            "nome" to "Fernanda",
            "sobrenome" to "Nogueira"
            //"nascimento" to 1996 Removendo a linha não vai ter mas nascimento no documento da fernanda
        )

        /** Precisamos de uma referencia, para o dado que queremos alterar
         * Nesse exemplo aqui de baixo estamos pegando a referencia de onde esta os dados da Fernanda
         */

        val referencia = bancoDeDados
            .collection("usuarios")
            .document("2")


        /**
         * Apos pegar a referencia da Fernanda, vamos ter um objeto DocumentReference
         * Ai com esse objeto usamos o objeto set e passamos os novos dados.
         * Dessa forma vamos substituir todos os valores, e se faltar algum valor la em cima na coluna ele vai deletar a coluna
         */

        //referencia.set(dados)

        /**
         * Dessa forma vamos atualizar somente o valor que queremos
         * Nesse exemplo vamos utilizar chave : valor.
         */

//        referencia.update("nome", "Fernanda Ferreira")
//            .addOnSuccessListener {
//                exibirMensagem("Documento atualizado com sucesso!")
//            }
//            .addOnFailureListener {
//                exibirMensagem("Falha ao atualizar documento!")
//            }


        /**
         * Dessa forma também podemos remover a referencia no caso remover todo o documento
         */

        referencia.delete()


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
        verificarUsuarioLogado()
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

            if (idDoUsuario != null) {
                salvarDadosUsuario("Alleph", "1994")
            }


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
            val intent = Intent(this, UploadImagemActivity::class.java) // Se o usuario estiver logado, vamos jogar ele para a proxima tela
            startActivity(intent)

        }else{
            // Não existe usuario logado se der null
            exibirMensagem("Não tem usuario logado")
        }
    }

}