package com.allephnogueira.projetoingresso

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.projetoingresso.TesteAPI.EventResponse
import com.allephnogueira.projetoingresso.TesteAPI.RetrofitClient
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Response

class PrincipalActivity : AppCompatActivity() {




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
        val dataFilmeTopo: TextView = findViewById(R.id.textData) // Data do filme

        val api = RetrofitClient.instance
        // Aqui estamos pegando a interface
        api.getUpcomingEvents().enqueue(object : retrofit2.Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) { // Verificando se retornou algo
                    val eventResponse = response.body() // Passando para o eventResponse o Body que veio do servidor
                    val events = eventResponse?.items // Passando para o events os items que veio do servidor
                    if (events != null && events.isNotEmpty()) { // Testando para ver se tem algo dentro de events


                        // Aqui vamos criar um Handle para o filme que esta no topo ficar repetindo
                        // Crie um Handler para executar tarefas a cada 3 segundos
                        val handler = Handler(Looper.getMainLooper())
                        val runnable = object : Runnable {
                            override fun run() {
                                val primeiroEvento = events.random()
                                nomeFilmeTopo.text = primeiroEvento.title
                                dataFilmeTopo.text = primeiroEvento.date
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
                    Log.e("MainActivity", "Falha ao buscar eventos: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                Log.e("MainActivity", "Erro: ${t.message}")
            }
        })
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
