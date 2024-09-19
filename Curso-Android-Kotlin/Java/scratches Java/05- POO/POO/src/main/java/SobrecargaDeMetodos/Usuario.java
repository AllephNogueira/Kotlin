package SobrecargaDeMetodos;

import Encapsulamento.ContaBancaria;

// Aqui temos outra sobrecarga de metodo
    // Imagina um app de filtro de imagem
    // O usuario tem filtro preto e branco
    // Onde um voce aplica somente a imagem e no outro voce aplica a imagem e voce define o filtro.
    // Entao temos 2 metodos diferentes, mas com o mesmo nome.
    // Se o usuario entrar somente 1 a imagem ele vai usar o primeiro metodo
    // Se o usuario entrar com 2 informações vamos cair no metodo 2.
    // **** LEMBRAR QUE NAO PODEMOS TER OS METODOS COM A MESMA ASSINATUAR
    // Ou seja 2 metodos com 2 assinaturas inteiro, 2 metodos com a mesma assinatura String...
    // Se precisar colocar igual devemos mudar o nome do metodo.
class Filtro {
    void pretoEBranco(String imagem) {
        System.out.println("Filtro preto e branco aplicado.");
    }
    void pretoEBranco(String imagem, int nivelPretoEBranco) {
        System.out.println("Filtro preto e branco aplicado, com nivel.");
    }

    void pretoEBranco (String imagem, double inserirTomCinza) {
        System.out.println("Inserir imagem e cor cinza.");
    }


}

public class Usuario extends Filtro{ // Aqui se reparar estamos herdando os metodos de filtro.
    String email;
    String senha;
    String telefone;


    // Observa que temos 2 metodos com o mesmo nome, mas com ações diferentes.
    // Oque vai fazer diferença são os dados que vamos entrar.

    void logar(String email, String senha) {
        this.email = email;
        this.senha = senha;
        System.out.println("Usuario logado, com e-mail e senha.");
    }

    void logar(String telefone) {
        this.telefone = telefone;
        System.out.println("Usuario logado, com telefone.");
    }


}

class Principal {
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.logar("allephn@hotmail.com.br", "123456");


        Filtro filtro = new Filtro();
        filtro.pretoEBranco("Filtro 1", 60);



        usuario.pretoEBranco("imagem alleph"); // Repara que aqui estamos herdando de outra classe, o metodo.


    }
}
