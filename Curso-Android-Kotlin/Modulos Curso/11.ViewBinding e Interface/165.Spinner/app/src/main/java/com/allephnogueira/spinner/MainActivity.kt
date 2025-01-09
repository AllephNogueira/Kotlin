package com.allephnogueira.spinner

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        spinnerExibicao() // Exibir meu spinner

        with(binding){
            btnEnviar.setOnClickListener {
                spinnerSelecionarItem() // Metodo para capturar o item selecionado
            }
        }



    }


    private fun spinnerExibicao() {


        val categorias = listOf("Selecione a bandeira do Posto", "BR", "Ipiranga", "Shell", "Outros")


        binding.spinnerCategorias.adapter = ArrayAdapter<String>(
            this, // Contexto
            android.R.layout.simple_spinner_dropdown_item, // Qual layout vamos utilizar
            categorias // Aqui vamos passar as nossas categorias

        )


    }


    private fun spinnerSelecionarItem() {
        val itemSelecionado = binding.spinnerCategorias.selectedItem // Retorna o nome do item selecionado
        val itemPosicao = binding.spinnerCategorias.selectedItemPosition // Retorna a posição do item

        if (itemPosicao!=0){
            binding.textResultado.text = "Posto selecionado: $itemSelecionado - Posição: $itemPosicao"
        }else {
            Toast.makeText(this, "Selecione um item", Toast.LENGTH_SHORT).show()
        }


    }

}