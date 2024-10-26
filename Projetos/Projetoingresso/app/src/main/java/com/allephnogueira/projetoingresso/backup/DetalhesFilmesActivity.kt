//package com.allephnogueira.projetoingresso.Filmes
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.allephnogueira.projetoingresso.backup.PrincipalActivity
//import com.allephnogueira.projetoingresso.R
//import com.allephnogueira.projetoingresso.Service.EventResponse
//import com.allephnogueira.projetoingresso.Service.RetrofitClient
//import com.bumptech.glide.Glide
//import retrofit2.Call
//import retrofit2.Response
//
//class DetalhesFilmesActivity : AppCompatActivity() {
//
//    val filmeQueVamosVerOsDetalhes = PrincipalActivity.filmeNaImagemDoTopo
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_detalhes_filmes)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//        val imagemTopo: ImageView = findViewById(R.id.imageAnuncio) // Imagem do topo
//        val nomeFilmeTopo: TextView = findViewById(R.id.textNomeTopo) // Titulo do filme
//        val dataFilmeTopo: TextView = findViewById(R.id.textTopoData) // Data do filme
//        val generoDoFilme: TextView = findViewById(R.id.textTopoGenero) // Genero do filme
//        val sinopseDoFilme: TextView = findViewById(R.id.textSinopse)
//
//        // Teste do filme que esta chegando
//        Log.d("FilmeChegando", "Filme chegando é $filmeQueVamosVerOsDetalhes")
//
//
//        val api = RetrofitClient.instance
//        // Aqui estamos pegando a interface
//        api.getUpcomingEvents().enqueue(object : retrofit2.Callback<EventResponse> {
//            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
//                if (response.isSuccessful) { // Verificando se retornou algo
//                    val eventResponse =
//                        response.body() // Passando para o eventResponse o Body que veio do servidor
//                    val events =
//                        eventResponse?.items // Passando para o events os items que veio do servidor
//                    if (events != null && events.isNotEmpty()) { // Testando para ver se tem algo dentro de events
//                        val primeiroEvento =
//                            events[filmeQueVamosVerOsDetalhes] // Aqui eu devo recuperar la da frente
//
//                        Log.d("Debug", "numero que esta vindo da outra tela.")
//                        val filmeNaImagemDoTopo = primeiroEvento.id
//                        Log.d(
//                            "Debug",
//                            "Id do evento que esta passando na tela $filmeNaImagemDoTopo"
//                        )
//                        Log.d("Debug", "Evento que esta passando agora $events")
//
//
//                        nomeFilmeTopo.text = primeiroEvento.title
//                        Log.d(
//                            "Debug", "Nome do filme que esta exibindo na tela ${nomeFilmeTopo.text}"
//                        )
//                        generoDoFilme.text = primeiroEvento.genres.firstOrNull()
//                        if (primeiroEvento.synopsis.isNotEmpty()) {
//                            sinopseDoFilme.text = primeiroEvento.synopsis
//                        }else{
//                            sinopseDoFilme.text = "Desculpe, esse filme esta sem informação!"
//                        }
//
//                        // Converter a data
//                        // Exibir data de estreia junto com o dia da semana.
//                        val premiereDate = primeiroEvento.premiereDate
//                        val formattedDate =
//                            "${premiereDate.dayAndMonth}/${premiereDate.year} (${premiereDate.dayOfWeek})"
//                        dataFilmeTopo.text = ("Data de lançamento: $formattedDate")
//
//                        val imageUrl = primeiroEvento.images.firstOrNull()?.url
//                        if (!imageUrl.isNullOrEmpty()) {
//                            Glide.with(this@DetalhesFilmesActivity)
//                                .load(imageUrl)
//                                .into(imagemTopo)
//                        }
//
//
//                    } else {
//                        Log.e("MainActivity", "No events found")
//                    }
//                } else {
//                    Log.e(
//                        "MainActivity",
//                        "Falha ao buscar eventos: ${response.errorBody()?.string()}"
//                    )
//                }
//            }
//
//            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
//                Log.e("MainActivity", "Erro: ${t.message}")
//            }
//        })
//
//
//    }
//
//
//    fun retornarParaInicio(view: View) {
//        Log.d("Debug", "retornando para inicio da tela")
//        startActivity(Intent(this, PrincipalActivity::class.java))
//    }
//}