package Polimorfismo;

public class Animal {
    public String name;
    public int idade;
    public String sexo;
    public int quantidadeDePatas;


    public void emitirSom() {}

    public void comer(){
        System.out.println("Comendo...");
    }

    public void mover(){
        System.out.println("Correndo...");
    }

    public void correr(){
        System.out.println("Correr como um ");
    }
}
