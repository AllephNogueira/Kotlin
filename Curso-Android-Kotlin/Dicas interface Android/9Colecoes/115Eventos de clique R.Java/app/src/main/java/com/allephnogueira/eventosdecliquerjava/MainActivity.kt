package com.allephnogueira.eventosdecliquerjava

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.eventosdecliquerjava.teste.Recursos


class MainActivity : AppCompatActivity() {

    private lateinit var btnExecutar : Button
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

        btnExecutar = findViewById(R.id.btn_executar)
        textResultado = findViewById(R.id.text_resultado)

        Recursos.id.text_numero


        btnExecutar.setOnClickListener {
            textResultado.text = "Alleph"
        }
    }

    /**
     * view é o nome que escolhemos, poderia colocar qualquer nome
     * View é o tipo
     *
     * entao fica :   view do tipo View
     */
    fun cliqueBotao(view: View) {
        view.isEnabled = false

        textResultado.text = "Botao clicado"
    }
}