package com.allephnogueira.acaidotue;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CaixaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_caixa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Podemos criar no onCreate
        // onCreate = quando a tela for criada.
        // Então aqui vamos fazer tudo quando a tela estiver sendo criada, vai ser criado tudo de forma automatica.


        // Vamos pegar a referencia do botao para fazer o evento de click

        Button botao = findViewById(R.id.botaoSalvar); // Aqui vamos retornar um objeto ai podemos manipular da forma que quiser.
        // setOnClickListener configura açao para o botao quando for clicada
        botao.setOnClickListener(new View.OnClickListener() {
            //onClickListener é onde vamos colocar as ações que queremos fazer.
            @Override
            public void onClick(View view) {
                // Vamos por o codigo a ser executado
                System.out.println("Botão foi clicado.");
            }
        }); // Dentro do parametro que vamos colocar a ação que queremos executar



    }


}