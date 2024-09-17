package Metodos.ParametroTipoClass;

public class ParametroComObjeto {

    // Vamos imaginar que eu preciso de um parametro do tipo class/objeto

    String nome;
    int peso;
    int idade;

    void exibirUsuario (ParametroComObjeto usuario) {
        usuario.nome = nome;
        usuario.peso = peso;
        usuario.idade = idade;

        System.out.println(usuario.nome + " " + usuario.peso + " " + usuario.idade);
    }


    public static void main(String[] args) {
        // Agora vamos criar o objeto
        ParametroComObjeto usuario = new ParametroComObjeto();

        usuario.nome = "Alleph";
        usuario.peso = 82;
        usuario.idade = 30;


        usuario.exibirUsuario(usuario);
    }
}
