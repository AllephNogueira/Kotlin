package com.allephnogueira.projetoingresso.Service

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MetodosAPIs {
    // Problema, o numero aleatorio sempre fica sendo jogado, eu quero que ele va para a variavel apenas quando eu clicar no filmeAleatorio
    companion object {

        var numeroDeFilmeAleatorio: Int = 0
        var numeroImagemAbaixo: Int = 0


        // Método genérico para pegar dados da API

        fun filmesAleatorios(context: Context,
                             imagemTopo: ImageView,
                             nomeFilme : TextView,
                             dataFilmeTopo: TextView,
                             generoDoFilme: TextView) {


            val api = RetrofitClient.instance
            api.getUpcomingEvents().enqueue(object : Callback<EventResponse> {
                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>
                ) {
                    if (response.isSuccessful) {
                        val eventResponse = response.body()
                        val events = eventResponse?.items
                        // Ficar atualizando os filmes de cartaz
                        if (events != null && events.isNotEmpty()) {

                            val handler = Handler(Looper.getMainLooper())
                            val runnable = object : Runnable {
                                override fun run() {

                                    // Gerando um filme aleatorio
                                    numeroDeFilmeAleatorio = events.indices.random()
                                    val dadosDoFilme = events[numeroDeFilmeAleatorio]
                                    Log.d("FilmeEscolhido", "NUMERO ALEATORIO $numeroDeFilmeAleatorio")
                                    nomeFilme.text = dadosDoFilme.title
                                    generoDoFilme.text = dadosDoFilme.genres.firstOrNull() // Pegar sempre o primeiro

                                    // Convertendo a data do filme
                                    // Exibir data de estreia junto com o dia da semana.
                                    val premiereDate = dadosDoFilme.premiereDate
                                    val formattedDate =
                                        "${premiereDate.dayAndMonth}/${premiereDate.year}"
                                    dataFilmeTopo.text = formattedDate



                                    val imageUrl = dadosDoFilme.images.firstOrNull()?.url
                                    if (!imageUrl.isNullOrEmpty()) {
                                        Glide.with(context)
                                            .load(imageUrl.toString())
                                            .into(imagemTopo)
                                    }
                                    // Executa novamente após 5 segundos
                                    handler.postDelayed(this, 5000)
                                }
                            }
                            // Inicia a execução do runnable
                            handler.post(runnable)
                        } else {
                            Log.e("MainActivity", "No events found")
                        }
                    } else {
                        Log.e(
                            "MainActivity",
                            "Falha ao buscar eventos: ${response.errorBody()?.string()}"
                        )
                    }
                }
                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    Log.e("TestePegarDadosApi", "Erro: ${t.message}")
                }
            })
        }

        fun detalhesDoFilme(context: Context,
                            imagemTopo: ImageView,
                            nomeFilme : TextView,
                            dataFilmeTopo: TextView,
                            generoDoFilme: TextView,
                            sinopseDoFilme: TextView) {
            val api = RetrofitClient.instance
            api.getUpcomingEvents().enqueue(object : Callback<EventResponse> {
                override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                    if (response.isSuccessful) {
                        val eventResponse = response.body()
                        val events = eventResponse?.items
                        if (events != null && events.isNotEmpty()) {
                            val dadosDoFilme = events[numeroDeFilmeAleatorio]
                            Log.d("FilmeEscolhido", "NUMERO DA IMAGEM DE CIMA $numeroDeFilmeAleatorio")

                            nomeFilme.text = dadosDoFilme.title
                            generoDoFilme.text = dadosDoFilme.genres.firstOrNull() // Pegar sempre o primeiro

                            // Convertendo a data do filme
                            // Exibir data de estreia junto com o dia da semana.
                            val premiereDate = dadosDoFilme.premiereDate
                            val formattedDate =
                                "${premiereDate.dayAndMonth}/${premiereDate.year}"
                            dataFilmeTopo.text = formattedDate
                            if (dadosDoFilme.synopsis.isNotEmpty()){ // Se tiver algo dentro imprime se nao mostra uma mensagem.
                                sinopseDoFilme.text = dadosDoFilme.synopsis
                            }else{
                                sinopseDoFilme.text = "Desculpa informção ainda nao esta disponivel para esse filme."
                            }

                            val imageUrl = dadosDoFilme.images.firstOrNull()?.url
                            if (!imageUrl.isNullOrEmpty()) {
                                Glide.with(context)
                                    .load(imageUrl.toString())
                                    .into(imagemTopo)
                            }
                        }
                    } else {
                        Log.e("TestePegarDadosApi", "Falha ao buscar eventos: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    Log.e("TestePegarDadosApi", "Erro: ${t.message}")
                }
            })
        }


        fun filmesParaOCard(context: Context,
                            imagemTopo: ImageView,
                            dataFilmeTopo: TextView,
                            generoDoFilme: TextView) {

            val api = RetrofitClient.instance
            api.getUpcomingEvents().enqueue(object : Callback<EventResponse> {
                override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                    if (response.isSuccessful) {
                        val eventResponse = response.body()
                        val events = eventResponse?.items
                        if (events != null && events.isNotEmpty()) {
                            // Gerando um filme aleatorio

                            numeroDeFilmeAleatorio = events.indices.random()
                            val dadosDoFilme = events[numeroDeFilmeAleatorio]
                            Log.d("FilmeEscolhido", "NUMERO DA IMAGEM DE BAIXO $numeroDeFilmeAleatorio")


                            generoDoFilme.text = dadosDoFilme.genres.firstOrNull() // Pegar sempre o primeiro

                            // Convertendo a data do filme
                            // Exibir data de estreia junto com o dia da semana.
                            val premiereDate = dadosDoFilme.premiereDate
                            val formattedDate =
                                "${premiereDate.dayAndMonth}/${premiereDate.year}"
                            dataFilmeTopo.text = formattedDate


                            val imageUrl = dadosDoFilme.images.firstOrNull()?.url
                            if (!imageUrl.isNullOrEmpty()) {
                                Glide.with(context)
                                    .load(imageUrl.toString())
                                    .into(imagemTopo)
                            }
                        }
                    } else {
                        Log.e("TestePegarDadosApi", "Falha ao buscar eventos: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    Log.e("TestePegarDadosApi", "Erro: ${t.message}")
                }
            })
        }




    }
}
