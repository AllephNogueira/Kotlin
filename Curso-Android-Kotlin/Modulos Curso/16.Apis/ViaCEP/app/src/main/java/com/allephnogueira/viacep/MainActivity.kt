package com.allephnogueira.viacep

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.viacep.api.EnderecoAPI
import com.allephnogueira.viacep.api.RetrofitHelper
import com.allephnogueira.viacep.databinding.ActivityMainBinding
import com.allephnogueira.viacep.model.Endereco
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


    private var endereco: Endereco? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBuscar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                endereco = retornarDadosApi()
                withContext(Dispatchers.Main) {
                    // Rua, Bairro, UF, Regiao
                    binding.textRua.text = "Rua: ${endereco?.logradouro}"
                    binding.textBairro.text = "Bairro: ${endereco?.bairro}"
                    binding.textUF.text = "UF: ${endereco?.uf}"
                    binding.textRegiao.text = "Região: ${endereco?.regiao}"

                }
            }
        }


    }


    suspend fun retornarDadosApi() : Endereco? {

        /* Iniciando uma variavel do tipo endereco */
        var endereco : Endereco? = null
        try {

            /* "Retrofit criando uma classe por baixo dos panos para recuperar endereco" */
            val enderecoAPI = retrofit.create(EnderecoAPI::class.java)
            val resposta = enderecoAPI.recuperarEndereco()

            /* Verificando se a resposta foi dos dados que veio da API foi positiva */
            if (resposta.isSuccessful){
                /* Adicionando os dados convertidos dentro da classe */
                endereco = resposta.body()
            }else {
                Log.i("info_api", "Não conseguimos recuperar os dados")
            }

        }catch (e : Exception){
            e.printStackTrace()
            Log.i("info_api", "Não conseguimos recuperar os dados")


        }

        /* Retornando a classe com os dados atualizados para poder usar em qualquer local */
        return endereco
    }





//        try {
//            val enderecoAPI = retrofit.create(EnderecoAPI::class.java)
//            retorno = enderecoAPI.recuperarEndereco()
//        }catch (e: Exception){
//            e.printStackTrace()
//            Log.i("info_api", "Não conseguimos recuperar os dados")
//        }
//
//        /* Verificando se os dados foi retornado para poder usar
//        * Se retorno não for nulo e tiver sucesso
//        * Dentro do body é onde vai estar todos os dados que ja foram convertidos.*/
//        if (retorno != null && retorno.isSuccessful) {
//            /* retornoApi é onde vai esta todos os dados ja em OBJETOS */
//            endereco = retorno.body()
//        }
//
//
//    }
}