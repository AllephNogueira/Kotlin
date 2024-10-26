package com.allephnogueira.projetoingresso

import android.content.Intent
import android.icu.util.LocaleData
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.projetoingresso.Filmes.DetalhesFilmesActivity
import com.allephnogueira.projetoingresso.Service.Event
import com.allephnogueira.projetoingresso.Service.EventResponse
import com.allephnogueira.projetoingresso.Service.RetrofitClient
import com.bumptech.glide.Glide
import kotlinx.datetime.LocalDateTime
import org.jetbrains.annotations.TestOnly
import retrofit2.Call
import retrofit2.Response
import java.time.format.DateTimeFormatter

class PrincipalActivity : AppCompatActivity() {

    companion object {
        var filmeNaImagemDoTopo : Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        Log.d("Debug", "Activity aberta.")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val imagemTopo: ImageView = findViewById(R.id.imageAnuncio) // Imagem do topo
        val nomeFilmeTopo: TextView = findViewById(R.id.textNomeTopo) // Titulo do filme
        val dataFilmeTopo: TextView = findViewById(R.id.textTopoData) // Data do filme
        val generoDoFilme: TextView = findViewById(R.id.textTopoGenero) // Genero do filme


        val api = RetrofitClient.instance
        // Aqui estamos pegando a interface
        api.getUpcomingEvents().enqueue(object : retrofit2.Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) { // Verificando se retornou algo
                    val eventResponse =
                        response.body() // Passando para o eventResponse o Body que veio do servidor
                    val events =
                        eventResponse?.items // Passando para o events os items que veio do servidor
                    if (events != null && events.isNotEmpty()) { // Testando para ver se tem algo dentro de events


                        // Aqui vamos criar um Handle para o filme que esta no topo ficar repetindo
                        // Crie um Handler para executar tarefas a cada 3 segundos
                        val handler = Handler(Looper.getMainLooper())
                        val runnable = object : Runnable {
                            override fun run() {

                                // Aqui pegamos o tamanho total da lista (indices)
                                // Depois colocamos um numero aleatorio (Random)

                                val numeroAleatorio = events.indices.random()

                                // Agora vamos utilizar esse numero aletorio
                                val primeiroEvento = events[numeroAleatorio]

                                filmeNaImagemDoTopo = numeroAleatorio // Passa o indice pra ca pra gente poder utilizar na outra Acitivity


                                Log.d("Debug", "Id do evento que esta passando na tela $numeroAleatorio")
                                Log.d("Debug", "Evento que esta passando agora ${events.indices}")

                                nomeFilmeTopo.text = primeiroEvento.title
                                Log.d("Debug","Nome do filme que esta exibindo na tela ${nomeFilmeTopo.text}")

                                generoDoFilme.text = primeiroEvento.genres.firstOrNull()

                                // Converter a data
                                // Exibir data de estreia junto com o dia da semana.
                                val premiereDate = primeiroEvento.premiereDate
                                val formattedDate =
                                    "${premiereDate.dayAndMonth}/${premiereDate.year} (${premiereDate.dayOfWeek})"
                                dataFilmeTopo.text = formattedDate

                                val imageUrl = primeiroEvento.images.firstOrNull()?.url
                                if (!imageUrl.isNullOrEmpty()) {
                                    Glide.with(this@PrincipalActivity)
                                        .load(imageUrl)
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
                Log.e("MainActivity", "Erro: ${t.message}")
            }
        })
    }


    // Agora vamos chamar a proxima tela pegando os detalhes do filme que esta exibindo na imagem a cima.
    fun abrirDetalhesDeFilme(view: View) {
        Log.d("Debug", "Abrindo detalhes de filmes detalhes de filme")
        startActivity(Intent(this, DetalhesFilmesActivity::class.java))
    }


}


//    fun ultimosLancamentos(view: View){
//        Log.d("Debug", "Iniciar activity de ultimosLancamentos")
//        startActivity(Intent(this, UltimosLancamentosActivity::class.java))
//    }
//
//    fun filmesEmBReve(view: View) {
//        Log.d("Debug", "Iniciar activity de filmes em breve.")
//        startActivity(Intent(this, EmBreveActivity::class.java))
//    }
//
//    fun abrirDetalhesDeFilme(view: View) {
//        Log.d("Debug", "Abrindo detalhes de filmes detalhes de filme")
//        startActivity(Intent(this, DetalhesFilmesActivity::class.java))
//    }
