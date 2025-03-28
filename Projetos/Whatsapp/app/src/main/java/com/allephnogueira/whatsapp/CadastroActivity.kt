package com.allephnogueira.whatsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.whatsapp.databinding.ActivityCadastroBinding
import com.allephnogueira.whatsapp.model.Usuario
import com.allephnogueira.whatsapp.utils.exibirMensagem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class CadastroActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCadastroBinding.inflate(layoutInflater) }
    private val autenticador by lazy { FirebaseAuth.getInstance() }
    private val bancoDeDados by lazy { FirebaseFirestore.getInstance() }
    private val storage by lazy { FirebaseFirestore.getInstance() }


    private lateinit var nome: String
    private lateinit var email: String
    private lateinit var senha: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarToolbar()
        inicializarEventosClique()


    }


    private fun inicializarToolbar() {
        /* Acessando nossa toolBar
        Lembrar de acessar o include primeiro, porque ela esta dentro dele
         */
        val toolbar = binding.includeToolbar.tbPrincipal
        /* Agora vamos usar esse metodo porque ele é um suporte para permitir que usamos a toolbar
         */
        setSupportActionBar(toolbar)

        /* Agora que ja passamos o suporte, podemos utilizar ele logo a baixo */

        supportActionBar?.apply {
            title = "Faça o seu cadastro"
            /*  setDisplayHomeAsUpEnabled
            Lembra que configuramos no Manifest que essa activity seria filha do Login?
            Então esse metodo cria um botao na toolBar que quando o usuario clicar nele, voce retorna para o pai (Tela login).
            ATENÇÃO: Você deve configurar no Manifest o parent, se não o botao não vai funcionar.
             */
            setDisplayHomeAsUpEnabled(true)
        }

    }

    private fun inicializarEventosClique() {
        binding.btnCadastrar.setOnClickListener {
            if (validarCampos()) {
                cadastrarUsuario(nome, email, senha)

            }
        }

    }

    private fun validarCampos(): Boolean {

        nome = binding.editNomeCadastro.text.toString()
        email = binding.editEmailCadastro.text.toString()
        senha = binding.editSenhaCadastro.text.toString()

        if (nome.isNotEmpty()) {
            binding.textInputLayoutNomeCadastro.error = null

            if (email.isNotEmpty()) {
                binding.textInputLayoutEmailCadastro.error = null

                if (senha.isNotEmpty()) {
                    binding.textInputLayoutSenhaCadastro.error = null
                    return true
                } else {
                    binding.textInputLayoutSenhaCadastro.error = "Digite sua senha!"
                    return false
                }
            } else {
                binding.textInputLayoutEmailCadastro.error = "Digite seu email!"
            }
        } else {
            binding.textInputLayoutNomeCadastro.error = "Digite seu nome!"
        }
        return false
    }

    private fun cadastrarUsuario(nome: String, email: String, senha: String) {

        autenticador.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { resultado ->
                if (resultado.isSuccessful) {

                    // Resultado dando tudo certo, vamos cadastrar o resto dos dados
                    val idUsuarioLogado = resultado.result.user?.uid

                    if (idUsuarioLogado != null) {
                        val usuario = Usuario(  idUsuarioLogado, nome, email  )
                        salvarUsuarioFireStore(usuario)
                    }

                }
            }.addOnFailureListener { erro ->
                try {
                    throw erro
                    /* Aqui dentro do catch podemos testar varios tipos de erros */
                } catch (erroSenhaFraca: FirebaseAuthWeakPasswordException) {
                    erroSenhaFraca.printStackTrace()
                    exibirMensagem("Digite uma senha mais forte!")
                }
                catch (erroEmailJaCadastrado: FirebaseAuthUserCollisionException) {
                    erroEmailJaCadastrado.printStackTrace()
                    exibirMensagem("E-mail já cadastrado")
                }
                catch (erroCredenciaisInvalidas: FirebaseAuthInvalidCredentialsException) {
                    exibirMensagem("E-mail ou senha invalidos!")
                }
            }
    }

    private fun salvarUsuarioFireStore(usuario: Usuario) {

        bancoDeDados.collection("Usuarios")
            .document(usuario.id)
            .set(usuario) // Aqui dentro já tem as chaves e valores  |||| nome = "alleph"
            .addOnSuccessListener {
                exibirMensagem("Cadastro realizado com sucesso.")
                // Cadastrando o restante dos dados vamos para a proxima tela
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                exibirMensagem("Erro ao fazer seu cadastro.")
            }

    }
}

