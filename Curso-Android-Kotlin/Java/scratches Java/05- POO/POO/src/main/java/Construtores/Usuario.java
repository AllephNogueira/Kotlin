package Construtores;

public class Usuario {

    // Atributos
    String email;
    String senha;
    int telefone;


    // Vamos imaginar uma sobrecarga de metodos, imagina que o usuario escolheu email e senha
    // Entao ele vai cair no construtor 1
    // Se ele escolheu email e telefone ele vai cair no construtor 2

    // Construtor 1
    Usuario (String email, String senha) {
        this.email = email;
        this.senha = senha;

        System.out.println("Construtor 1 executado!");
        // Aqui podemos verificar se o usuario existe, se vamos cadastrar, podemos fazer uma serie de informações dentro do proprio construtor.
    }

    Usuario(String email, int telefone) {
        this.email = email;
        this.telefone = telefone;
        System.out.println("Construtor 2 executado!");
    }

    // Metodo são açoes.
    void verificarUsuarioLogado () {
    }


}


class Princial {
    public static void main(String[] args) {

        // Exemplo usuario escolheu construtor 1
        Usuario alleph = new Usuario("allephn@hotmail.com.br", "allephnogueira");

        // Exemplo usuario 2 escolheu construtor 2
        Usuario user2 = new Usuario("allephn@hotmail.com.br", 75575694);


        alleph.verificarUsuarioLogado();
        user2.verificarUsuarioLogado();
    }
}
