package com.allephnogueira.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvLista : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listaConvidados = listOf(
            Mensagem("Alleph", "Tudo bem?", "10:45"),
            Mensagem("Fernanda", "Comprar arroz", "10:50"),
            Mensagem("Gabriel", "Comprar cerveja", "12:00"),
            Mensagem("Jane", "Fura a parede pra mim?", "17:30"),

        )

        rvLista = findViewById(R.id.recycler_view)

        rvLista.adapter = MensagemAdapter(listaConvidados)
        rvLista.layoutManager = LinearLayoutManager(this) // Exibir um a baixo do outro.
    }
}