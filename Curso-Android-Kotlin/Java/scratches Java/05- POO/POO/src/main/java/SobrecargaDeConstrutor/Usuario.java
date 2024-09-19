package SobrecargaDeConstrutor;

public class Usuario {

    // Atributos
    String email;
    String senha;


    // Construtor
    Usuario (String email, String senha) {
        this.email = email;
        this.senha = senha;

        System.out.println("Construtor executado!");
    }

    // Metodo
    void verificarUsuarioLogado () {
    }




}


class Princial {
    public static void main(String[] args) {
        Usuario alleph = new Usuario("allephn@hotmail.com.br", "allephnogueira");


        alleph.verificarUsuarioLogado();
    }
}
