package com.allephnogueira.radiobuttoneradiogroup

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.radiobuttoneradiogroup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { /** minha view binding */
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


        with(binding){

            /** Primeira forma de fazer e verificando os itens
             * 1 a 1
             */
//            btnExecutar.setOnClickListener {
//                radioButton()
//            }

            /** Outra forma de fazer
             *
             */

            btnExecutar.setOnClickListener {
                segundaFormaDeFazer()

            }

            /** Agora imagina que queremos exibir uma lista com todos os itens que o usuario clicou
             * Então teriamos que fazer um radioButton
             * E ir pegando os dados e adicionando em uma listOf
             * E ai sim imprimir essa listOf, podemos fazer dessa listOf um recyclerView com todos os dados.
             */
        }

    }



    private fun radioButton() {
        val selecionadoVisa = binding.rbVisa.isChecked
        val selecionadoMaster = binding.rbMaster.isChecked
        val selecionadoPix = binding.rbPix.isChecked

        if (selecionadoVisa) {
            binding.textResultado.text = "Forma de pagamento: Visa"
        }else if (selecionadoMaster) {
            binding.textResultado.text = "Forma de pagamento: Master"
        }else if (selecionadoPix) {
            binding.textResultado.text = "Forma de pagamento: Pix"
        }else {
            binding.textResultado.text = "Nenhuma forma de pagamento selecionada!"
        }
    }

    private fun segundaFormaDeFazer() {


        /**
         * Aqui vamos pegar o item selecionado
         */

        val itemSelecionado = binding.rgFormaPagamento.checkedRadioButtonId

        /**
         * Exibir item selecionado
         * e exibindo
         */

        binding.textResultado.text = when(itemSelecionado) {
            // Dessa forma que fazemos para descobrir qual botao o usuario clicou.
            // Estamos pegando pelo ID de cada um dele.
            // Poderiamos adicionar esses valores dentro de uma variavel também, para depois manipular.
            R.id.rbVisa -> "Visa"
            R.id.rbMaster -> "Master"
            R.id.rbPix -> "Pix"
            else -> "Nada selecionado"
        }


        /** Aqui vamos limpar
         * Apos a gente imprimir na tela oque o usuario quer, vamos limpar
         */

        binding.rgFormaPagamento.clearCheck()
    }




}