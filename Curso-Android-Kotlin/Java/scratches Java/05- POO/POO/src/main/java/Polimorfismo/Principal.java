package Polimorfismo;

// Polimorfismo

// Poli == muitas
// Morfo == formas
// É onde um metodo pode ter varias formas diferentes.
// O mesmo metodo pode ter varios comportamentos diferentes, vamos imaginar um metodo emitirSom()
// O gato emitirSom() faz um barulho e o cachorro faz outro, então vamos ter 2 formas em apenas 1 metodo.


/*
*
*                   TIPOS DE POLIMORFIMOS
* ---- SOBRECARGA DE METODOS    /    SOBREPOSIÇÃO
*
* */


public class Principal {
    public static void main(String[] args) {
        Cachorro cachorro = new Cachorro(); // Vamos imaginar que o cachorro
        cachorro.mover();// Ele vai correr
        cachorro.emitirSom();
        cachorro.comer();
        cachorro.latir("Alleph");
        // Agora vamos usar um atributo para definir a quantidade de patas utilizando o polimorfismo.
        cachorro.quantidadeDePatas = 4;
        cachorro.correr();


        System.out.println("\n----------------\n");


        Passaro passaro = new Passaro();
        passaro.mover(); // O Passaro ele deve voar
        passaro.emitirSom();
        passaro.comer();
        // Agora vamos usar um atributo para definir a quantidade de patas utilizando o polimorfismo.
        passaro.quantidadeDePatas = 2;
        passaro.correr();

        // Então estamos utilizando o mesmo metodo, mas queremos respostas diferentes.
    }



}
