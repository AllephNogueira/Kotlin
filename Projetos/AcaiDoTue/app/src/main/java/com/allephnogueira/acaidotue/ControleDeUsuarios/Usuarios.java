package com.allephnogueira.acaidotue.ControleDeUsuarios;

public class Usuarios {
    public String login;
    public String senha;
    int nivel;

    public Usuarios(String login, String senha, int nivel) {
        this.login = login;
        this.senha = senha;
        this.nivel = nivel;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public int getNivel() {
        return nivel;
    }


    public void controlandoAcesso(){

    }
}
