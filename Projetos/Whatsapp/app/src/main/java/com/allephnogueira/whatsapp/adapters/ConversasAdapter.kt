package com.allephnogueira.whatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.allephnogueira.whatsapp.databinding.ItemContatoBinding
import com.allephnogueira.whatsapp.databinding.ItemConversasBinding
import com.allephnogueira.whatsapp.model.Conversa
import com.squareup.picasso.Picasso

class ConversasAdapter(
    private val onClick: (Conversa) -> Unit
) : Adapter<ConversasAdapter.ConversasViewHolder>() {


    private var listaConversas = emptyList<Conversa>()

    fun adicionarLista(lista: List<Conversa>) {
        listaConversas = lista
        notifyDataSetChanged()
    }

    inner class ConversasViewHolder(
        private val binding: ItemConversasBinding
    ) : ViewHolder(binding.root) {

        fun bind(conversa: Conversa) {

            binding.textConversaNome.text = conversa.nome
            binding.textConversaMensagem.text = conversa.ultimaMensagem
//            binding.textData.text = conversa.data.toString()
            Picasso.get()
                .load(conversa.foto)
                .into(binding.imageConversaFoto)


            // evento de clique
            // Aqui é quando o usuario clica em contato, e vamos abrir uma nova activity com a tela de mensagem para o contato clicado
            // E repara que estamos passando um usuario como referencia
            // Dentro desse usuario, ja temos todos os dados.
            binding.clItemConversa.setOnClickListener {
                onClick(conversa)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversasViewHolder {
        val inflater = LayoutInflater.from( parent.context )
        val itemView = ItemConversasBinding.inflate(
            inflater, parent, false
        )

        return ConversasViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ConversasViewHolder, position: Int) {
        val conversas = listaConversas[position]
        holder.bind(conversas)
    }

    override fun getItemCount(): Int {
        return listaConversas.size
    }
}