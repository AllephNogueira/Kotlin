package Polimorfismo;

public class Passaro extends Animal{


    //Sobrescrita de metodo.
    // Repara que na classe pai, a forma é diferente, quando chamamos o objeto passaro, repara que ele muda a forma que vai se mover.
    public void mover(){
        System.out.println("Voando");
    }

    public void emitirSom(){
        System.out.println("Piu... Piu... Piu...");
    }

    public void comer(){
        System.out.println("Comendo... Sementes");
    }

    @Override // é uma anotação que esta dizendo que esse trecho do codigo é uma sobescrita que a classe FILHA(Passaro) esta fazendo na classe PAI(Animal)
    public void correr(){
        super.correr(); // <- Implementação padrao = não repetir codigo e sim reaproveitar.
        // Sobreposição ---- saber mais na classe Cachorro
        System.out.println("Passaro de: " + this.quantidadeDePatas + " patas");
    }
}
