package com.allephnogueira.toolbar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.toolbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


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
        /** Esse metodo serve para configurar nossa barra para diferentes versoes
         *
         * A baixo vamos configurar os dados como nome, subtitulo e apos vamos inicialiazar a toolbar
         *
         */

        binding.tbPrincipal.setTitleTextColor(ContextCompat.getColor(this, R.color.white)) /* Configuração da cor */
        binding.tbPrincipal.title = "Youtube"
        binding.tbPrincipal.subtitle = "Videos a todo momento"


        /** Nesse metodo vamos passar nossa toolbar e vamos configurar ela como se fosse uma ActionBar
         *
         */
        setSupportActionBar(binding.tbPrincipal)
    }


}