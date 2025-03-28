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
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {ActivityLoginBinding.inflate(layoutInflater)}
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

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

    private fun inicializarEventosDeClique() {
        binding.textCadastro.setOnClickListener {
            startActivity(
                Intent(this, CadastroActivity::class.java))
        }

        binding.btnLogar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            firebaseAuth.signInWithEmailAndPassword(email,senha)
                .addOnSuccessListener {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .addOnFailureListener { erro ->
                    try {
                        throw erro
                    }catch (senhaInvalida : FirebaseAuthInvalidUserException) {
                        exibirMensagem("Email ou senha invalido!")
                    }
                }

        }

    }


}