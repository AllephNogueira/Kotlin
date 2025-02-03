package com.allephnogueira.a210threads

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.a210threads.databinding.ActivityMainBinding

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


        binding.btnAbrir.setOnClickListener {
            val intent = Intent(this, SegundaActivity::class.java)
            startActivity(intent)
        }


        binding.btnIniciar.setOnClickListener {
            /** Aqui estamos encadeando metodo, estamos instanciando e ja usando seu metodo
             * Reparar que o metodo start ele vem de herança da classe Thread()
             */
            MinhaThread().start()
        }



    }


    inner class MinhaThread : Thread() {
        override fun run() {
            super.run()

            repeat(30){indice ->
                /** Aqui estamos mandando ele repetir 30x e esperar 1 segundao antes de repetir a proxima
                 * Metodo Thread.CurrentThread().nome = Cada Thread tem um nome e ai vamos saber qual thread esta processando esse bloco de codigo */
                Log.i("info_thread", "MinhaThread: $indice - T: ${Thread.currentThread().name}")

                /** Atualizando o botao para indicar que estamosa usando uma thread
                // binding.btnIniciar.text = "Executando" ERRADO
                 */

                runOnUiThread {
                    /** runOnUiThread = executar na Thread Principal
                     * Aqui oque vai acontecer? somente essa linha de codigo que vai ser executada na thread principal
                     */
                    binding.btnIniciar.text = "Executando: $indice - T: ${Thread.currentThread().name}"
                    /**Habilitando e desabilitando o botao para o usuario nao clicar e reiniciar
                     *
                     */
                    binding.btnIniciar.isEnabled = false

                    if (indice == 29) {
                        /** Agora quando a contagem terminar, vamos deixar o usuario clicar no botao novamente
                         *
                         */
                        binding.btnIniciar.text = "Reiniciar a execução"
                        binding.btnIniciar.isEnabled = true
                    }
                }

                sleep(1000)
            }

        }
    }
}