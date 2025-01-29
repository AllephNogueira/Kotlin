package com.allephnogueira.listadetarefas.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.allephnogueira.listadetarefas.model.Tarefa

class TarefaDAO(context: Context) : ITarefasDAO {

    private val escrita = DatabaseHelper(context).writableDatabase // Fazer escrita (UPDATE, INSERT, DELETE)
    private val leitura = DatabaseHelper(context).readableDatabase // leitura (SELECT)


    override fun salvar(tarefa: Tarefa): Boolean {

        val valores = ContentValues()
        valores.put("${DatabaseHelper.COLUNA_DESCRICAO}", tarefa.descricao)


        try {
            escrita.insert(
                DatabaseHelper.NOME_TABELA_TAREFAS,
                null,
                valores
            )
            Log.i("info_db", "Sucesso ao salvar tarefa!")
        }catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_db", "Erro ao salvar tarefa!")
            return false
        }

        return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        TODO("Not yet implemented")
    }

    override fun remover(idTarefa: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun listarTarefa(): List<Tarefa> {
        TODO("Not yet implemented")
    }
}