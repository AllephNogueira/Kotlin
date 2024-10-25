package com.allephnogueira.projetoingresso.TesteAPI

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.allephnogueira.projetoingresso.R
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teste_api2)

        val eventImage: ImageView = findViewById(R.id.eventImage) // Imagem do topo
        val eventTitle: TextView = findViewById(R.id.eventTitle) // Titulo do filme
        val eventDate: TextView = findViewById(R.id.eventDate) // Data do filme

        val api = RetrofitClient.instance
        // Aqui estamos pegando a interface
        api.getUpcomingEvents().enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) { // Verificando se retornou algo
                    val eventResponse = response.body() // Passando para o eventResponse o Body que veio do servidor
                    val events = eventResponse?.items // Passando para o events os items que veio do servidor
                    if (events != null && events.isNotEmpty()) { // Testando para ver se tem algo dentro de events
                        val firstEvent = events[0] // Pegando o primeiro evento (Primeiro item da lista)
                        eventTitle.text = firstEvent.title // Passando o titulo
                        eventDate.text = firstEvent.date // Data do item

                        val imageUrl = firstEvent.images.firstOrNull()?.url // Pegando a imagem do item
                        Log.d("MainActivity", "Image URL: $imageUrl")

                        if (!imageUrl.isNullOrEmpty()) { // Tem algo dentro de imagem? Se tiver vamos carregar
                            Glide.with(this@MainActivity)
                                .load(imageUrl) // Carregando a imagem
                                .into(eventImage) // Carregando na imagem que criamos.
                        } else {
                            Log.e("MainActivity", "Image URL is null or empty")
                        }
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
