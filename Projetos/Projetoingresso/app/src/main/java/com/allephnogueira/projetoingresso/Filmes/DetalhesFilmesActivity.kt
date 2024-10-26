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
import com.allephnogueira.projetoingresso.Service.MetodosAPIs

class DetalhesFilmesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalhes_filmes)
        Log.d("NDetalhesFilmesActivity", "Activity criada")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("NDetalhesFilmesActivity", "Configurando insets listener")





        // Aqui eu preciso recuperar o numero de la e passar para eles
        val numeroDoFilmeRecuperado =

        try {
            val imagemTopo: ImageView = findViewById(R.id.imageAnuncio) // Imagem do topo
            val nomeFilmeTopo: TextView = findViewById(R.id.textNomeTopo) // Titulo do filme
            val dataFilmeTopo: TextView = findViewById(R.id.textTopoData) // Data do filme
            val generoDoFilme: TextView = findViewById(R.id.textTopoGenero) // Genero do filme
            val sinopseDoFilme: TextView = findViewById(R.id.textSinopse)
            Log.d("NDetalhesFilmesActivity", "Views inicializadas")

            // Chamando o método detalhesDoFilme
            MetodosAPIs.detalhesDoFilme(
                this,
                imagemTopo,
                nomeFilmeTopo,
                dataFilmeTopo,
                generoDoFilme,
                sinopseDoFilme
            )





            Log.d("NDetalhesFilmesActivity", "detalhesDoFilme chamado")
        } catch (e: Exception) {
            Log.e(
                "NDetalhesFilmesActivity",
                "Erro ao inicializar as views ou chamar o método detalhesDoFilme",e)
        }


    }

    fun retornarParaInicio(view: View) {
        Log.d("Debug", "retornando para inicio da tela")
        startActivity(Intent(this, MainActivity::class.java))
    }

}
