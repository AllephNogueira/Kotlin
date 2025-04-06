package com.allephnogueira.whatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.allephnogueira.whatsapp.databinding.ItemContatoBinding
import com.allephnogueira.whatsapp.model.Usuario
import com.squareup.picasso.Picasso

class ContatosAdapter(
    private val onClick: (Usuario) -> Unit
) : Adapter<ContatosAdapter.ContatosViewHolder>() {

    private var listaContatos = emptyList<Usuario>()
    fun adicionarLista(lista: List<Usuario>) {
        listaContatos = lista
        notifyDataSetChanged()
    }

    inner class ContatosViewHolder(
        private val binding: ItemContatoBinding
    ) : ViewHolder(binding.root) {

        fun bind(usuario: Usuario) {

            binding.textContatoNome.text = usuario.nome
            Picasso.get()
                .load(usuario.foto)
                .into(binding.imageContatoFoto)


            // evento de clique
            // Aqui é quando o usuario clica em contato, e vamos abrir uma nova activity com a tela de mensagem para o contato clicado
            // E repara que estamos passando um usuario como referencia
            // Dentro desse usuario, ja temos todos os dados.
            binding.clItemContato.setOnClickListener{
                onClick(usuario)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatosViewHolder {
        val inflater = LayoutInflater.from( parent.context )
        val itemView = ItemContatoBinding.inflate(
            inflater, parent, false
        )

        return ContatosViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ContatosViewHolder, position: Int) {
        val usuario = listaContatos[position]
        holder.bind( usuario )
    }


    override fun getItemCount(): Int {
        return listaContatos.size
    }
}