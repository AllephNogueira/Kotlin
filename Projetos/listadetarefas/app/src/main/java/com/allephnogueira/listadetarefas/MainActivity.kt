package com.allephnogueira.listadetarefas

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.allephnogueira.listadetarefas.adapter.TarefaAdapter
import com.allephnogueira.listadetarefas.database.TarefaDAO
import com.allephnogueira.listadetarefas.databinding.ActivityMainBinding
import com.allephnogueira.listadetarefas.model.Tarefa

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var listaDeTarefas = emptyList<Tarefa>() // Criando uma lista vazia
    private var tarefaAdapter : TarefaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /** Mudando a cor da barra de status */
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.primaria, theme)


        /** Abrindo o layout quando o usuario clicar no botao + */
        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarTarefaActivity::class.java)
            startActivity(intent)
        }

        // Construir o recyclerView
        tarefaAdapter = TarefaAdapter()

        binding.rvTarefas.adapter = tarefaAdapter

        binding.rvTarefas.layoutManager = LinearLayoutManager(this)


    }

    private fun atualizarListaTarefas(){
        val tarefaDAO = TarefaDAO(this)
        listaDeTarefas = tarefaDAO.listarTarefa()
        tarefaAdapter?.adicionarLista(listaDeTarefas)
    }

    override fun onStart() {
        /** Lembra que o onCreate ele executa apenas uma vez
         * Então criamos o onStart que sempre quando o usuario sai e volta ele executa novamente, assim sempre atualizando a lista de tarefas
         * Para relembrar o onStart ele é chamado toda vez que o usuario sai da tela
         * onStart é o metodo que geralmente a gente usa para recuperar dados.
         */
        super.onStart()
        // Toda vez que a gente sair e entrar na pagina vamos chamar esse metodo passando os novos dados e o RecyclerView vai atualizar.
        atualizarListaTarefas()



    }
}