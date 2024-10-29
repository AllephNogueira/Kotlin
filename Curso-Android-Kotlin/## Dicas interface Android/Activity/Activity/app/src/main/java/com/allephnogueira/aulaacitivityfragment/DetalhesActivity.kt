package com.allephnogueira.aulaacitivityfragment

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalhesActivity : AppCompatActivity() {

    lateinit var btnFechar : Button
    lateinit var textFilme : TextView // Onde vamos passar o nome do filme


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
        textFilme = findViewById(R.id.textFilme) // Onde estamos passando a referencia do objeto filme

        /** Bundle é um objeto do android que significa (PACOTE - EMBRULHAR)
         Essa classe é utilizada para armazenar valores.
         Esse objeto é uma maneira do android empacotar as informações e passar de uma tela para outra. */

        /** intent = Quando passamos uma intent(intenção) na tela anterior o proprio android studio ja passou esses dados e deixou guardado
         * so vamos precisar acessar eles agora
         *
         * extras = lembra que usamos o extra também? os dados estao dentro de inputExtra, vamos acessar eles também.
         *
         * agora o bundle tem todos os metodos passados da tela anterior pra ca.
         */
        val bundle = intent.extras
        /**
         * Agora vamos recuperar o filme, ja passamos ele para dentro do bundle
         * Repara que estamos utilizando o getString porque o dado que passamos é do tipo String, mas poderia ser outro tipo tambem
         * como getInt para numeros inteiros.
         * vamos passar outros parametros para teste
         *
         * primeiro parametro é o nome que definimos la (key)
         *
         * Atenção: Devemos utilizar uma chamada segura (?) porque o objeto que esta vindo pode ser nulo.
         * bundle?.getString("filme")
         * Mas podemos também verificar se é diferente de nulo
         *
         * if (bundle != null) {
         *             /** Os dados podem vir nulo, por isso vamos verificar antes. */
         *             bundle.getString("filme")
         *         }
         *
         */
        if (bundle != null) {
            /** Os dados podem vir nulo, por isso vamos verificar antes. */
            val filme = bundle.getString("filme")
            textFilme.text = filme
        }




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