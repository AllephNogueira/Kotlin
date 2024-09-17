package SobreCarga;
// Sobre carga e quando temos varios metodos com o mesmo nome, mas o JVM sabe qual utlizar na hora que ele e chamado

// Vamos criar 2 metodos
// Nome e Sobrenome
// Nome, sobrenome, telefoe

import java.math.BigInteger;

public class SobreCarga {
    private String nome;
    private String email;
    private String telefone;

    public static void dadosCadastro(String nome, String email) {
        System.out.println("Dados cadastrados foram:" + nome + " - " + email);
    }

    public static void dadosCadastro(String nome, String email, String telefone) {
        System.out.println("Dados cadastrados foram:" + nome + " - " + email + " - " + telefone);
    }

    public static void dadosCadastro(Long telefone) {
        System.out.println("Telefone de contato: " + telefone);
    }
}
