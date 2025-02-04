package com.allephnogueira.coroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnInciar.setOnClickListener {
            CoroutineScope( Dispatchers.IO).launch {
                /** Thread para atualizar dados
                 * Não serve para atualizar interface
                 * Ela é uma thread secundaria
                 * Se quiser atualizar interface precisamos da Thread Main
                 */

                repeat(15) {indice ->
                    Log.i("info_coroutine", "Executando: $indice - T: ${Thread.currentThread().name}")

                    withContext(Dispatchers.Main) {
                        /** Qual contexto queremos atualizar? qual thread?
                         * Agora aqui dentro vamos executar no contexto da thread principal
                         * Ela vai ser responsavel por atualizar o codigo a baixo que é atualizar o nome do botao
                         * Que é uma atualização de VIEW/Interface
                         */

                        binding.btnInciar.text = "Executando"
                        binding.btnInciar.isEnabled = false
                        if (indice >= 14) {
                            binding.btnInciar.isEnabled = true
                            binding.btnInciar.text = "Executar novamente?"
                        }
                    }

                    delay(1000) // Tempo que ele vai esperar para executar novamente.

                }


            }
        }
    }
}