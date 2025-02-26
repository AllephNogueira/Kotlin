package com.allephnogueira.recuperandolista.View

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.recuperandolista.R
import com.allephnogueira.recuperandolista.api.FotosAPI
import com.allephnogueira.recuperandolista.api.RetrofitHelper
import com.allephnogueira.recuperandolista.databinding.ActivityPostagemComFotosBinding
import com.allephnogueira.recuperandolista.model.Fotos
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostagemComFotosActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPostagemComFotosBinding.inflate(layoutInflater)}
    private val retrofit by lazy { RetrofitHelper.retrofit }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.IO).launch {
            postagemUnicaComFoto()
        }
    }

    private suspend fun postagemUnicaComFoto() {
        var retorno : Response<Fotos>? = null

        try {
            val postagemComFotoAPI = retrofit.create(FotosAPI::class.java)
            retorno = postagemComFotoAPI.recuperarFoto(1)
        }catch (e: Exception) {
            e.printStackTrace()
        }

        if(retorno != null && retorno.isSuccessful) {
            val urlImagem = retorno.body()?.url


            withContext(Dispatchers.Main) {
                binding.textResultadoFoto.text = urlImagem.toString()

                val urlImagemGoogle = "https://i.ytimg.com/vi/wDr4S69y6Uk/maxresdefault.jpg?sqp=-oaymwEmCIAKENAF8quKqQMa8AEB-AH-CYAC0AWKAgwIABABGHIgYSg2MA8=&rs=AOn4CLC4dZvrlF2aQuXA34mwoEtn_QLZHQ"

                Picasso.get()
                    .load(urlImagemGoogle)
                    .into(binding.imageViewRecuperada)
            }



            Log.i("url_imagem", urlImagem.toString())
        }else {
            binding.textResultadoFoto.text = retorno?.code().toString()
        }
    }
}