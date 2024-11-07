package com.allephnogueira.navegandopelosfragment

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.navegandopelosfragment.Fragment.ConversasFragment
import com.allephnogueira.navegandopelosfragment.Fragment.StatusFragment

class MainActivity : AppCompatActivity() {

    lateinit var btnConversas : Button
    lateinit var btnStatus : Button
    lateinit var btnRemoverFragment : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnConversas = findViewById(R.id.buttonConversas)
        btnStatus = findViewById(R.id.buttonStatus)
        btnRemoverFragment = findViewById(R.id.buttonRemoverFragment)


        /**
         * Quando clicar no botao quero abrir o fragment.
         */

        val conversasFragment = ConversasFragment()
        btnConversas.setOnClickListener {
            /**
             * supportFragmentManager é um objeto que nos permite manipular os fragments
             * beginTransaction é ele que começa a transação ele inicializa as transações que voce pode fazer no fragment
             *
             */
            val fragmentManager = supportFragmentManager.beginTransaction()

            /**
             * Aqui podemos fazer diversas alterações no nosso fragment
             * A ALTERAÇÂO QUE ESTAMOS FAZENDO É: ADICIONAR ELE NA TELA.
             * Primeiro parametro é o container = objeto que vai ser exibido na tela
             * Segundo parametro é o objeto Fragment onde colocamos nossos codigos, reparar que precisamos instanciar ele()
             */
            fragmentManager.replace( R.id.fragmentNaActivity, ConversasFragment() )

            /**
             * Aqui é onde vamos confirmar as alterações
             */
            fragmentManager.commit()
        }


        /**
         * Fazendo de outra forma para ser mais organizado e mais rapido
         */


        btnStatus.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentNaActivity, StatusFragment()).commit()
        }

        /**
         * Lembrar de uma coisa o metodo Add. ele vai adicionar um fragment, mas se ja existir um na tela, ele vai adicionar o outro por cima
         * Devemos sempre usar o replace para substituir o atual.
         *
         * ERRADO : supportFragmentManager.beginTransaction().add(R.id.fragmentNaActivity, StatusFragment()).commit()
         * CORRETO: supportFragmentManager.beginTransaction().*replace*(R.id.fragmentNaActivity, StatusFragment()).commit()
         */


        /**
         * Agora imagina que queremos remover um fragment que não queremos mais utilizar
         * Para remover precisamos remover uma instancia especifica de um objeto
         * Porque precisamos instanciar um objeto dentro da variavel?
         *
         * Porque toda vez que instanciamos dentro do proprio codigo ele cria instancias diferentes
         * Imagina assim
         * jogador1 = ConversasFragment
         * jogador2 = ConversasFragment
         * Repara que ele vai criando varios objetos, então não vamos saber de fato qual o jogador que queremos remover
         * Pra isso vamos criar uma variavel e instanciar apenas 1 objeto dentro.
         * Criamos la em cima para abrir a conversa  val conversasFragment = ConversasFragment()
         * Agora esse botao aqui vai ser responsavel por destruir esse objeto que esta dentro da variavel.
         */

        btnRemoverFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .remove(conversasFragment)
                .commit()
        }
    }
}