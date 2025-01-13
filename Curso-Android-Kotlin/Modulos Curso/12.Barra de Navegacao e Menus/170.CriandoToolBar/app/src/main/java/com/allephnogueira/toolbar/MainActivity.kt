package com.allephnogueira.toolbar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.toolbar.databinding.ActivityMainBinding

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

        inicializarToolBar()

    }

    private fun inicializarToolBar() {
        /** Esse metodo serve para configurar nossa barra para diferentes versoes
         *
         * A baixo vamos configurar os dados como nome, subtitulo e apos vamos inicialiazar a toolbar
         *
         */

        binding.tbPrincipal.setTitleTextColor(ContextCompat.getColor(this, R.color.white)) /* Configuração da cor */
        binding.tbPrincipal.title = "Youtube"
        binding.tbPrincipal.subtitle = "Videos a todo momento"

        /** Para configurar nossas imagens no menu, precisamos inflar ele.
         * Para isso vamos criar um menu para ele um Layout xml
         */

        binding.tbPrincipal.inflateMenu(R.menu.menu_principal)

        /** EVENTOS DE CLIQUE
         * Se eu nao adicionar esses metodos, quando eu clicar, nada vai acontecer.
         */

        binding.tbPrincipal.setOnMenuItemClickListener { menuItem ->
            /** ATENÇAO
             * Aqui devemos retornar sempre um verdadeiro se ação foi executada
             * Devemos adicionar um else, porque ele tem retorno
             * Então se caso nao cair nessas opcoes devemos deixar uma opcao padrao para ele nao fazer nada.
             */
            when(menuItem.itemId) {

                R.id.itemAdiconar -> {
                    Toast.makeText(this, "Item adicionado...", Toast.LENGTH_SHORT).show()
                    // Retorno qualificado = quer dizer que o retorno vai ser direcionado para o setOnMenuItemClickListener
                    // Resumindo aqui estamos querendo dizer que o retorno vai ser para o setOnMenuItemClickListener...
                    return@setOnMenuItemClickListener true
                }
                R.id.itemCarregar -> {
                    Toast.makeText(this, "Item carregado...", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.itemSair -> {
                    Toast.makeText(this, "Saindo...", Toast.LENGTH_LONG).show()
                    finish()
                    true
                }

                else -> {
                    true // Aqui vamos adicionar um true porque ele sempre vai retornar verdadeiro.
                }
            }

        }

        /** ATENÇÃO
         *
         * Nesse metodo vamos passar nossa toolbar e vamos configurar ela como se fosse uma ActionBar
         *Aqui eu passo uma toolbar, mas ela age como se fosse uma actionBar
         * Se eu quero uma toolBar personalizada eu devo desativar essa linha de codigo
         *
         * //setSupportActionBar(binding.tbPrincipal)
         *
         * Resumindo aplicando esse metodo de cima ela vai se comportar como se fosse uma ActionBar.
         * E para usar os eventos de clique, podemos ver a aula anterior.
         */

    }


}