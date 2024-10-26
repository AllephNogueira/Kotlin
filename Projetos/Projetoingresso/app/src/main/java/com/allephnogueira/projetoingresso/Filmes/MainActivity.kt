package com.allephnogueira.projetoingresso.Filmes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.projetoingresso.R
import com.allephnogueira.projetoingresso.Service.Event
import com.allephnogueira.projetoingresso.Service.MetodosAPIs
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imagemTopo: ImageView = findViewById(R.id.imageAnuncio) // Imagem do topo
        val nomeFilmeTopo: TextView = findViewById(R.id.textNomeTopo) // Titulo do filme
        val dataFilmeTopo: TextView = findViewById(R.id.textTopoData) // Data do filme
        val generoDoFilme: TextView = findViewById(R.id.textTopoGenero) // Genero do filme
        MetodosAPIs.filmesAleatorios(this, imagemTopo, nomeFilmeTopo, dataFilmeTopo, generoDoFilme)

        // Agora vamos começar a alterar as imagens das fotos de baixo
        // Aqui eu quero

        val imagemCardFilme : ImageView = findViewById(R.id.imageFilmeCard1)
        val textDataCardFilme : TextView = findViewById(R.id.textDataCard)
        val sinopseCardFilme : TextView = findViewById(R.id.textCardSinopse)
        MetodosAPIs.filmesParaOCard(this, imagemCardFilme, textDataCardFilme, sinopseCardFilme)


         



    }


    private fun displayMultipleEvents(events: List<Event>) {
        // Presumindo que você tem várias ImageViews para exibir múltiplos filmes
        val imagemFilme1: ImageView = findViewById(R.id.imageFilmeCard1)
        val imagemFilme2: ImageView = findViewById(R.id.imageFilmeCard2)
        val imagemFilme3: ImageView = findViewById(R.id.imageFilmeCard3)

        Glide.with(this).load(events[0].images.firstOrNull()?.url).into(imagemFilme1)
        Glide.with(this).load(events[1].images.firstOrNull()?.url).into(imagemFilme2)
        Glide.with(this).load(events[2].images.firstOrNull()?.url).into(imagemFilme3)

        // Atualizando também os TextViews se necessário
    }





    fun abrirDetalhesDeFilme(view: View) {
        Log.d("Debug", "Abrindo detalhes de filmes detalhes de filme")
        startActivity(Intent(this, DetalhesFilmesActivity::class.java))
    }

    fun ultimasEstreias(view: View){
        Log.d("Debug", "Abrindo ultimas estreias")
        startActivity(Intent(this, UltimosLancamentosActivity::class.java))
    }

    fun filmesEmBreve(view: View){
        Log.d("Debug", "Abrindo filmes em breve")
        startActivity(Intent(this, MainActivity::class.java))
    }
}