package com.allephnogueira.funcaosuspensa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.funcaosuspensa.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var job: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnCancelar.setOnClickListener {
            Toast.makeText(applicationContext, "Coroutine cancelada", Toast.LENGTH_SHORT).show()
            job?.cancel()

            binding.btnIniciar.isEnabled = true
            binding.btnIniciar.text = "Retornar?"

        }

        binding.btnIniciar.setOnClickListener {

            /** Parar uma coroutine
             * Toda vez que utlizamos uma coroutina ela retorna um objeto do tipo Job
             * E com esse objeto do tipo Job podemos fazer algumas coisas.
             *
             */

//            job = CoroutineScope(Dispatchers.IO).launch {
//                executar()
//            }

            /** Contando a coroutine, fazendo ela trabalhar no maximo 20 segundos */

            job = CoroutineScope(Dispatchers.IO).launch {

                withTimeout(20000){ // 20 segundos.
                    executar()
                }

            }


            /**
             *  Aqui mudamos o nome da funçao para executarR para poder fazer a funçao de parada em uma coroutine
             */

//            CoroutineScope(Dispatchers.IO).launch {
//                executarR()
//                /** O metodo executar ele vai pegar todos os dados e vai executar aqui
//                 * Ele vai pegar os dados de outros metodos
//                 * Vai recuperar o usuario que esta logado
//                 * Vai recuperar as postagens do usuario
//                 */
//
//            }
        }


        binding.btnAbrirOutraTela.setOnClickListener {
            val intent = Intent(this, SegundaActivity::class.java)
            startActivity(intent)
        }


    }


    /** OnStop parando uma coroutine quando a gente abrir outra activity */

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }


    private suspend fun executar() {
        repeat(15) { indice ->
            Log.i("info_coroutine", "Executando: $indice - T: ${Thread.currentThread().name}")
            delay(2000) //2s

            withContext(Dispatchers.Main) {
                binding.btnIniciar.text = "Executando"
                binding.btnIniciar.isEnabled = false
                if (indice >= 14) {
                    binding.btnIniciar.isEnabled = true
                }
            }
        }
    }


    private suspend fun recuperarUsuarioLogado(): Usuario {
        /** Toda funçao suspensa ela só pode ser usada dentro de uma corrotina
         * Ou só pode ser usada dentro de outra funçao suspensa.
         *
         * Delay = Se você reparar ele também é uma suspend fun, repare o icone ao lado.
         * Se voce remover o suspend voce nao pode usar o metodo.
         *
         * Atençao aqui estamos simulando que vamos retornar um usuario.
         * Usuario = ID e Nome
         *
         * Aqui vamos retornar o nome e id do usuario.
         */


        delay(2000) //2s

        return Usuario(1020, "Alleph")
    }


    private suspend fun recuperarPostagensPeloId(idUsuario: Int): List<String> {
        delay(2000)

        return listOf(
            "Viagem para Cabo Frio",
            "Comprei um carro novo",
            "Me formei...",
            "Estudando Android",
            "Indo dormir."
        )
    }


    private suspend fun executarR() {
        /** Nesse log vamos pegar o usuario que esta vindo da outra funçao e vamos exibir o nome do usuario
         *
         * Atençao, repara que para recuperar o usuarioLogado simulamos que ele iria demorar 2 segundo para acontecer isso.
         * Então ele vai aguardar recuperar esse usuario e apos isso que ele vai executar a proxima linha
         */

        val usuario = recuperarUsuarioLogado()
        Log.i("info_coroutine", "${usuario.usuario}: ${Thread.currentThread().name}")

        /** Atençao aqui também, o metodo recuperar postagens também demora 2 segundos
         * Reparar que se não usar o suspend fun ele não vai aguardar as postagens ser recuperada e vamos ter problemas
         * Imagina oque vamos exibir na tela se as postagens não for configuradas
         *
         * Imagina que recuperamos os dados e salvamos na nossa data class
         * Entao vamos pegar esse ID recuperado e vamos passar para recuperar também as postagens.
         */
        val postagens = recuperarPostagensPeloId(usuario.id)
        Log.i("info_coroutine", "Usuario: ${usuario.id} - T: ${Thread.currentThread().name}")
        Log.i("info_coroutine", "Postagens: ${postagens.size} - T: ${Thread.currentThread().name}")
    }
}