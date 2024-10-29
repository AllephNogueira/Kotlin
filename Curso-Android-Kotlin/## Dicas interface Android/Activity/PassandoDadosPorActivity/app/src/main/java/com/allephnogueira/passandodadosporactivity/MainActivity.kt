package com.allephnogueira.passandodadosporactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Primeiro precisamos acessar o botao para utilizar depois
    lateinit var botaoAcessar : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        botaoAcessar = findViewById(R.id.button_acessar)

        val intent = Intent(this, SegundaActivity::class.java)

        botaoAcessar.setOnClickListener {
            intent.putExtra("nome", "Alleph Nogueira")
            startActivity(intent)
        }


    }
}