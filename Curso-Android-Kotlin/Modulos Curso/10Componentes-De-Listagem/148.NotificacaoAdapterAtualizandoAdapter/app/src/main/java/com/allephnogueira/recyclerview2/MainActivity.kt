package com.allephnogueira.recyclerview2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var rvAgenda : RecyclerView
    lateinit var contatoAdapter : AdapterAgenda
    lateinit var btnExecutar: Button




    // 4 Atualizando os dados sempre que o usuario sair e voltar para a tela.
    override fun onStart() {
        // Aqui é sempre quando o usuario sai da tela, então imagina que ele sai da tela, quando ele voltar os dados vai ser atualizado.
        // Ou seja quando ele volta para a tela o metodo vai ser chamado e podemos carregar toda nossa lista de contato novamente
        // Agora imagina que queremos buscar os dados em outro local, poderiamos por exemplo buscar os dados em um servidor ou API
        // Aqui é somente um exemplo, mas vamos fazer algo mais simples e vamos continuar usando la em baixo.

        //contatoAdapter.atualizarListaDeDados()

        super.onStart()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        // Aqui é exibido apenas uma vez, quando o usuario abre a tela carregamos o onCreate;
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listaContatoAgenda = mutableListOf<Contato>(
            Contato("Alleph", "21988180461"),
            Contato("Fernanda", "21980301853"),
            Contato("Alleph2", "21975575694"),
            Contato("Teste", "11123456769"),
            Contato("Teste", "11123456789"),
            Contato("Teste", "11123456789"),
            Contato("Teste", "11123456789"),
            Contato("Teste", "11123456789")
        )

        rvAgenda = findViewById(R.id.recyclerView)

        contatoAdapter = AdapterAgenda { nome ->
            Toast.makeText(this, "Ola $nome ", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, NovaActivity::class.java)
            intent.putExtra("nome", nome)

            startActivity(
                intent
            )
        }


        contatoAdapter.atualizarListaDeDados(listaContatoAgenda) // 3 Agora aqui podemos passar nossa lista, porque ja estamos com o Adapter instanciado


        rvAgenda.adapter = contatoAdapter

        //5 Agora queremos que o usuario clique no botao e adicione um novo contato
        // Imagina que esse button vamos executar algo do tipo: Adicionar novo contato, atualizar lista de contatos...

        btnExecutar = findViewById(R.id.btn_executar)
        btnExecutar.setOnClickListener {
            // Nesse exemplo vamos adicionar um novo item e vamos notificar o adapter que essa lista mudou.

            // 5.1 Adicionando um novo usuario dentro da lista.
            listaContatoAgenda.add(
                Contato("AllephNovo", "11988180461")
            )

            // 5.2 Atualizando a lista apos adicionar novo usuario

            contatoAdapter.atualizarListaDeDados(listaContatoAgenda)

            // 5.3 Agora precisamos passar o metodo la dentro do Adpater, aquele do começo da aula
            // Para o adapter entender que vamos atualizar a lista novamente.

        }


        /**
         * Aqui podemos utilizar de duas formas, seja XML ou Codigo
         * Da forma que esta aqui esta na vertical.
         */
        rvAgenda.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false)


        rvAgenda.addItemDecoration(
            DividerItemDecoration(this, RecyclerView.VERTICAL)
        )


//        rvAgenda.layoutManager = GridLayoutManager (
//            this,
//            3,
//            RecyclerView.VERTICAL,
//            false)

//        rvAgenda.layoutManager = StaggeredGridLayoutManager (
//            2,
//            RecyclerView.VERTICAL
//        )

    }


}