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


        //notifyDataSetChanged()// 6 Passando o metodo para o adapter atualizar a lista novamente
        // Com esse metodo o recyclerView entende que ele precisa recarregar os dados.
        // Com esse metodo o recyclerView vai atualizar a LISTA TODA DE ITENS novamente

    }

    fun executarOperacao (contato: String, telefone: String) {

        /**
         * Aula NOVA 149.1
         * Com esse metodo vamos atualizar somente oque chegou novo.
         *
         * 3 Aqui vamos adicionar um novo contato
         */

        //listaContatos.add(Contato(contato, telefone))

        /**
         * Agora precisamos atualizar esse novo contato.
         * Atençao: SEMPRE QUE FOR POSSIVEL, DEVEMOS ATUALIZAR SOMENTE OS NOVOS ITENS
         * IMAGINA QUE TEMOS UMA LISTA COM 500 ITENS, NAO PODEMOS FICAR ATUALIZANDO ELA A TODO TEMPO.
         *
         * Aqui é onde vamos exibir, em qual posição.
         *
         * Imagina que nao sabemos qual a posição da nossa lista, entao vamos pegar a lista e pegar a ultima posição dela
         */

        //notifyItemInserted(4)

        // Exemplo da lista
        // posiçao 0 Alleph
        // posiçao 1 Fernanda
        // posiçao 2 Alleph2
        // posicap 3 ------

        //notifyItemInserted(listaContatos.size) // O size vai contar a quantidade de itens, temos uma lista com 3 itens, então ele vai adicionar na casa 3
        // Repara que o size ele nao conta de 0 ate 2 que é = 3 itens
        // Ele conta de 1 ate 3 - Sendo assim o 3 na lista vai esta vago.


//        /**
//         * Agora vamos imaginar que queremos adicionar um item em uma posição especifica
//         *
//         */
//
//        listaContatos.add(
//            0, // Aqui vamos passar onde queremos que o item fique, em qual casa ele vai ficar!
//            Contato(contato, telefone))
//
//        /**
//         * Nesse caso ai estamos adicionando um novo contato na posicao 0
//         * E o contato anterior que estava na posicao 0?
//         * O list ele vai reajusta passando oque estava no 0 para 1
//         *
//         * Mas para atualizar no adapter também vamos precisar de outro parametro para isso.
//         */
//
//        notifyItemInserted(0) // Aqui devemos por o parametro que queremos na posição


        /**
         * Esse metodo aqui de baixo vamos dizer que chegou um total de 10 itens e vamos dizer em qual posiação ele deve entrar.
         *
         * Aqui por exemplo esta chegando 2 novos contatos, ou seja la quantos vai vir.
         *
         */

        listaContatos.add(Contato(contato, telefone))


//        p0 - Contato("Alleph", "21988180461"),
//        p1 - Contato("Fernanda", "21980301853"),
//        p2 - Contato("Alleph2", "21975575694"),



        notifyItemRangeChanged(listaContatos.size, 2 ) // Posição inicial 3 / quantidade de itens. 2



    }


    fun notificandoItemAtualizadoOuRemovido () {

        /**
         * Atualizando somente um item na posição 0
         */

        //listaContatos[0] = Contato("Alleph Novo TIM", "21988180461") // Aqui estamos atualizando o item 0
        //notifyItemChanged(0) // Atualizando somente a posicao 0


        /**
         * Agora vamos imaginar que uma quantidade de itens sofreu alterações, então devemos passar a posição inicial e a quantidade de itens que atualizou
         */

//        listaContatos[0] = Contato("Alleph Novo TIM", "21988180461") // Aqui estamos atualizando o item 0
//        listaContatos[1] = Contato("Alleph Antigo TIM", "21975575694")
//        notifyItemRangeChanged(0, 2) // Posição inicial - quantidade de itens


        /**
         * Remover um item da lista
         */
//
//        listaContatos.removeAt(1)
//        notifyItemRemoved(1)


        /**
         * Remover com o ranger, no caso vamos remover 2 itens da lista
         */

        listaContatos.removeAt(0)
        listaContatos.removeAt(1)
        notifyItemRangeRemoved(1,2)
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
