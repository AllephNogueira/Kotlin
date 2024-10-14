package com.allephnogueira.acaidotue.CadastroUsuario;

public class CadastroUsuarioL {
    public String usuario;
    public String email;
    public String senha;
    public String nome;
    public String telefone;

    public CadastroUsuarioL(String usuario, String email, String senha, String nome, String telefone) {
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
