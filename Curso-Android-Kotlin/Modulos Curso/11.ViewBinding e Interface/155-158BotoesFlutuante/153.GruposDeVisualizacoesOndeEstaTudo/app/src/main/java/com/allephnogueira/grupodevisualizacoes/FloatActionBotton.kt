package com.allephnogueira.grupodevisualizacoes

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.allephnogueira.grupodevisualizacoes.databinding.ActivityFloatActionBottonBinding

class FloatActionBotton : AppCompatActivity() {

    private val binding by lazy {
        // Lazy: carregamento preguiçoso ele so vai carregar quando for chamado
        ActivityFloatActionBottonBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        with(binding) { // Com whith ele ja vai saber que estamos falando do objeto binding, então nao precisamos repetir.
            // Se nao tivesse o with, teriamos que ficar repetindo o objeto na frente de cada fabBotao por exemplo
            // ficando assim ; binding.fabBotaoExibir
            // binding.groupMenu
            // o whith sabe que estamos tratando do binding

            fabBotaoExibir.setOnClickListener {

                // Vamos verificar se o item esta visivel
                // Se tiver visivel, vamos esconder
                // Se tiver invisivel vamos tornar visivel

                if (groupMenu.isVisible) { // Se o item estiver visivel
                    groupMenu.visibility = View.INVISIBLE // Esconde o item
                }else {
                    groupMenu.visibility = View.VISIBLE
                }
            }

        }

    }
}