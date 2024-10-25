package com.allephnogueira.projetoingresso

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.projetoingresso.Filmes.DetalhesFilmesActivity
import com.allephnogueira.projetoingresso.Filmes.EmBreveActivity
import com.allephnogueira.projetoingresso.Filmes.UltimosLancamentosActivity
import com.allephnogueira.projetoingresso.api.RetrofitClient

class PrincipalActivity : AppCompatActivity() {

    private val retrofit by lazy {
        RetrofitClient.retrofit
        Log.d("Debug", "Teste de APIs")
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

    }

    fun ultimosLancamentos(view: View){
        Log.d("Debug", "Iniciar activity de ultimosLancamentos")
        startActivity(Intent(this, UltimosLancamentosActivity::class.java))
    }

    fun filmesEmBReve(view: View) {
        Log.d("Debug", "Iniciar activity de filmes em breve.")
        startActivity(Intent(this, EmBreveActivity::class.java))
    }

    fun abrirDetalhesDeFilme(view: View) {
        Log.d("Debug", "Abrindo detalhes de filmes detalhes de filme")
        startActivity(Intent(this, DetalhesFilmesActivity::class.java))
    }



}