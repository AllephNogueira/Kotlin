package com.allephnogueira.alcoolougasolina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool : TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textInputGasolina : TextInputLayout
    private lateinit var editGasolina: TextInputEditText

    private lateinit var btnCalcular : Button
    private lateinit var textResultado : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializarOsComponentesDeInterface()
        btnCalcular.setOnClickListener{
            calcularOMelhorPreco()
        }
    }

    private fun calcularOMelhorPreco() {
        val precoAlcool = editAlcool.text.toString()
        val precoGasolina = editGasolina.text.toString()


        val resultadoValidacao = validarCampos(precoAlcool, precoGasolina)
        if (resultadoValidacao) {
            val precoAlcoolNumero = precoAlcool.toDouble()
            val precoGasolinaNumero = precoGasolina.toDouble()
            val resultado = precoAlcoolNumero / precoGasolinaNumero

            if (resultado >= 0.7) {
                textResultado.text = "Melhor utilizar gasolina"
            }else {
                textResultado.text = "Melhor utilizar o alcool"
            }
        }
    }

    private fun validarCampos(precoAlcool: String, precoGasolina: String): Boolean {

        textInputAlcool.error = null
        textInputGasolina.error = null

        if (precoAlcool.isEmpty()){
            textInputAlcool.error = "Digite o preço do álcool"
            return false
        }else if (precoGasolina.isEmpty()){
            textInputGasolina.error = "Digite o preço da gasolina"
            return false
        }


        return true
    }

    private fun inicializarOsComponentesDeInterface() {
        textInputAlcool = findViewById(R.id.text_input_alcool)
        editAlcool = findViewById(R.id.edit_alcool)

        textInputGasolina = findViewById(R.id.text_input_gasolina)
        editGasolina = findViewById(R.id.edit_gasolina)

        btnCalcular = findViewById(R.id.btn_calcular)
        textResultado = findViewById(R.id.text_resultado)


    }
}