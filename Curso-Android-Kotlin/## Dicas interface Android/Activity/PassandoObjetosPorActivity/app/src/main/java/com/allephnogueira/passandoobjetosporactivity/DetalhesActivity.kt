package com.allephnogueira.passandoobjetosporactivity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class DetalhesActivity : AppCompatActivity() {

    lateinit var nomeFilme : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalhes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nomeFilme = findViewById(R.id.text_nomeFilme)


        val bundle = intent.extras


        /**
         * Podemos atribuir um metodo em uma variavel
         * Se a versao for maior que 33 a variavel vai receber o
         * Todos os metodo tanto o getSerializable e o Parcelable eles devem ser feito o teste
         *
         * bundle?.getSerializable("Filme", Filme::class.java)
         * bundle?.getParcelable("Filme", Filme::class.java)
         *
         * Essa forma que esta em cima é apenas para versões maiores que tiramisu(33)
         */

        val filme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.i("Build", "Versão do sistema é ${Build.VERSION.SDK_INT}")
            /**
             *
             * Aqui ele testa a versao se for maior que a 33
             * val filme = if (Build.VERSION.SDK_INT >= 33)
             *
             * E aqui ele pega a versao pelo nome
             * val filme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
             *
             * Lembrar que esse novo metodo é apenas para versoes de sdk superior a 33
             * Entao vamos usar o BUILD (BUILD é para saber os dados do celular que esta rodando nosso codigo)
             * Vamos testar a versão do SDK se for maior que 33, vamos utilizar o metodo novo, caso nao seja vamos utilziar o metodo antigo
             *
             * getSerializable ou getParcelable
             * Primeiro parametro é a chave que colocamos la na outra tela, vamos acessar ela
             *      Acessando a chave vamos ter os dados que estao dentro dela
             * Segundo parametro é a nossa classe DTO
             */

             bundle?.getParcelable("Filme", Filme::class.java)

        }else {
            /**
             * Caso a versao seja menor que a 33 vamos utilizar o metodo antigo
             */
            bundle?.getParcelable("Filme")
        }

        /**
         * Repara que filme ainda pode vir nulo, para isso vamos fazer uma verificação( ? )
         */
        nomeFilme.text = filme?.nome


    }
}