package com.allephnogueira.recyclerview2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterAgenda(private val clique: (String) -> Unit)
    : Adapter <AdapterAgenda.AgendaViewHolder>() {

    private var listaContatos = mutableListOf<Contato>() // 1 Removemos a lista do construtor e colocamos ela como atributo.
    // Usamos uma lista mutable = mudavel - onde podemos atualizar os dados depois que ela foi criada.
    
    fun atualizarListaDeDados (lista : MutableList<Contato>) {// / 2 Esse metodo vamos utilizar para atualizar os dados.
        // Vamos chamar o metodo, passando uma lista; que pode ser uma nova lista
        // O lista de contato recebe o metodo add todos, e como parametro dele vamos passar a nova lista
        // Repara que aqui em baixo ja temos uma lista, só que quando atualizamos os dados, vamos substituir tudo que esta dentro dessa lista.
        listaContatos = lista


        notifyDataSetChanged()// 6 Passando o metodo para o adapter atualizar a lista novamente
        // Com esse metodo o recyclerView entende que ele precisa recarregar os dados.
    }

    inner class AgendaViewHolder (val itemView : View) : ViewHolder(itemView) {
        /**
         * Aqui vamos definir os dados que vamos alterar, no nosso recyclerView
         */

        val imagemPerfil : ImageView = itemView.findViewById(R.id.imagemPerfil)
        val nomeUsuario : TextView = itemView.findViewById(R.id.text_card_nome)
        val telefoneUsuario : TextView = itemView.findViewById(R.id.text_card_ultimaMensagem)
        val cardView : CardView = itemView.findViewById(R.id.card_view)

        fun bind(contato : Contato) { // Bind = conectar
            nomeUsuario.text = contato.nome
            telefoneUsuario.text = contato.telefone


            cardView.setOnClickListener {
                clique(contato.nome)
            }
        }


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
            R.layout.item_cardview,
            parent,
            false
        )


//        val itemView = layoutInflate.inflate(
//            R.layout.item_agenda,
//            parent,
//            false
//        )

        return AgendaViewHolder(itemView)

    }



    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        /**
         * Aqui é onde vamos tratar os dados, como vai ser exibido.
         * Vinculando os dados.
         */

        val contato = listaContatos[position]

        holder.nomeUsuario.text = contato.nome
        holder.telefoneUsuario.text = contato.telefone

        /** Aqui cada item é executado de forma separada de acordo com sua posição, aqui vamos pegar essa posição
         * Para fazer aqui dentro vamos precisar do contexto e para isso devemos fazer dessa forma
         */

        holder.bind(contato)


    }


    override fun getItemCount(): Int {
        /**
         * Aqui é onde vamos definir a quantidade de itens, para isso vamos utilizar o size, para ele contar automaticamente.
         */
        return listaContatos.size
    }

}
