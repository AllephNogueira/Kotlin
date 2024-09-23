package Polimorfismo;

public class Cachorro extends Animal{



    // Sobrecarga de metodos, são metodos com assinaturas diferentes.
    public void latir(){
        System.out.println("Latir");
    }
    public void latir(String pessoa){
        System.out.println("Latir para " + pessoa);
    }


    //Repara que esse metodo ja tem na classe "PAI"
    // Agora vamos o conceito de sobrescrita de metodo.
    public void mover(){
        System.out.println("Correndo com 4 patas.");
    }


    public void emitirSom(){
        System.out.println("Au... Au... Au...");
    }


    public void comer(){
        System.out.println("Comendo... Raçao...");
    }

    @Override
    public void correr(){
        super.correr();
        // Agora a mensagem vai ficar completa
        // Estamos acessando a primeira parte de classe pai que é: Correr como um
        // e a segunda parte da subclasse que é: cachorro de...

        // Ficando assim, correr como um cachorro de ....
        System.out.println("Cachorro de: " + quantidadeDePatas + " patas.");
    }
}
