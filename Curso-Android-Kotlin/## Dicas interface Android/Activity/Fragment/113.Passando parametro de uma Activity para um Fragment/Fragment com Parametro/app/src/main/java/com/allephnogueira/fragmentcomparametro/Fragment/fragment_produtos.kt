package com.allephnogueira.fragmentcomparametro.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.allephnogueira.fragmentcomparametro.R

class fragment_produtos : Fragment() {


    lateinit var textCategoria : TextView
    lateinit var resultadoBusca : TextView

    /**
     * Aqui vamos pegar os dados que vem da outra activity, mas eles podem vir nulo, então vamos iniciar eles como nulos.
     */

    private var categoria: String? = null
    private var usuario: String? = null


    /**
     * Agora vamos recuperar os dados que vem da outra activity
     * Lembrar que o onCreateView ele cria a exibição
     * Então vamos recuperar no onCreate (Aqui configuramos as informações)
     *
     *
     * Resumindo onCreate (Recupero as informações que esta vindo da outra Activity, e preparo os dados para ser visualizados.)
     * onCreateView (Exibo essas informações)
     * onStart (posso recuperar dados que precisam ser exibidos na minha interface)
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Entao vamos pegar os dados que estao vindo como argumento.
         * Recuperando argumentos da activity
         */

        categoria = arguments?.getString("categoria")
        usuario = arguments?.getString("usuario")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val minhaView = inflater.inflate(
            R.layout.fragment_itens,
            container,
            false
        )

        textCategoria = minhaView.findViewById(R.id.text_categoria)
        resultadoBusca = minhaView.findViewById(R.id.textProdutoBuscado)

        /**
         * Pegando o argumento na hora que o fragmento for chamado
         * Exibindo os argumentos no fragmento.
         */

        textCategoria.text = categoria
        resultadoBusca.text = usuario



        return minhaView
    }
}