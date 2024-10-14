package com.allephnogueira.acaidotue;

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

import com.allephnogueira.acaidotue.CadastroUsuario.CadastroUsuario;
import com.allephnogueira.acaidotue.ControleDeUsuarios.Usuarios;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<String, Usuarios> usuarios = new HashMap<>();

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

    public void cadastro(View view) {
        Toast.makeText(getApplicationContext(), "Redirecionando para Cadastro.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CadastroUsuario.class);
        startActivity(intent);

    }

    public void entrar(View view) {
        usuarios.put("alleph", new Usuarios("alleph", "123456", 3)); // Administrador
        usuarios.put("fernanda", new Usuarios("fernanda", "123456", 2)); // Vendedor
        usuarios.put("cliente", new Usuarios("cliente", "123456", 1)); // Cliente


        EditText loginDigitado = findViewById(R.id.editTextUsuarioEntrar);
        EditText senhaDigitada = findViewById(R.id.editTextSenhaEntrar);
        String login = loginDigitado.getText().toString();
        String senha = senhaDigitada.getText().toString();

        if (usuarios.containsKey(login)) { // Em usuarios contem o login que foi digitado?
            Usuarios usuario = usuarios.get(login); // Se tiver o usuario vai receber esse login

            // Agora vamos verificar se esta correto.
            if (usuario.getSenha().equals(senha)) {
                Toast.makeText(getApplicationContext(), "Dados corretos - Nivel de acesso" + usuario.getNivel(), Toast.LENGTH_LONG).show();

                // Decisao baseada no nivel de acesso
                if (usuario.getNivel() > 2) {
                    Intent intent = new Intent(this, PaginaAdministrador.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, PaginaCliente.class);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Dados incorretos!", Toast.LENGTH_LONG).show();
            }


        }
    }

    public void esqueciMinhaSenha(View view) {
        Toast.makeText(getApplicationContext(), "Opcao ainda não disponivel.", Toast.LENGTH_LONG).show();
    }
}