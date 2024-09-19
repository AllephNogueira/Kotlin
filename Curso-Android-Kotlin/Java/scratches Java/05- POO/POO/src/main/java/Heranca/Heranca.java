package Heranca;


class Animal {
    public String name;
    public String cor;
    public int peso;


    public void correr(){
        System.out.println("Correr");
    }
    public void dormir(){
        System.out.println("Dormir");
    }

}


class Cachorro extends Animal{

    public void latir(){
        System.out.println("Latir");
    }
}

class Gato extends Animal{

    public void miar(){
        System.out.println("Miar");
    }

}



public class Heranca {

    // Onde vamos herda tudo.
    public static void main(String[] args) {


        Cachorro c = new Cachorro();// Observe que aqui o metodo esta dentro da classe Animal, mas estamos utilizando.
        c.name = "Crixus";
        c.cor = "Branco/Amarelo";
        c.peso = 5;
        c.dormir();
        c.latir();


        Gato gato = new Gato(); // Observe que aqui o metodo esta dentro da classe Animal, mas estamos utilizando.
        gato.name = "Anastacia";
        gato.cor = "Marrom";
        gato.peso = 10;
        gato.correr();
        gato.miar();

    }
}




