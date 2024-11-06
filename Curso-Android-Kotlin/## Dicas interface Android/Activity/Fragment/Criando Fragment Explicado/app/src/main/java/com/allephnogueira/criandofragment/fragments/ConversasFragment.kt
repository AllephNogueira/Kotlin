package com.allephnogueira.criandofragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.allephnogueira.criandofragment.R

class ConversasFragment : Fragment() {


    override fun onCreateView(
        /**
         * Inflate = inflar seu layout = construir seu layout
         */
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * Esse metodo é onde vamos inflar nossa informação na tela
         * Inflar = jogar nossa informação na tela
         * Ele espera receber 3 parametros
         *      Nosso layout
         *
         * Nosso view Group
         *      Vamos passar o proprio container, porque ja recebemos ele ali (l-18)
         *      Oque é esse container? é nosso componente o fragmentContainer
         *
         * o Ultimo é o attchToRoot = anexar o elemento raiz
         *      false
         *      Imagina que tem varios container(Fragmentos) na tela, podemos escolher de forma manual onde queremos colocar ele, mas nao queremos isso
         *      Queremos deixar que ele coloque automatico, ate porque só temos 1 Container na tela.
         *
         *      Se eu colocasse como TRUE
         *      Seria eu mesmo que iria anexar de forma manual
         */


//        return inflater.inflate(
//            R.layout.fragment_conversas,
//            container,
//            false
//
//        )

        /**
         * Podemos fazer dessa forma também, porque assim vamos ter os itens que estão dentro do  nosso fragment
         */

        val view = inflater.inflate(
            R.layout.fragment_conversas,
            container,
            false

        )

        view.findViewById<TextView>(R.id.text_Conversa).text = "Alterando o texto"

        return view

        /**
         * Agora imagina que queremos acessar o conversa
         */


    }

}