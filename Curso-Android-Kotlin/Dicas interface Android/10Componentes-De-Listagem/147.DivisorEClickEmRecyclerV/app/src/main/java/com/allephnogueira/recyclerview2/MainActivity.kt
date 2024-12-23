package com.allephnogueira.recyclerview2

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    lateinit var rvAgenda : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contatosAgenda = listOf<Contato>(
            Contato("Alleph", "21988180461"),
            Contato("Fernanda", "21980301853"),
            Contato("Alleph2", "21975575694"),
            Contato("Teste", "111234567845666666666691112345678456666666666911123456784566666666669111234567845666666666691112345678456666666666911123456784566666666669"),
            Contato("Teste", "11123456789"),
            Contato("Teste", "11123456789"),
            Contato("Teste", "11123456789"),
            Contato("Teste", "11123456789")
        )

        rvAgenda = findViewById(R.id.recyclerView)
        rvAgenda.adapter = AdapterAgenda(contatosAgenda) { nome ->
            Toast.makeText(this, "Ola $nome ", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, NovaActivity::class.java)
            intent.putExtra("nome", nome)

            startActivity(
                intent
            )
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