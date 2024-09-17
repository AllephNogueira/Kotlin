package Metodos.ParametroTipoClass;

public class ParametroComObjetoEReturn {

    // Vamos imaginar que eu preciso de um parametro do tipo objeto

    String nome;
    int peso;
    int idade;

    ParametroComObjetoEReturn exibirUsuario (ParametroComObjetoEReturn usuario) {
        usuario.nome = nome;
        usuario.peso = peso;
        usuario.idade = idade;

        return usuario;
    }


    public static void main(String[] args) {
        // Agora vamos criar o objeto
        ParametroComObjetoEReturn usuario = new ParametroComObjetoEReturn();

        usuario.nome = "Alleph";
        usuario.peso = 82;
        usuario.idade = 30;


        usuario.exibirUsuario(usuario);
    }
}

