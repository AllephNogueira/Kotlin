package com.allephnogueira.recyclerview2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterAgenda(private val contatos: List<Contato>) : Adapter <AdapterAgenda.AgendaViewHolder>() {

    inner class AgendaViewHolder (val itemView : View) : ViewHolder(itemView) {
        /**
         * Aqui vamos definir os dados que vamos alterar, no nosso recyclerView
         */

        val imagemPerfil : ImageView = itemView.findViewById(R.id.image_perfil)
        val nomeUsuario : TextView = itemView.findViewById(R.id.text_nome)
        val telefoneUsuario : TextView = itemView.findViewById(R.id.text_telefone)

    }

    override fun getItemCount(): Int {
        /**
         * Aqui é onde vamos definir a quantidade de itens, para isso vamos utilizar o size, para ele contar automaticamente.
         */
        return contatos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
        /**
         * Aqui é onde vamos criar nossa view holder
         * view = visualização
         * holder =
         * Vamos pegar XML e vamos converter para uma VIEW
         * Vamos tambem definir qual vai ser o layout a ser utilizado
         */

        val layoutInflate = LayoutInflater.from(parent.context)
        val itemView = layoutInflate.inflate(
            R.layout.item_agenda,
            parent,
            false
        )

        return AgendaViewHolder(itemView)

    }



    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        /**
         * Aqui é onde vamos tratar os dados, como vai ser exibido.
         * Vinculando os dados.
         */

        val contato = contatos[position]

        holder.nomeUsuario.text = contato.nome
        holder.telefoneUsuario.text = contato.telefone


    }

}
