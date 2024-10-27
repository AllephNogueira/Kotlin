package com.allephnogueira.recyclerview

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.allephnogueira.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //*** configuração inicial
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // **** configuração do binding
        enableEdgeToEdge()
        setContentView(binding.root) //***
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecycleView()
    }

    private fun initRecycleView() {
        // layoutManager é a forma da gente exibir o recycleView
        // Vamos colocar para ele exibir um a baixo do outro.
        // Também temos o grid que podemos dividir os itens em coluna
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // Isso aqui é para ele gerar uma performance melhor no carregamento.
        binding.recyclerView.setHasFixedSize(true)
        // Aqui ele espera receber uma lista de String que foi oque passamos la.
        binding.recyclerView.adapter = Adapter(getList()) { nome -> // Isso aqui a gente chama de CallBack é um ouvinte.
            Toast.makeText(this, "$nome", Toast.LENGTH_SHORT).show() // Aqui vamos passar a string que recebemos
        }
    }


    private fun getList() = listOf ("Alleph", "Fernanda", "Crixus", "Amora", "Anastacia", "Calopsita Pai", "Calopsita Mae", "Zeca", "Bethoven"
    ,"Alleph", "Fernanda", "Crixus", "Amora", "Anastacia", "Calopsita Pai", "Calopsita Mae", "Zeca", "Bethoven")

}