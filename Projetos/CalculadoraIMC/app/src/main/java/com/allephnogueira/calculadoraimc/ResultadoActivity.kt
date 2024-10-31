package com.allephnogueira.calculadoraimc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoActivity : AppCompatActivity() {
    /**
     * Agora aqui vamos capturar esses valores que vinheram da outra activity e fazer o calculo
     */

    private lateinit var textPeso : TextView
    private lateinit var textAltura : TextView
    private lateinit var textResultado : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textPeso = findViewById(R.id.text_peso)
        textAltura = findViewById(R.id.text_altura)
        textResultado = findViewById(R.id.text_resultado)

        /**
         * Aqui vamos recuperar os valores que vem da outra activity
         */

        val bundle = intent.extras
        if (bundle != null){
            val peso = bundle.getDouble("peso")
            val altura = bundle.getDouble("altura")

            /**
             * Agora vamos exibir dentro das textView
             */
            textPeso.text = ("Peso informado: $peso kg")
            textAltura.text = ("Altura informada: $altura m")


            /**
             * Formula do calculo de IMC
             */
            val imc = peso / (altura * altura)

            /**
             * Aqui vamos adicionar dentro da variavel o resultado o teste
             * Repara que colocamos o if diretamente dentro da variavel, para evitar de ficar duplicando codigo.
             */

            textResultado.text = if(imc < 18.5 ) {
                "Seu IMC é: Baixo"
            }else if (imc in 18.5..24.9 ) {
                "Seu IMC é: Normal"
            }else if(imc in 25.0..29.9) {
                "Seu IMC é: Sobrepeso"
            }else {
                "Seu IMC é: Obeso"
            }

        }

    }
}