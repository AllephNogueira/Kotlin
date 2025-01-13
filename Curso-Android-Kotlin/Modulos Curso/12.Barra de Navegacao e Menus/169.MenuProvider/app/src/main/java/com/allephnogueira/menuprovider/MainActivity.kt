package com.allephnogueira.menuprovider

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.menuprovider.databinding.ActivityMainBinding

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
        inicializarActionBar()

        // Aqui seria para nao exibir o ActionBar
        //supportActionBar?.hide()



    }





    private fun inicializarActionBar() {
        /**
         * Vamos inicializar o actionBar
         * Vamos adicionar esse metodo addMenuprovide
         * vamos adicionar uma interface e vamos ter que adicionar seus metodos
         *
         * Vamos ter que aderir os 2 metodos que vem da interface
         * onCreateMenu = quando o menu for criado
         * onMenuItemSelected = ao selecionar um item
         */
        addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(
                        R.menu.menu, // Onde esta nosso layout
                        menu // Objeto menu
                    )
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                    /** Lembrar que aqui nao podemos usar o This, porque estamos dentro de outra classe
                     * Aqui estamos dentro da interface que implementa esses metodos então por isso vamos utilizar o applicationContext
                     *
                     */
                    when(menuItem.itemId){
                        R.id.menuCadastrar -> {
                            Toast.makeText(applicationContext, "Cadastrar contato", Toast.LENGTH_SHORT).show()
                        }

                        R.id.menuDeletar -> {
                            Toast.makeText(applicationContext, "Deletar usuario", Toast.LENGTH_SHORT).show()
                        }

                        R.id.menuSair -> {
                            Toast.makeText(applicationContext, "Fechando...", Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }

                    return true
                }

            }
        )
    }
}