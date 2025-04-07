package com.allephnogueira.whatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.allephnogueira.whatsapp.databinding.ItemContatoBinding
import com.allephnogueira.whatsapp.databinding.ItemMensagensDestinatarioBinding
import com.allephnogueira.whatsapp.databinding.ItemMensagensRemetenteBinding
import com.allephnogueira.whatsapp.model.Mensagem
import com.allephnogueira.whatsapp.utils.Constantes
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class ConversasAdapter : Adapter<ViewHolder>() {

    private var listaMensagens = emptyList<Mensagem>()
    fun adicionarLista( lista: List<Mensagem>) {
        listaMensagens = lista
        notifyDataSetChanged()
    }


    /** Aqui estamos criando 2 classes, porque temos 2 itens diferentes no recyler view
     * Então cada classe vai cuidar do seu viewHolder
     */
    class MensagensRemetenteViewHolder(
        private val binding : ItemMensagensRemetenteBinding
    ) : ViewHolder(  binding.root  ) {

        fun bind(mensagem: Mensagem) {
            binding.textMensagemRemetente.text = mensagem.mensagem

        }

        companion object {
            fun inflarLayout(parent: ViewGroup) : MensagensRemetenteViewHolder{
                val inflate = LayoutInflater.from( parent.context )
                val itemView = ItemMensagensRemetenteBinding.inflate(
                    inflate, parent, false
                )
                return MensagensRemetenteViewHolder(itemView)
            }
        }
    }

    class MensagensDestinatarioViewHolder(
        private val binding : ItemMensagensDestinatarioBinding
    ) : ViewHolder(  binding.root  ) {

        fun bind(mensagem: Mensagem) {
            binding.textMensagemDestinatario.text = mensagem.mensagem
        }

        companion object {
            fun inflarLayout(parent: ViewGroup) : MensagensDestinatarioViewHolder{
                val inflate = LayoutInflater.from( parent.context )
                val itemView = ItemMensagensDestinatarioBinding.inflate(
                    inflate, parent, false
                )
                return MensagensDestinatarioViewHolder(itemView)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        /** Esse metodo ele executa antes do viewHolder
         *
         */

        val mensagem = listaMensagens[position]
        val idUsuarioLogado = FirebaseAuth.getInstance().currentUser?.uid.toString()
        return  if (idUsuarioLogado == mensagem.idUsuario) {
            // Se o id do usuario for igual ao que esta salvo la na mensagem, colocamos exemplo uma cor
            Constantes.TIPO_REMETENTE
        }else {
            // Se o id for diferente é sinal que é o destinatario que esta enviando a mensagem
            // Ai podemos fazer outra coisa.
            Constantes.TIPO_DESTINATARIO
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        /* Aqui é oque vamos retornar para a visualização */
        return if (viewType == Constantes.TIPO_REMETENTE) {
            MensagensRemetenteViewHolder.inflarLayout(parent)

        }else {
           MensagensDestinatarioViewHolder.inflarLayout(parent)
        }

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mensagem = listaMensagens[position]

        // Dessa forma aqui vamos verificar
        // is == é
        // Entao ele vai verificar qual o tipo do holder, se é destinatario ou remetente.
        when(holder) {
            is MensagensRemetenteViewHolder -> holder.bind(mensagem)
            is MensagensDestinatarioViewHolder -> holder.bind(mensagem)
        }



        // Aqui estamos convertendo o holder, para poder acessar o metodo bind dentro da classe.
//        val mensagensRemetenteViewHolder = holder as MensagensRemetenteViewHolder
//        mensagensRemetenteViewHolder.bind()


    }


    override fun getItemCount(): Int {
        return listaMensagens.size
    }
}