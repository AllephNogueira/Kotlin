package com.allephnogueira.a118encadeamentoetoast

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btnExecutar : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnExecutar = findViewById(R.id.btn_executar)

        btnExecutar.setOnClickListener {
            /**
             * Toast é para voce exibir uma mensagem na tela
             *
             * Tradução : make = Fazer || Text = Texto (Fazer texto)
             *
             *
             * Se você reparar o metodo makeText, espera receber 3 parametros
             *  Contexto, Texto, Duração.
             *
             *  Contexto = This, esta dizendo para abrir o proprio objeto.
             *
             *  Long = Duração longa  || Short = duração curta.
             *
             *  Agora apos configurar tudo precisamos exibir, e para isso usamos o SHOW()
             */
            Toast
                .makeText(this,
                    "Sucesso ao fazer algo",
                    Toast.LENGTH_LONG)
                .show()

            /**
             * Atençao aos atalhos, se a gente escrever toast em minusculo ele vai ser um atalho e vai criar o toast para mim
             */

            //Toast.makeText(, "", Toast.LENGTH_SHORT).show()
        }
    }
}