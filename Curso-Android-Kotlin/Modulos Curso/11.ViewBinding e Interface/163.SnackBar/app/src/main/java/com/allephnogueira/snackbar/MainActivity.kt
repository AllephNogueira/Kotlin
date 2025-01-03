package com.allephnogueira.snackbar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.snackbar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

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

            btnExecutar.setOnClickListener { viewSetOnClick ->


                exibirSnackBar(viewSetOnClick) // Aqui estamos passando nossa View como parametro


//                /** Na SnackBar, precisamos passar 3 parametros
//                 * 1 view
//                 * 1 texto
//                 * 1 duração
//                 * igual ao toast
//                 *
//                 * Lembrar que a snackBar, voce pode passar qualquer view, ela mesma vai tentar encontrar o objeto do tipo viewGroup
//                 * Que pode sim exibir a snackBar
//                 * Diferente do toast, quando voce clica ela abre por cima de outros itens
//                 * Ja a snack bar ela precisa de um viewGroup para ela abrir.
//                 *
//                 * Usando dentro do setOnClickListener que já é do tipo View, podemos utilizar ela mesmo
//                 */
//                Snackbar.make(
//                    viewSetOnClick,
//                    "Alteração feita com sucesso",
//                    Snackbar.LENGTH_INDEFINITE // é um tipo de duração idefinida o item é exibido e só sai da tela se o usuario clicar em outra coisa.
//                ).show()
//
//                /**Agora podemos configurar uma ação para nossa snackBar
//                 *
//                 */

            }
        }
    }

    private fun exibirSnackBar(view: View) {


        val snack = Snackbar.make(
            view,
            "Mensagem da snackBar",
            Snackbar.LENGTH_INDEFINITE
        )


        // Aqui podemos passar a açao e ainda criar uma funçao lambda por fora

        snack.setAction("Desfazer"){
            Toast.makeText(this, "Desfeito", Toast.LENGTH_SHORT).show()
        }

        // Configurando as cores e os demais para testes.

        snack.setTextColor(
            // Porque usamos o this? porque o contexto realmente é essa classe
            // Se fosse outra classe teriamos que passar ela.
            ContextCompat.getColor(this, R.color.black)
            // E ai dessa forma podemos mudar a cor de varios itens..
        )


        // Aqui estamos fazendo um encadeamento de metodo
        // O Show, poderiamos utilizar ali em cima depois do (
        // Mas estamos passando tudo para dentro de uma variavel e exibindo com ela.
        // Vantagens de usar a snack passando ela para uma variavel e transformando em objeto
        // Agora podemos utilizar varios metodos com a variavel.
        snack.show()
    }

}