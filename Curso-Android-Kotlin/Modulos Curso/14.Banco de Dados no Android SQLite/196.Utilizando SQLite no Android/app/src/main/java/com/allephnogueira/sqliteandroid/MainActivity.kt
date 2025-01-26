package com.allephnogueira.sqliteandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.sqliteandroid.database.DatabaseHelper
import com.allephnogueira.sqliteandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val bancoDeDados by lazy {
        DatabaseHelper(this)
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding){

            btnSalvar.setOnClickListener {
                salvar()
            }

            btnListarProdutos.setOnClickListener {
                listar()
            }


        }


    }

    private fun listar() {
        TODO("Not yet implemented")
    }

    fun salvar() {

        val produtoDigitado = binding.editProduto.text.toString() // Capturando oque o usuario digitou.
        val comandoSQL = "INSERT INTO produtos (id_produto, titulo, descriacao) VALUES (null, '$produtoDigitado', 'Descrição...');"

        try {
            bancoDeDados.writableDatabase.execSQL(comandoSQL)

            Log.i("info_db", "Produto cadastrado com sucesso.")
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}