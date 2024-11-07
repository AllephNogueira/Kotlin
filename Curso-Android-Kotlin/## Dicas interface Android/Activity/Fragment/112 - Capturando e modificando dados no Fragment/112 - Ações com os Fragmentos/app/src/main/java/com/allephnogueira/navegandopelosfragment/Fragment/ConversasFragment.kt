package com.allephnogueira.navegandopelosfragment.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.allephnogueira.navegandopelosfragment.R

class ConversasFragment : Fragment() {


    /**
     * Adicionando as variaveis que vão receber os dados
     */
    private lateinit var editNome : EditText
    private lateinit var buttonExecutar : Button
    private lateinit var textNome : TextView



    override fun onAttach(context: Context) {
        Log.i("ciclo_vida", "onAttach iniciado.")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo_vida", "onCreate - Onde vamos criar nosso objeto, onde vamos colocar nossos codigos.")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("ciclo_vida", "onCreateView - Onde vamos criar a visualização do nosso fragmento.")
        val minhaView = inflater.inflate(
            R.layout.fragment_conversas,
            container,
            false
        )

        /**
         * Recuperando os objetos da interface com o minhaView: View
         * Como utilizar o findViewByID aqui?
         * Diferente da Activity, la dentro do onCreate tem o metodo setContentView(R.layout.activity_main) que deixa disponivel o find, mas aqui não tem.
         * Então vamos utilizar nossa variavel minhaView que recebe os objetos do tipo View
         *
         * ATENÇÃO = Se voce reparar minhaView recebe o fragment_conversas, o seja ali dentro vai esta todos os itens do layout
         */

        editNome = minhaView.findViewById(R.id.editNome)
        buttonExecutar = minhaView.findViewById(R.id.buttonExecutar)
        textNome = minhaView.findViewById(R.id.textNome)


        /**
         * Configurando o botão quando o usuario clicar dentro dele.
         */
        buttonExecutar.setOnClickListener {
            /**
             * Recuperando o nome digitado pelo usuario
             * Atenção: Repara que pegamos o objeto editNome:
             * Pegamos o atributo dele, que no caso é o texto que foi digitado nele, poderiamos pegar ate o tamanho da fonte.
             * E convertemos esse atributo que esta dentro do text para String.
             */

            val nome = editNome.text.toString()

            /**
             * Exibindo o nome que o usuario digitou.
             */
            textNome.text = nome
        }

        return minhaView
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("ciclo_vida", "onViewCreate - Logo apos a tela ser criada, aqui quando a tela for criada, podemos fazer algo.")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        Log.i("ciclo_vida", "onStart - quando queremos carregar alguns dados.")
            super.onStart()
    }

    override fun onResume() {
        Log.i("ciclo_vida", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i("ciclo_vida", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i("ciclo_vida", "onStop - vamos parar o fragmento")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.i("ciclo_vida", "onDestroyView - Vamos destruir o fragmento")
        super.onDestroyView()
    }

    override fun onDetach() {
        Log.i("ciclo_vida", "onDetach - Vai desacoplar o fragmento da activity " +
                "Esse metodo também é usado quando usamos o remove fragmento.")
        super.onDetach()
    }





}