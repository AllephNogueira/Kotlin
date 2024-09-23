package ClassConcretaVSAbstrata;

// Conceitual
abstract public class Animal {
    // Uma classe abstrata ela não pode ser instanciada, ela é uma classe generica
    // Animal é uma classe que depende das outras
    public String nome;
    public int idade;
    public String peso;


    public Animal(){};
    public Animal(String nome, int idade, String peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
    }
}
