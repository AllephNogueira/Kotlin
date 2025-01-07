package com.allephnogueira.alertdialog

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.alertdialog.databinding.ActivityMainBinding

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

        with(binding) {
            btnCadastrar.setOnClickListener {

                alertDialogResumido()
            }
        }
    }

    private fun caixaDialogAlerta() {

        /**
         * Estamos acessando a classe AlertDialog
         * Depois vamos usar o metodo Builder()
         * Ele serve para construir o alertDialog para gente.
         */

        val alertBuilder = AlertDialog.Builder(this) // Aqui é para construir

        /**
         * Agora que criamos nosso alertDialog, podemos fazer todas as configurações
         */

        alertBuilder.setTitle("Cadastro de posto")
        alertBuilder.setMessage("Deseja realmente cadastrar esse posto?")

        /** Agora vamos utilizar as ações do nosso AlertDialog
         *
         */

        alertBuilder.setNegativeButton("Cancelar"){ dialog, posicao ->
            /** Aqui dentro vamos utilizar 2 parametros, o dialog e posição
             * Posição seria a posição do botao que estamos criando.
             * Cada posição vai ter ex um numero e ai vamos poder saber qual item foi clicado
             * Imagina que a posiçao 1 seria adicionarPosto, e com isso iriamos chamar um metodo para adicionar novos postos
             * Imagina que a posição 2 seria cancelar, então usuariamos igual a baixo
             *
             * Lembrar que toda vez que queremos executar uma função lambda como 2 parametro podemos fazer isso
             * Resumindo o metodo setNegativeButton ele espera receber 2 parametros, e com isso podemos fazer o segundo parametro de função lambda
             */


            Toast.makeText(this, "Cancelar clicado $posicao.", Toast.LENGTH_SHORT).show()

            /** Tudo que voce quiser fazer com o alert, podemos utilizar o dialog
             * //dialog.dismiss() //dimiss = liberar a caixa de dialago
             */





        }


        alertBuilder.setPositiveButton("Adiconar"){dialog, posicao ->
            /**
             * Aqui vamos seguir o mesmo parametro
             */

            Toast.makeText(this, "Posto adiconado $posicao", Toast.LENGTH_SHORT).show()

        }

        /** Outras ações
         *  Aqui temos 2 ações, se em Cancelable estiver FALSO
         *  O usuario tem que fazer alguma ação, ele nao tem a opção de cancelar.
         *  ** CANCELAR ** lembra que quando clicamos fora ele automaticamente fecha o Alert? então isso nao é mais possivel se cancelar estiver false
         *
         *
         *  O NeutralButton serve para voce colocar um botao onde o usuario pode pedir ajuda.
         *
         * setIcon podemos utilizar para exibir um icone que fizemos.
         *
         */

        alertBuilder.setCancelable(false)


        alertBuilder.setNeutralButton("Ajuda"){dialog, posicao ->
            Toast.makeText(this, "Clique em adicionar para adicionar um novo posto\nCancelar para não adicionar um posto.", Toast.LENGTH_SHORT).show()
        }


        alertBuilder.setIcon(R.drawable.ic_alert_24)



        /**
         * Agora vamos criar nosso alertDialog
         */

        val alertDialog = alertBuilder.create() // Aqui é para criar

        /**
         * Agora vamos exibir ele
         */

        alertDialog.show()




    }

    fun alertDialogResumido() {
        /**
         * Aqui estamos fazendo de forma resumida com encadeamento de metodos.
         */

        AlertDialog.Builder(this)
            .setTitle("O titulo")
            .setMessage("Mensagem do meu alerta")
            .setCancelable(false) // O usuario é obrigado a clicar em alguma informação
            .setNeutralButton("?"){dialog, posicao ->
                Toast.makeText(this, "Voce quer adicionar um novo posto?", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar"){dialog, posicao ->
                Toast.makeText(this, "Cancelando... $posicao", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Adicionar"){dialog, posicao ->
                Toast.makeText(this, "Adicionando novo posto... $posicao", Toast.LENGTH_SHORT).show()
            }
            .setIcon(R.drawable.ic_alert_24)
            .create()
            .show()
    }
}