package com.allephnogueira.altapressaognvpro.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.altapressaognvpro.R
import com.allephnogueira.altapressaognvpro.databinding.ActivityLoginBinding
import com.allephnogueira.altapressaognvpro.model.VerificarUsuarioLogado
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    private val autenticacao by lazy { FirebaseAuth.getInstance() }

    // Iniciar o serviço do Google
    private lateinit var googleLoginCliente: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleLoginCliente = GoogleSignIn.getClient(this, gso)

        with(binding) {
            btnEntrar.setOnClickListener {
                val email = textInputEmail.text.toString()
                val senha = textInputSenha.text.toString()

                if (email.isEmpty()) {
                    exibirMensagem("Digite seu e-mail")
                } else if (senha.isEmpty()) {
                    exibirMensagem("Digite sua senha")
                } else {
                    try {
                        logandoUsuarioComLoginESenha(email, senha)
                    } catch (e: Exception) {
                        exibirMensagem("Ocorreu um erro ao tentar fazer login.")
                    }
                }
            }

            btnEsqueciSenha.setOnClickListener {

            }

            btnCadastrar.setOnClickListener {
                startActivity(
                    Intent(applicationContext, CadastroActivity::class.java)
                )
            }

            btnEntrarComGoogle.setOnClickListener {
                acessarUsandoGoogle()
            }
        }


    }

    override fun onStart() {
        super.onStart()
        VerificarUsuarioLogado.verifcarUsuarioLogado(this)

    }


    /*********************** METODOS ********************/

    private fun logandoUsuarioComLoginESenha(email: String, senha: String) {
        autenticacao.signInWithEmailAndPassword(email, senha)
            .addOnSuccessListener { task ->

                exibirMensagem("Entrada confirmada!")
                startActivity(Intent(this, MapsActivity::class.java))

            }
            .addOnFailureListener { exception ->
                val mensagemErro = exception.message
                exibirMensagem("Dados incorretos.")
            }

    }

    private fun exibirMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }


    private fun acessarUsandoGoogle() {

        val signInIntent = googleLoginCliente.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUII(account)
            }
        } else {
            Toast.makeText(this, "Acesso falhou, tente novamente mais tarde!", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun updateUII(account: GoogleSignInAccount) {
        val credencial = GoogleAuthProvider.getCredential(account.idToken, null)
        autenticacao.signInWithCredential(credencial).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(
                    Intent(this, MapsActivity::class.java)
                )
                //finish() // Fechar a tela de login
            } else {
                Toast.makeText(this, "Login falhou!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}