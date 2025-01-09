package com.allephnogueira.spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        spinnerExibicao() // Exibir meu spinner

        /** Aqui só vamos precisar fazer se quiser colocar a ação de selecionar algo apos clicar no botao
         *
         */
//        with(binding){
//            btnEnviar.setOnClickListener {
//                spinnerSelecionarItem() // Metodo para capturar o item selecionado
//            }
//        }



    }


    private fun spinnerExibicao() {




        /** Aqui podemos fazer de 3 formas, a primeira e criando uma lista
         * A segunda e pegando uma lista que colocamos la dentro do XML
         */

        /**
         * Primeira opcao criando uma lista
         */
        //        val categorias = listOf("Selecione a bandeira do Posto", "BR", "Ipiranga", "Shell", "Outros")


        /**
         * Segunda opcao criando um array no strings.xml
         */

//        val categorias = resources.getStringArray(
//            // Aqui para acessar fazemos isso
//            R.array.categorias
//        )

        /** Terceira opcao
         * Repara que só muda que primeiro colocamos o layout e depois a lista */

        binding.spinnerCategorias.adapter = ArrayAdapter.createFromResource(
            this, // Contexto
            R.array.categorias, // Onde esta nossa lista de itens // aqui vem do XML
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item // Layout
        )

//        binding.spinnerCategorias.adapter = ArrayAdapter<String>(
//            this, // Contexto
//            android.R.layout.simple_spinner_dropdown_item, // Qual layout vamos utilizar
//            categorias // Aqui vamos passar as nossas categorias // Aqui seria uma variavel com nossa lista
//
//        )


        /** Aqui estamos começando a configurar nosso evento de clique
         *    **  Nesse evento ele vai ficar esperando o usuario clicar no nosso spinner e fazer algo **
         * no OnItemSelectedListener vai ter 2 pacotes, mas vamos utilizar o pacote de android. widget
         *Agora vamos ter que implementar alguns metodos
         * No caso vai ser esses 2 de baixo.
         *
         */

        binding.spinnerCategorias.onItemSelectedListener = object: OnItemSelectedListener {
            override fun onItemSelected(
                /**
                 * Aqui nesse primeiro metodo é quando alguem clicar vamos poder selecionar uma ação para fazer ali em baixo
                 */
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) { // Aqui é onde vamos executar uma ação.
                // Aqui vamos pegar o item selecionado

                //val itemSelecionado = parent?.selectedItem // esse metodo tambem pega
                val itemSelecionado = parent?.getItemAtPosition(position) // Aqui vamos passar a posição que ja esta como nosso parametro.

                binding.textResultado.text = "Item selecionado: $itemSelecionado"

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                /** Quando nada for selecionado, também podemos fazer uma ação.
                 *
                 */

                binding.textResultado.text = "Nenhum item selecionado"
            }

        }


    }


    private fun spinnerSelecionarItem() {
        val itemSelecionado = binding.spinnerCategorias.selectedItem // Retorna o nome do item selecionado
        val itemPosicao = binding.spinnerCategorias.selectedItemPosition // Retorna a posição do item

        if (itemPosicao!=0){
            binding.textResultado.text = "Posto selecionado: $itemSelecionado - Posição: $itemPosicao"
        }else {
            Toast.makeText(this, "Selecione um item", Toast.LENGTH_SHORT).show()
        }


    }

}