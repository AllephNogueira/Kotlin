package com.example.aulacomponenteslistagemcolecoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.aulacomponenteslistagemcolecoes.databinding.ActivityRecyclerviewBinding

class RecyclerviewActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRecyclerviewBinding.inflate( layoutInflater )
    }

    private lateinit var rvLista: RecyclerView
    private lateinit var btnClique: Button
    private lateinit var mensagemAdapter: MensagemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        val lista = mutableListOf(
            Mensagem("jamilton", "Olá, tudo bem?", "10:45"),
            Mensagem("ana", "Lorem ipsum dolorem sit amet, dolor sit amet ipsum dolorem, dolor sit amet ipsum doloremLorem ipsum dolorem sit amet, dolor sit amet ipsum dolorem, dolor sit amet ipsum dolorem", "00:45"),
            Mensagem("maria", "Não acredito...", "06:03"),
            Mensagem("pedro", "Futebol hoje?", "15:32"),
        )

        //rvLista = findViewById(R.id.rv_lista)
        //btnClique = findViewById(R.id.btn_clique)

        //tipo: MensagemAdapter, Adapter
        mensagemAdapter = MensagemAdapter { nome ->

            Toast.makeText(this, "Olá $nome", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ListViewActivity::class.java)
            intent.putExtra("nome", nome)

            startActivity(
                intent
            )
        }

        mensagemAdapter.atualizarListaDados(
            lista
        )
        binding.rvLista.adapter = mensagemAdapter

        //LinearLayoutManager (XML e Código)
        binding.rvLista.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        binding.btnClique.setOnClickListener {

            mensagemAdapter.executarOperacao()
            /*
            lista.add(
                Mensagem("Nova Jamilton", "teste", "17:12")
            )
            mensagemAdapter.atualizarListaDados( lista )
            */

        }

        /*rvLista.addItemDecoration(
            DividerItemDecoration(
                this,
                RecyclerView.VERTICAL
            )
        )*/

        /*rvLista.layoutManager = GridLayoutManager(
            this,
            2
        )*/
        /*rvLista.layoutManager = StaggeredGridLayoutManager(
            2,
            RecyclerView.VERTICAL
        )*/

    }
}