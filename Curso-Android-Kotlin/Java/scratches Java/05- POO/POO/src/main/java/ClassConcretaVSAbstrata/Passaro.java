package ClassConcretaVSAbstrata;

// Concreta
public class Passaro extends Animal {


    // Vazio para instanciar sem nada
    public Passaro() {};
    // Podemos instanciar ja com algo dentro
    public Passaro(String nome, int idade, String peso) {
        super(nome, idade, peso);
    }

}

class Principal {
    public static void main(String[] args) {
        Passaro p = new Passaro("Calopsita", 7, "100g");

        System.out.println("Cliente: " + p.nome + " Idade: " + p.idade + " Peso: " + p.peso);
    }
}
