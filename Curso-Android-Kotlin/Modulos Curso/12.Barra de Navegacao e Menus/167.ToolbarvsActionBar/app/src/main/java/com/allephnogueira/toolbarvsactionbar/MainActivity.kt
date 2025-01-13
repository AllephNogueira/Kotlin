package com.allephnogueira.toolbarvsactionbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.toolbarvsactionbar.databinding.ActivityMainBinding

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
        //supportActionBar?.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        /** 1 AO CRIAR OPCOES DE MENU
         * Esse metodo permite que voce reconstrua o menu
         * Aqui vamos precisar inflar e construir nosso menu
         * Esse metodo é chamado quando a nossa tela é carregada e ai se tiver ele constroi e menu para a gente
         *
         *
         * Primeiro parametro é nosso XML que construimos
         * Segundo parametro é o proprio menu que vai construir.
         *
         * Objeto menu: -> é o menu que é utilizado pelo proprio android, seria aquelas opções que tem em cima.
         * Resumindo ele vai criar e passar para voce, e voce vai inflar utilizando seu proprio layout
         * Ai sim ele vai configurar e vai inflar no seu layout.
         *
         * Agora os menus não sao clicaveis, agora vamos utilizar outros metodos para isso.
         */

        menuInflater.inflate(R.menu.menu_principal, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /** 2 AO UTILIZAR AS OPCOES SELECIONADAS
         * Atenção não vamos criar eventos de clique, porque ele já vai de forma automatica nesse metodo aqui
         * Quando o usuario clicar no item na tela, o metodo vai ser chamado e ele vai saber qual foi o menuItem que foi clicado
         * Ai sim ele vai te passar o menuItem que foi clicado
         *
         *
         * Podemos fazer um teste, pegamos o item e dentro dele vai ter o ID dos itens
         * Lembra que configuramos um ID para cada item do actionBar? la no menu.xml?
         * Então agora ele vai saber qual foi o id do item clicado
         *
         * Ou seja aqui podemos adicionar também uma opçao para adicionar tema dark...
         */

        when(item.itemId){
            R.id.item_adicionar -> {
                Toast.makeText(this, "Adicionar foto", Toast.LENGTH_SHORT).show()
            }
            R.id.item_pesquisar -> {
                Toast.makeText(this, "Pesquisar foto", Toast.LENGTH_SHORT).show()
            }
            R.id.item_configuracoes -> {
                Toast.makeText(this, "Configurar foto", Toast.LENGTH_SHORT).show()
            }
            R.id.item_sair -> {
                // Aqui vamos fazer a ação para fechar o app
                Toast.makeText(this, "Fechando app", Toast.LENGTH_SHORT).show()
                finish()
            }

        }



        return true
    }

}