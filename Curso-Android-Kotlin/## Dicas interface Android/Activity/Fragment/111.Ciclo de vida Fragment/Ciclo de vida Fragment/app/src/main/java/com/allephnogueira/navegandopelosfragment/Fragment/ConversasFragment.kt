package com.allephnogueira.navegandopelosfragment.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.allephnogueira.navegandopelosfragment.R

class ConversasFragment : Fragment() {

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

        minhaView.findViewById<TextView>(R.id.textView).text = "Abri o fragment 1"

        return minhaView
    }

    // Metodo depreciado
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        Log.i("ciclo_vida", "onAttach iniciado.")
//        super.onActivityCreated(savedInstanceState)
//    }

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