package com.allephnogueira.switchetogglebutton

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.switchetogglebutton.databinding.ActivityMainBinding

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

            /** FORMA 1 DE FAZER
             * Aqui vamos pegar o objeto Switch e vamos fazer um evento de clique, que é quando ele for ativo
             * Ele verifica se o item foi ativo
             * Se foi ativo ele vai mudar o tema.
             *
             */
            swTema.setOnCheckedChangeListener { _, isChecked -> // _ pq nao vamos usar, então nao queremos que ocupe espaço na memoria
                if (isChecked) { // Se estiver ativo
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }


            /** OPCAO 2
             * Aqui vamos exibir quando clicar no botao
             * Vamos exibir SWITCH marcado ou não
             */
            btnTema.setOnClickListener {
                switchTema()
            }


        }
    }

    private fun switchTema() {
        val switchMarcado = binding.swTema.isChecked // verifcar se esta marcado
        val toggleMarcado = binding.toggleAtivo.isChecked // verifcar se esta marcado


        binding.textResultado.text = "Switch $switchMarcado \nToggle: $toggleMarcado"
    }
}