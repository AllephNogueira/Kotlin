package com.allephnogueira.tela_login_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Entrar(View view){
        // Aqui eu pego os dados do botao
        EditText loginEditText = findViewById(R.id.editText_Usuario);
        EditText senhaEditText = findViewById(R.id.editTextTextPassword);

        // Agora vamos passar esses dados
        String loginDigitado = loginEditText.getText().toString();
        String senhaDigitada = senhaEditText.getText().toString();

        // Agora vamos criar a instancia do usuario
        Usuario usuario = new Usuario();

        // Agora vamos passar os dados para o usuario
        usuario.setLogin(loginDigitado);
        usuario.setSenha(senhaDigitada);


        if(usuario.getLogin().equals("alleph") && usuario.getSenha().equals("123456")){
            Toast.makeText(this, "Usuario confirmado", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Servico.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Dados incorretos", Toast.LENGTH_LONG).show();
        }
    }

    public void Sair(View view){
        System.out.println("Aplicação encerrada!");
        finish();
    }

}