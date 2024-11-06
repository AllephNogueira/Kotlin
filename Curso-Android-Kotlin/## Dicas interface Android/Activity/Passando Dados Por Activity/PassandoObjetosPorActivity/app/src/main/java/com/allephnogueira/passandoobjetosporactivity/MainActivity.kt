package com.allephnogueira.passandoobjetosporactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Criamos uma variavel
    // Adicionamos o tipo dela
    lateinit var btnDetalhesFilme : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Criamos uma variavel
        // Adicionamos dentro da variavel a classe que ela vai chamar
        val intent = Intent(this, DetalhesActivity::class.java)

        // Passamos para o botao onde esta o item que ele vai alterar
        // Passamos para o botao a sua referencia
        btnDetalhesFilme = findViewById(R.id.button_DetalhesFilme)

        // Criamos um evento de click, quando o usuario clicar vai chamar a variavel que esta com a classe dentro.
        btnDetalhesFilme.setOnClickListener { startActivity(intent) }

        // Agora vamos usar nosso Data Class, passando os dados.
        val filme = Filme (
            "Harry Potter",
            "Harry Potter é um bruxo que veio para lutar com um bruxo das trevas",
            9.5,
            "Alleph",
            "Sony"
        )

        // Agora vamos passar os dados
        // Aqui vamos passar a referencia como Filme e vamos passar os dados que são do tipo Filme

        intent.putExtra("Filme", filme)


    }
}