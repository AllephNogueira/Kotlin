package com.allephnogueira.aulaacitivityfragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btnAbrirSegundaTela : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val intent = Intent(this, DetalhesActivity::class.java)

        btnAbrirSegundaTela = findViewById(R.id.button_abrir)
        btnAbrirSegundaTela.setOnClickListener {
            // Vamos passar os dados para a proxima activity por parametro.
            // Primeiro valor é o identificador do que voce quer passar
            // Segundo é o dado

            intent.putExtra("filme", "Harry Potter")
            intent.putExtra("classificacao", 8)
            intent.putExtra("avaliacoes", 300)
            intent.putExtra("exibicao", false) // exibição nos cinemas?



            startActivity(intent)
        }

    }

}