package com.allephnogueira.acaidotue.CadastroUsuario;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.allephnogueira.acaidotue.R;

public class CadastroUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void cadastroUsuario(View view) {
        EditText textoUsuario = findViewById(R.id.editTextUsuarioEntrar);
        EditText textoEmail = findViewById(R.id.editTextEmail);
        EditText textSenha = findViewById(R.id.editTextSenha);
        EditText textNome = findViewById(R.id.editTextNome);
        EditText textTelefone = findViewById(R.id.editTextTelefone);

        String usuario = textoUsuario.getText().toString();
        String email = textoEmail.getText().toString();
        String senha = textSenha.getText().toString();
        String nome = textNome.getText().toString();
        String telefone = textTelefone.getText().toString();

        CadastroUsuarioL novoUsuario = new CadastroUsuarioL(usuario, email, senha, nome, telefone);


        if (!novoUsuario.usuario.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Usuario cadastrado com sucesso.", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), "Erro no cadastrado do usuario.", Toast.LENGTH_LONG).show();
        }

        System.out.println("Usuario cadastrado " + novoUsuario.usuario);
    }


}