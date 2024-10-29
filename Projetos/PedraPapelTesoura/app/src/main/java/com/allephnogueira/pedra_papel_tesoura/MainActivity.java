package com.allephnogueira.pedra_papel_tesoura;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int quantidadeDeVitorias = 0;
    private int quantidadeDeDerrotas = 0;
    private int quantidadeDeEmpates = 0;

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


    public void pedra(View view){
        verificarGanhador("Pedra");
        alterarCorImagem("Pedra");
    }

    public void papel(View view){
        verificarGanhador("Papel");
        alterarCorImagem("Papel");

    }

    public void tesoura(View view){
        verificarGanhador("Tesoura");
        alterarCorImagem("Tesoura");
    }



    private void verificarGanhador(String escolhaUsuario){
                // AGORA VAMOS VERIFICAR QUEM GANHOU

        // USUARIO CLICOU EM
        System.out.println("Item clicado: " + escolhaUsuario);

        // ESCOLHA DO APP FOI
        String escolhaDoApp = gerarEscolhaAleatoriaApp();
        System.out.println("Escolha da maquina: " + escolhaDoApp);

        // AGORA VAMOS FAZER A CONDIÇAO PARA DIZER QUEM GANHOU
        TextView textResultado = findViewById(R.id.textResultado);


        // Testando as condições para ver quem é o ganhador
        if (    (escolhaDoApp == "Pedra" && escolhaUsuario == "Tesoura") ||
                (escolhaDoApp == "Papel" && escolhaUsuario == "Pedra") ||
                (escolhaDoApp == "Tesoura" && escolhaUsuario == "Papel")){ // App ganhou
            textResultado.setText("Você perdeu :(");
            contagemVitoriaEDerrota(false);



        } else if ((escolhaUsuario == "Pedra" && escolhaDoApp == "Tesoura") ||
                  (escolhaUsuario == "Papel" && escolhaDoApp == "Pedra") ||
                  (escolhaUsuario == "Tesoura" && escolhaDoApp == "Papel")) { // Usuario ganhou
            textResultado.setText("Você ganhou :)");
            contagemVitoriaEDerrota(true);

        } else { // Empatou.
            textResultado.setText("jogo empatou :(");

        }
        resetarCorDaImagem(); // Metodo para redefinir a cor da imagem.
    }


    // GERAR A ESCOLHA DO APLICATIVO ALEATORIA.
    private String gerarEscolhaAleatoriaApp(){
        String[] opcoes = {"Pedra", "Papel", "Tesoura"};
        int numeroAleatorio = new Random().nextInt(3);


        // Pegando a referencia da imagem.
        ImageView imagemApp = findViewById(R.id.image_app);

        // Aqui vamos retornar a opcao (Pedra, Papel, Tesoura"
        // De acordo com o nome vamos inserir a nova imagem.
        String escolhaApp = opcoes[numeroAleatorio];

        // Trocando a imagem de acordo com a escolha do usuario.
        switch (escolhaApp) {
            case "Pedra":
                imagemApp.setImageResource(R.drawable.pedra);
                break;
            case "Papel":
                imagemApp.setImageResource(R.drawable.papel);
                break;
            case "Tesoura":
                imagemApp.setImageResource(R.drawable.tesoura);
                break;
            default:
                System.out.println("Opcão não encontrada!");
        }
        return opcoes[numeroAleatorio];
    };


    private void alterarCorImagem(String imagemQueFoiClicada) {
        if(imagemQueFoiClicada == "Pedra") {
            ImageView imagemDestaque = findViewById(R.id.imagePedra);
            imagemDestaque.setBackgroundColor(Color.YELLOW);
        } else if (imagemQueFoiClicada == "Papel") {
            ImageView imagemDestaque = findViewById(R.id.imagePapel);
            imagemDestaque.setBackgroundColor(Color.YELLOW);
        }else {
            ImageView imagemDestaque = findViewById(R.id.imageTesoura);
            imagemDestaque.setBackgroundColor(Color.YELLOW);
        }
    }

    private void resetarCorDaImagem() {
        ImageView imagemPedra = findViewById(R.id.imagePedra);
        ImageView imagemPapel = findViewById(R.id.imagePapel);
        ImageView imagemTesoura = findViewById(R.id.imageTesoura);

        imagemPedra.setBackgroundColor(Color.TRANSPARENT);
        imagemPapel.setBackgroundColor(Color.TRANSPARENT);
        imagemTesoura.setBackgroundColor(Color.TRANSPARENT);
    }

    // CONTABILIZAR VITORIAS E DERROTAS

    private void contagemVitoriaEDerrota(boolean opcao){
        if (opcao){ // Se opcao for verdadeira
            quantidadeDeVitorias ++;

            TextView textoVitoria = findViewById(R.id.textVitoria);
            String mensagem = quantidadeDeVitorias > 1? "VITÓRIAS: " : "VITÓRIA: ";
            textoVitoria.setText(mensagem + quantidadeDeVitorias);
        } else if (!opcao) {
            quantidadeDeDerrotas ++;

            TextView textoDerrota = findViewById(R.id.textDerrota);
            String mensagem = quantidadeDeDerrotas > 1? "DERROTAS: " : "DERROTA: ";
            textoDerrota.setText(mensagem + quantidadeDeDerrotas);
        }
        else {
            quantidadeDeEmpates++;
        }
    }
}