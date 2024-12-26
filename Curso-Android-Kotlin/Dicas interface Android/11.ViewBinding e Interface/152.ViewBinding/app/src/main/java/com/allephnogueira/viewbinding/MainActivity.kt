package com.allephnogueira.viewbinding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // by laze = carregamento preguiçoso ele só carrega na hora que voce for utilizar
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
        // Aqui nao precisamos  inflar nosso layoyt
        // Diferente das outras aulas, nao precisamos passar o nome do XML porque a propria classe ja sabe qual XML pertence a ela.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView( binding.root ) // Dentro desse root é onde tem nossa Activity inflada.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        // Agora acessando elementos da tela
        // Aqui nao precisa da chamada segura, porque ele sabe que sempre vamos ter espaço para esse botao
        // Ja o outro botao apenas se a tela for grande ou o celular estiver deitado.

        binding.btnClique.setOnClickListener {
            binding.btnClique.text = "Clicado"
        }

        // Repara que precisamos fzer a chamada segura ?
        // Porque ele sabe que podemos nao ter esse button em alguns layouts
        binding.btnLandascape?.setOnClickListener {
            binding.btnLandascape!!.text = "Clicado"
        }
    }
}