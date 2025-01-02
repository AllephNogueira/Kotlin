package com.allephnogueira.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var listViewUsuarios : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val listaUsuario = listOf("Alleph", "Fernanda", "Crixus", "Amora", "Anastacia", "Bethoven", "Passarinhos")

        listViewUsuarios = findViewById(R.id.list_usuarios)

        // Dessa forma vamos utilizar um adapter pronto, mas podemos criar o nosso também
//        listViewUsuarios.adapter = ArrayAdapter(
//            this, // Contexto
//            android.R.layout.meu_list_view, // Qual layout vamos utilizar
//            android.R.id.text_nome, // Esse text1 é o id dentro do layout, onde vamos substituir pela nossa lista
//            listaUsuario // Por ultimo vamos passar nossa lista de usuarios.
//        )

        listViewUsuarios.adapter = ArrayAdapter(
            this,
            android.R.layout.activity_list_item,
            android.R.id.text1,
            listaUsuario
        )


    }


}