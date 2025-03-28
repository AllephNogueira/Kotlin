package com.allephnogueira.whatsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.whatsapp.databinding.ActivityLoginBinding
import com.allephnogueira.whatsapp.utils.exibirMensagem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }


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

        inicializarEventosDeClique()
    }

    override fun onStart() {
        super.onStart()
        verificarUsuarioLogado()
    }

    private fun verificarUsuarioLogado() {
        val usuarioAtual = firebaseAuth.currentUser
        if ( usuarioAtual != null ) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun inicializarEventosDeClique() {
        binding.textCadastro.setOnClickListener {
            startActivity(
                Intent(this, CadastroActivity::class.java)
            )
        }

        binding.btnLogar.setOnClickListener {
            if (validarCampos()) {
                logarUsuario()
            }
        }

    }

    private fun logarUsuario() {
        firebaseAuth.signInWithEmailAndPassword(email, senha)
            .addOnSuccessListener {
                exibirMensagem("Logado com sucesso!")
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener { erro ->


                try {
                    throw erro
                } catch (emailNaoCadastrado: FirebaseAuthWeakPasswordException) {
                    emailNaoCadastrado.printStackTrace()
                    exibirMensagem("E-mail não cadastrado.")
                } catch (credenciaisInvalidas: FirebaseAuthInvalidCredentialsException) {
                    credenciaisInvalidas.printStackTrace()
                    exibirMensagem("E-mail ou senha estao incorretos.")
                }


            }
    }

    private fun validarCampos(): Boolean {

        email = binding.editEmailLogin.text.toString()
        senha = binding.editSenhaLogin.text.toString()


        if (email.isNotEmpty()) {
            binding.textInputLayoutEmailLogin.error = null
            if (senha.isNotEmpty()) {
                binding.textInputLayoutSenhaLogin.error = null
                return true
            } else {
                binding.textInputLayoutSenhaLogin.error = "Digite sua senha"
                return false
            }
        } else {
            binding.textInputLayoutEmailLogin.error = "Digite seu email"
            return false
        }
    }


}