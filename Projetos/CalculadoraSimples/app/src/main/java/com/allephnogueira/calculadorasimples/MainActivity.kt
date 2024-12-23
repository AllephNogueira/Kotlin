package com.allephnogueira.calculadorasimples

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var numero1 : EditText
    lateinit var numero2 : EditText
    lateinit var calcular: Button
    lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        numero1 = findViewById(R.id.textNumero1)
        numero2 = findViewById(R.id.textNumero2)
        calcular = findViewById(R.id.buttonCalcular)
        resultado = findViewById(R.id.textResultado)




        calcular.setOnClickListener {
            val numero1Value = numero1.text.toString().toIntOrNull() ?: 0
            val numero2Value = numero2.text.toString().toIntOrNull() ?: 0
            val calculoFinal: Int = numero1Value + numero2Value

            Log.d("Calculo", "Resultado da operação foi: $calculoFinal")
            resultado.text = "Calculo é: $calculoFinal"
        }

    }
}