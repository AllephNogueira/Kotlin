package com.allephnogueira.configuracaoretrofit

import android.os.Bundle
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.configuracaoretrofit.api.EnderecoAPI
import com.allephnogueira.configuracaoretrofit.api.RetrofitHelper
import com.allephnogueira.configuracaoretrofit.databinding.ActivityMainBinding
import com.allephnogueira.configuracaoretrofit.model.Endereco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    private val retrofit by lazy {
        RetrofitHelper.retrofit
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


        binding.btnIniciar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                recuperarEnderecoMetodo()

                withContext(Dispatchers.Main) {

                    binding.btnIniciar.text = "Iniciado"


                }
            }
        }


    }

    private suspend fun recuperarEnderecoMetodo() {

        /* Retorno: estamos pegando o objeto convertido de JSON para nossa CLASSE

         */
        var retorno: Response<Endereco>? = null

        try {
            /** Explicando o codigo
             * O metodo create ele faz assim:
             * Ele recebe a interface e ele mesmo cria e retorna para voce o objeto
             * Sem voce precisar de uma classe para usar o metodo
             * ai esse objeto permite voce acessa o metodo recuperarEndereco
             */
            val enderecoAPI = retrofit.create( EnderecoAPI::class.java )
            retorno = enderecoAPI.recuperarEndereco() // metodo dentro da INTERFACE


        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_endereco", "Erro ao recuperar")
        }

        /* Agora vamos verificar se ese retorno é nulo, se ele conseguiu ou nao trazer os dados
        * Não precisamos fazer o else, porque ele só vai cair aqui se por acaso nao retornar o catch */

        if (retorno != null) {
            /* Se ele conseguiu trazer os dados e ele é diferente de nulo  */
            if (retorno.isSuccessful) {
                /* Esse metodo ele retorna sucesso se ele conseguiu fazer a requisição */
                /* Retorno é um objeto response e para capturar usamos o body(corpo)
                * O retorno é igual ao objeto = ENDERECO
                *
                * Porque nao precisamos instanciar? porque a propria retrofit
                * Ja fez isso pra gente, quando passamos la nossa classe
                * Ela mesmo ja converteu todos os objetos para dentro da classe que criamos.*/

                val endereco = retorno.body() // Aqui é onde esta o meu ENDERECO com todos os atributos.
                val rua = endereco?.logradouro
                val bairro = endereco?.bairro
                val cidade = endereco?.localidade


                Log.i("info_endereco", "Endereco: $rua")

            }

        }


    }

}