package com.allephnogueira.toolbar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.toolbar.databinding.ActivityToolbarSeparadaBinding

class ToolbarSeparada : AppCompatActivity() {

    private val binding by lazy { ActivityToolbarSeparadaBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarToolBar()

    }

    private fun inicializarToolBar() {

        binding.includeToolbar.clLogo.visibility = View.GONE // Nao quero que apareça o toolbar que definimos no XML


        binding.includeToolbar.tbPrincipal.title = "Enviar videos"



        setSupportActionBar(binding.includeToolbar.tbPrincipal) // Exibir a nossa toolbar como actionBar


        supportActionBar?.setDisplayHomeAsUpEnabled(true) // habilitar o botao de voltar para a activity inferior

    }
}