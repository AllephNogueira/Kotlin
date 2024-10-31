package com.allephnogueira.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalcular : Button
    private lateinit var editPeso : EditText // Aqui estamos usando uma caixa de texto.
    private lateinit var editAltura : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        btnCalcular = findViewById(R.id.btnCalcular)
        editPeso = findViewById(R.id.edit_peso)
        editAltura = findViewById(R.id.edit_altura)


        btnCalcular.setOnClickListener {

            val intent = Intent(this, ResultadoActivity::class.java)

            /**
             * Quando o usuario clicar no botao vamos
             *
             * Recuperar  o texto digitado pelo usuario
             */
            val peso = editPeso.text.toString()
            val altura = editAltura.text.toString()

            /**
             * Agora imagina se o usuario nao digitar o peso e altura?
             * isNotEmpty = nao esta vazio.
             *
             */

            if (peso.isNotEmpty() && altura.isNotEmpty()) {

                /**
                 * Lembrar que o peso vem como string, mas queremos ele como Double para fazer o calculo
                 * Então estamos enviando ele como Double.
                 * Vamos enviar apenas se o usuario digitar os dados.
                 */
                intent.putExtra("peso", peso.toDouble())
                intent.putExtra("altura", altura.toDouble())
            }

            /**
             * Abrir uma nova Activity
             */

            startActivity(intent)
        }



    }

}