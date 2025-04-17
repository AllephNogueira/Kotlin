package com.allephnogueira.altapressaognvpro.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.altapressaognvpro.R
import com.allephnogueira.altapressaognvpro.databinding.ActivityCadastroBinding
import com.allephnogueira.altapressaognvpro.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CadastroActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCadastroBinding.inflate(layoutInflater) }

    private val autenticador by lazy { FirebaseAuth.getInstance() }

    private val bancoDeDados by lazy { FirebaseFirestore.getInstance() }

    lateinit var usuario: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /* Recupera oque esta vindo da outra intent
        Se dentro de texto estiver a palavra Google
        Vamos pedir para o usuario atualizar seus dados.
         */


        val texto = intent.getStringExtra("texto")


        with(binding) {

            if (texto == "Google") {
                terminarCadastroComGoogle("Termine seu cadastro.")
            }

            /* Recuperar os dados que os usuarios estao digitando
            * Lembrar de sempre fazer isso dentro de um onClick */

            btnCadastrar.setOnClickListener {
                val email = editTextEmail.text.toString()
                val senha1 = editTextSenha.text.toString()
                val senha2 = editTextSenha2.text.toString()
                val nome = editTextNome.text.toString()
                val sobrenome = editTextSobrenome.text.toString()
                val telefone = editTextTelefone.text.toString()
                val anoNascimento = editTextAnoNascimento.text.toString()


                usuario =
                    Usuario("-1", email, senha1, senha2, nome, sobrenome, telefone, anoNascimento)


                if (verificarCamposVazios(usuario)) {
                    return@setOnClickListener
                }

                if (verificarSeSenhasSaoIguais(usuario)) {
                    return@setOnClickListener
                }

                if (texto == "Google") {
                    usuario.id = autenticador.currentUser?.uid.toString()
                    cadastrandoUsuarioComIdUnico(usuario)

                }else {
                    cadastrarUsuario(usuario)
                }





            }
        }


    }

    private fun terminarCadastroComGoogle(texto: String) {
        binding.textoCadastro.text = texto
        // Se tiver a mensagem de termine seu cadastro é sinal que o usuario esta conectado ao google.
        // Agora vamos recuperar alguns dados

        val email = intent.getStringExtra("email")
        binding.editTextEmail.setText(email)
        binding.editTextEmail.isEnabled = false

        Log.i("CadastroActivity", "Usuario novo / usuario null")
    }

    private fun verificarCamposVazios(usuario: Usuario) : Boolean {
        /* Verificar se todos os campos foram preenchidos primeiro
        *  1 - Criar uma lista para armazenar os valores digitados
        *  2 - percorre toda a lista e verifica se algum item e null ou vazio
        *  3 - Se algum item estiver vazio ou null ele retorna o verdadeiro
        *
        *   Se ele retorna um VERDADEIRO ele cai na mensagem e retorna um VERDADEIRO
        *   Ai usamos la fora para trava a execução do codigo.
        *
        * ANY ==== O any é uma função de extensão no Kotlin que é utilizada para verificar se algum elemento de uma coleção (como uma lista) atende a uma determinada condição.
        *           Se pelo menos um elemento satisfizer a condição, any retorna true. Caso contrário, retorna false.
        * */
        val campos = listOf(usuario.email, usuario.senha, usuario.senha2, usuario.nome, usuario.sobrenome, usuario.telefone, usuario.anoNascimento)
        val todosOsCampos = campos.any { it.isNullOrBlank() }
        if (todosOsCampos) {
            exibirMensagem("Preencha todos os campos")

        }

        return todosOsCampos



    }

    private fun verificarSeSenhasSaoIguais(usuario: Usuario) : Boolean {
        if (usuario.senha != usuario.senha2) {
            exibirMensagem("ERRO: Senhas diferentes")
            exibirMensagem("As senhas precisam ser iguais!")
            return true
        } else {
            Log.i("info_cadastro", "OK: Senhas iguais!")
            return false
        }
    }

    private fun cadastrarUsuario(usuario: Usuario) {

        if (usuario.email != null && usuario.senha != null) {

            autenticador.createUserWithEmailAndPassword(usuario.email!!, usuario.senha!!)
                .addOnSuccessListener { authResult ->

                    /* AQUI É SE TUDO DER CERTO VAMOS CAIR AQUI
                    val email = authResult?.user // retorna o email do usuario
                    val idUsuario = authResult?.user?.uid // Retorna o id do usuario

                     */

                    /* Recuperando ID e EMAIL do usuario cadastrado */
                    usuario.id = authResult.user?.uid.toString()
                    usuario.email = authResult.user?.email.toString()
                    Log.i("Id_usuario", "${usuario.id}")

                    if (usuario.id != null) {
                        cadastrandoUsuarioComIdUnico(usuario)
                    }

                }.addOnFailureListener { exception ->
                    val mensagemDoErro = exception.message
                    exibirMensagem("Falha: $mensagemDoErro")
                    Log.i("Falha_cadastro", "$mensagemDoErro")
                    binding.btnCadastrar.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.vermelho
                        )
                    )
                }

        }



    }

    private fun cadastrandoUsuarioComIdUnico(usuario: Usuario) {
        /* Se ID nao vier nulo vamos fazer isso */

        usuario.id?.let { idUsuario ->

            val dados = mapOf(
                "id" to usuario.id,
                "email" to usuario.email,
                "senha" to usuario.senha,
                "senha2" to usuario.senha2,
                "nome" to usuario.nome,
                "sobrenome" to usuario.sobrenome,
                "telefone" to usuario.telefone,
                "anoNascimento" to usuario.anoNascimento
            )


            bancoDeDados
                .collection("usuarios")
                .document(idUsuario)
                .set(dados).addOnSuccessListener {
                    exibirMensagem("Cadastro realizado com sucesso!")
                    val intent = Intent(applicationContext, MapsActivity::class.java)
                    startActivity(intent)


                }.addOnFailureListener {
                    exibirMensagem("Erro: confira os dados.")
                }
        }

    }

    private fun exibirMensagem(mensagem: String) {
        Toast.makeText(applicationContext, mensagem, Toast.LENGTH_LONG).show()
    }
}