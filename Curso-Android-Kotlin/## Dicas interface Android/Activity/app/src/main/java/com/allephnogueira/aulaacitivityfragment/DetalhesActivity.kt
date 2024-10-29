package com.allephnogueira.aulaacitivityfragment

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalhesActivity : AppCompatActivity() {

    lateinit var btnFechar : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("clico_vida", "onCreate")
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalhes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnFechar = findViewById(R.id.button_fechar)
        btnFechar.setOnClickListener {
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        //Carregar os dados que vem do servidor
        //Imagina o alta pressão gnv, aqui é onde vamos carregar os dados do posto.
        Log.i("cliclo_vida", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("clico_vida", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("clico_vida", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("clico_vida", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("clico_vida", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("clico_vida", "onDestroy")
    }


}