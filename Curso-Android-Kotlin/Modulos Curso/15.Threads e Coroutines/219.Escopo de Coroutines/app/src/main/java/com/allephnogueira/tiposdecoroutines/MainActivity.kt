package com.allephnogueira.tiposdecoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.allephnogueira.tiposdecoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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


        binding.btnIniciar.setOnClickListener {

            /** Aqui estamos criando uma coroutine que vai atualizar a thread principal a UI
             * Vamos mandar repetir 15x
             * INDICE = quantidade de vezes que esta repetindo
             * Thread.currentThread.name = nome da Thread
             * Delay = tempo que vai demorar para repetir todo o codigo de novo
             */
            //CoroutineScope(Dispatchers.Main).launch { // MODO ANTIGO
            //MainScope().launch {

            //job = GlobalScope.launch {
            //lifecycleScope.launch {
            runBlocking {
                //binding.btnIniciar.setText("Terminou")
                repeat(15) { indice ->
                    withContext(Dispatchers.Main) {
                        binding.btnIniciar.text = "Terminou!"
                    }
                    Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")
                    delay(1000L) //1s
                }
            }
        }

        binding.btnParar.setOnClickListener {
            job?.cancel()
            binding.btnIniciar.text = "Iniciar novamente!"
        }

        binding.btnAbrirJanela.setOnClickListener {
            val intent = Intent(this, NovaActivity::class.java)
            startActivity(intent)

            finish()
        }


    }
}