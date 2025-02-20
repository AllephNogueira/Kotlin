package com.allephnogueira.acaidotue;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

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


        Button botao = findViewById(R.id.botaoSalvar);


        TextInputEditText editText_email = findViewById(R.id.edit_email); // Pegando a referencia do objeto.



        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //1 Pegamos o objeto
                //2 Pegamos o texto que esta dentro do objeto
                //3 Convertemos para uma string
                String emailUsuario = editText_email.getText().toString();

                System.out.println("Botão foi clicado " + emailUsuario);
            }
        });



    }


}