package com.allephnogueira.frasesdodia;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int ultimoIndice = -1; // Inicializa com um valor que não está no intervalo do array

    // Atributos

    String[] frases = {"Ame a todos, confie em todos não seja injusto com ninguém.",
            "Não limite os seus desafios. Desafie os seus limites.",
            "Viver cada dia como se fosse o último é uma ótima oportunidade para aprender a amar cada segundo do seu dia.",
            "Se o plano não funcionar, mude o plano, não a meta.",
            "Você é mais valente do que acredita, mais forte do que parece e está mais preparado do que imagina.",
            "Para ter sucesso, você deve eliminar dúvidas, aceitar desafios e rejeitar qualquer negatividade externa.",
            "Tente mover o mundo - o primeiro passo será mover a si mesmo."
    };

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


    public void gerarFrase (View view) {

        TextView gerarNovaFrase = findViewById(R.id.textResultado);


        int novoIndice;
        do {// Se o numero for igual ele vai repetir.
            novoIndice = new Random().nextInt(frases.length); // Instanciando e pegando o numero ao mesmo tempo.
        }while (novoIndice == ultimoIndice); // Se forem numeros diferentes ele vai sair do loop, se for igual ele faz de novo.

        // Salva a frase nova.
        ultimoIndice = novoIndice;

        gerarNovaFrase.setText(frases[novoIndice]);

    }

    public void exibirFrases (View view) {

        // Tipo do item Resultado = TextView
        // findByViewId = Encontre a view pelo id...
        TextView texto = findViewById(R.id.textResultado);
        String textoResultado = "";

        // Aqui ele vai percorrer o array
        // Vai pegar item a item e vai colocar dentro de frase.
        for (String frase: frases) {


            // Aqui vamos pegar o texto que não tem nada (0)
            // Colocar o novo texto nele (01)
            // Depois incrementar (01) com o novo texto (02)
            // Ficando todos os textos na mesma variavel.
            textoResultado = textoResultado + frase + "\n";
        }

        // Exibir na interface
        // texto é nossa TextView
        // setText é = a insira o texto
        // o parametro é onde esta nosso texto.
        texto.setText(textoResultado);

    }
}