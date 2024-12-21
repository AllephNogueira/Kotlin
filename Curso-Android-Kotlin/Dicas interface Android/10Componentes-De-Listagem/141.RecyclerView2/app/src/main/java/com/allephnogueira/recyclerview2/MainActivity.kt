package com.allephnogueira.recyclerview2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var rvAgenda : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contatosAgenda = listOf<Contato>(
            Contato("Alleph", "21988180461"),
            Contato("Fernanda", "21980301853"),
            Contato("Alleph2", "21975575694"),
            Contato("Teste", "11123456789"),
            Contato("Teste", "11123456789"),
            Contato("Teste", "11123456789"),
            Contato("Teste", "11123456789"),
            Contato("Teste", "11123456789")
        )

        rvAgenda = findViewById(R.id.recyclerView)
        rvAgenda.adapter = AdapterAgenda(contatosAgenda)
        rvAgenda.layoutManager = LinearLayoutManager(this)

    }
}