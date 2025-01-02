package com.allephnogueira.fragmentcomparametro

import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.allephnogueira.fragmentcomparametro.Fragment.fragment_produtos

class MainActivity : AppCompatActivity() {


    private lateinit var btnMercado : Button
    private lateinit var btnFamarcia : Button
    private lateinit var btnLanchonete : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnMercado = findViewById(R.id.btn_mercado)
        btnFamarcia = findViewById(R.id.btnFarmacia)
        btnLanchonete = findViewById(R.id.btnLanchonete)

        /**
         * Primeiro vamos querer que produto retorne uma lista de produtos
         * Vamos precisar instanciar ele
         */


        btnMercado.setOnClickListener {

            /**
             * Instanciando o fragmento para poder usar logo mais.
             */

            val produtosMercado = fragment_produtos()

            /**
             * Aqui vamos fazer o nosso bundle, passando uma chave(Categoria) e o valor dela
             */
//            val bundle = bundleOf(
//                /**
//                 * Aqui vamos colocar a chave e o valor
//                 */
//                "categoria" to "mercado",
//                "usuario" to "Alleph"
//
//                // Traduzindo a chave é categoria para(to) mercado.
//                // Podemos passar também mais valores se quiser, segue o exemplo.
//                // Imagina que podemos recuperar isso de um banco de dados.
//            )

            /**
             * Arguments serve para passar dados de um fragment para outro.
             * No actvitiy iriamos usar o inputExtra
             *
             * Passando o bundle para dentro de produtosMercado - no caso estamos passando os argumentos com a CHAVE
             */
            //produtosMercado.arguments = bundle


            /**
             * Aqui criar nosso container, nossa interface fragmento, passando o layout e a classe que vai manipular o layout.
             */
//            supportFragmentManager.
//            beginTransaction().
//            replace(R.id.fragmentContainerView, produtosMercado).
//            commit()


            val bundle = bundleOf(
                /**
                 * Aqui vamos colocar a chave e o valor
                 */
                "categoria" to "mercado",
                "usuario" to "Alleph"

                // Traduzindo a chave é categoria para(to) mercado.
                // Podemos passar também mais valores se quiser, segue o exemplo.
                // Imagina que podemos recuperar isso de um banco de dados.
            )

            supportFragmentManager.commit {
                replace<fragment_produtos>(R.id.fragmentContainerView, args = bundle)
            }

            /**
             * Agora explicação sobre passar o argumento para fragmentos
             * Vamos imaginar que queremos acessar os itens de mercado
             *
             * vamos colocar o fragmento dentro de produtosMercado
             *
             * vamos criar um bundle, onde vamos passar a categoria mercado.
             * Arguments serve para passar os dados de um fragmento para o outro.
             *
             */
        }

    }
}