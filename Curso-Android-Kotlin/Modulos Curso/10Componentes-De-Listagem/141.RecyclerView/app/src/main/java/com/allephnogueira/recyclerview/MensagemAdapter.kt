package com.allephnogueira.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

// Aqui precisamos falar qual a classe
// Precisamos falar qual classe vai manipular nosso XML
// No Construtor é onde vamos passar nossa lista ou os itens que esta vindo.
class MensagemAdapter(private val minhaLista : List<Mensagem>) : Adapter<MensagemAdapter.MensagemViewHolder>() {


    // Nossa classe precisa herdar de ViewHolder
    // E o viewHolder exige que voce passe qual itemView
    inner class MensagemViewHolder(val itemView : View) : ViewHolder(itemView) {
        // O ItemView é onde criamos nossoXML

         val imagePerfil : ImageView = itemView.findViewById(R.id.image_perfil)
         val textNome : TextView = itemView.findViewById(R.id.text_nome)
         val textUltimaMensagem : TextView = itemView.findViewById(R.id.text_ultimaMensagem)
         val textHorario : TextView = itemView.findViewById(R.id.text_hora)

    }


    // Agora sim, cada metodo desse que foi implementado e responsavel por cada uma das tarefas.

    // Ao criar o view holder. - Criar a visualização
    // Aqui onde vamos usar o XML para construir a visualização
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {
        // Agora aqui vamos converter o XML para um Objeto do tipo View
        // para ele criar a visualização

        val layoutInflater = LayoutInflater.from(
            parent.context
        )

        val itemView = layoutInflater.inflate(
            R.layout.item_lista,  // Meu XML
            parent,
            false // Onde queremos anexar
        )

        // Agora aqui é onde vamos construir
        return MensagemViewHolder(itemView)
    }



    // Ao vincular o view Holder
    // Aqui é onde vamos vincular os dados em cada view holder
    // Onde vamos transformar os dados um a um
    // Aqui é onde vamos passar os dados para a activity
    override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {
        val mensagem = minhaLista[position]

        holder.textNome.text = mensagem.nome
        holder.textUltimaMensagem.text = mensagem.ultimaMensagem
        holder.textHorario.text = mensagem.horario


    }


    // Recupera a quantidade de itens.
    // Conta a quantidade de itens
    override fun getItemCount(): Int {
        return minhaLista.size
    }
}