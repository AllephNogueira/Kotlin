package Variaveis.NullSafaty;


class Carro{
    String cor = "Prata";

    void acelerar (){}
}

public class NullSafaty {
    public static void main(String[] args) {

    }

    void cliqueBotao(){

        // Imagina que não instanciamos o carro
        Carro carro = null;

        // Imagina que o carro vai ser instanciado em outro local, mas acabou não sendo instanciado


        // E ai tentamos usar o carro
        carro.acelerar();

        // E ai iria gerar o erro NullPointerExecption
    }
}
