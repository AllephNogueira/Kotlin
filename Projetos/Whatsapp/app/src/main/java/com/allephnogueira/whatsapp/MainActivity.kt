package com.allephnogueira.whatsapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.whatsapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ContextCompat.getColor(this, R.color.primaria).also { this.window.statusBarColor = it }

        inicializarToolbar()


    }

    private fun inicializarToolbar() {
        val toolbar = binding.includeMainToolbar.tbPrincipal // Logal da toolbar
        setSupportActionBar( toolbar ) /* Este método define a Toolbar como a  da Activity atual, permitindo que ela seja utilizada para exibir títulos, botões de navegação, e outros elementos da interface padrão de aplicativos Android. */
        supportActionBar?.apply {
            title = "Whatsapp" // configura o texto
        }
        /** Vamos criar um menu para a nossa toolbar
         * Res > new XML > Source set = menu
         * La dentro esta tudo configurado se quiser olhar mais.
         *
         * Ali em baixo seria assim: Se o usuario selecionar perfil, vamos abri a tela
         * Se o usuario quiser sair, vamos deslogar ele.
         */
        addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_principal, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when ( menuItem.itemId ) {
                        R.id.item_perfil -> {
                            startActivity(
                                Intent(applicationContext, PerfilActivity::class.java)
                            )
                        }

                        R.id.item_sair -> {
                            deslogarUsuario()
                        }

                    }
                    return true
                }

            }
        )
    }

    private fun deslogarUsuario() {
        AlertDialog.Builder(this)
            .setTitle("Deslogar")
            .setMessage("Você realmente quer sair?")
            .setNegativeButton("Não") { _, _ -> }
            .setPositiveButton("Sim") { _, _ ->
                firebaseAuth.signOut()
                startActivity(
                    Intent(applicationContext, LoginActivity::class.java)
                )
            }
            .create()
            .show()

    }
}