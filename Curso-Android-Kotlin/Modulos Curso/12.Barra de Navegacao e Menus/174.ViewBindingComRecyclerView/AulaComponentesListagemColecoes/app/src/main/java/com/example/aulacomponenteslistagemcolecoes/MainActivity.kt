package com.example.aulacomponenteslistagemcolecoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.aulacomponenteslistagemcolecoes.teste.Produto
import com.example.aulacomponenteslistagemcolecoes.teste.Recursos


class Usuario {
    companion object {
        val nome: Int = 0
    }
}

class MainActivity : AppCompatActivity() {

    private lateinit var btnExecutar: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        val intent = Intent(this, MainActivity::class.java)

        val usuario: Usuario = UsuarioDoJamilton()
        intent.putExtra("usuario", usuario)

        startActivity(intent)*/

        btnExecutar = findViewById( R.id.btn_executar )
        textResultado = findViewById( R.id.text_resultado )

        /*btnExecutar.setOnClickListener { botao ->
            textResultado.text = "Jamilton Damasceno"
        }*/

    }

    fun cliqueBotao( view: View ){

        Toast.makeText(
            this,
            "Sucesso ao fazer algo",
            Toast.LENGTH_LONG
        ).show()


        textResultado.text = "Jamilton Damasceno"
    }

}