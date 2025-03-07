package com.allephnogueira.altapressaognvpro.view

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

class CadastroActivity : AppCompatActivity() {

    private val binding by lazy {ActivityCadastroBinding.inflate(layoutInflater)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCadastrar.setOnClickListener {
            val email = binding.textInputEmail.text.toString()
            val senha = binding.textInputSenha.text.toString()
            Log.i("CadastroUsuario_dados", "$email - $senha")



            // Futuramente verificar se o email e valido...
            cadastrarUsuario(email, senha)




        }


    }

    private fun cadastrarUsuario(email: String, senha: String) {
        val autenticador = FirebaseAuth.getInstance()


        autenticador.createUserWithEmailAndPassword(email, senha).addOnSuccessListener {authResult ->

            /* AQUI É SE TUDO DER CERTO VAMOS CAIR AQUI
            val email = authResult?.user // retorna o email do usuario
            val idUsuario = authResult?.user?.uid // Retorna o id do usuario

             */


            // MENSAGEM DE SUCESSO, TRAVAMENTO DO BOTAO, MODIFICAR NOME DO BOTAO E COR.
            toastMensagem("Usuario cadastrado com sucesso!")
            binding.btnCadastrar.isEnabled = false
            binding.btnCadastrar.text = "Conta criada"
            binding.btnCadastrar.setBackgroundColor(ContextCompat.getColor(this, R.color.preto))



        }.addOnFailureListener { exception ->
            val mensagemDoErro = exception.message
            toastMensagem("Falha: $mensagemDoErro")
            Log.i("Falha_cadastro", "$mensagemDoErro")
            binding.btnCadastrar.setBackgroundColor(ContextCompat.getColor(this, R.color.vermelho))
        }

    }

    private fun toastMensagem(mensagem: String){
        Toast.makeText(applicationContext, mensagem, Toast.LENGTH_LONG).show()
    }
}