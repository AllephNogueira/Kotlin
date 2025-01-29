package com.allephnogueira.listadetarefas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.listadetarefas.database.TarefaDAO
import com.allephnogueira.listadetarefas.databinding.ActivityAdicionarTarefaBinding
import com.allephnogueira.listadetarefas.databinding.ActivityMainBinding
import com.allephnogueira.listadetarefas.model.Tarefa

class AdicionarTarefaActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAdicionarTarefaBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.btnSalvar.setOnClickListener {
            val nomeTarefa = binding.editTarefa.text.toString()
            salvarTarefa(nomeTarefa)

        }

    }

    private fun salvarTarefa(nomeTarefa: String) {

        if (nomeTarefa.isNotEmpty()) {
            val tarefaDAO = TarefaDAO(this)

            val tarefa = Tarefa(
                -1,
                nomeTarefa,
                "DEFAULT"
            )

            tarefaDAO.salvar(tarefa)
            Toast.makeText(this, "Tarefa salva com sucesso!", Toast.LENGTH_LONG).show()
            finish()
        }else {
            Toast.makeText(this, "Adicione uma tarefa", Toast.LENGTH_SHORT).show()
        }
    }
}