package com.allephnogueira.configuracaoretrofit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.configuracaoretrofit.api.EnderecoAPI
import com.allephnogueira.configuracaoretrofit.api.RetrofitHelper
import com.allephnogueira.configuracaoretrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnIniciar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                recuperarEndereco()
            }
        }


    }

    private suspend fun recuperarEndereco() {
        /** Explicando o codigo
         * O metodo create ele faz assim:
         * Ele recebe a interface e ele mesmo cria e retorna para voce o objeto
         * Sem voce precisar de uma classe para usar o metodo
         * ai esse objeto permite voce acessa o metodo recuperarEndereco
         */
       val enderecoAPI = retrofit.create( EnderecoAPI::class.java )
        enderecoAPI.recuperarEndereco()
    }

}