package ConstrutoresComHeranca;

public class Cachorro extends Animal {

    // Lembrar que construtores geralmente são publicos
    // Construtor cachorro
    // Lembrar que é normal ter um construtor vazio para que podemos instanciar a classe sem passar nada
    public Cachorro(){}

    // Agora fazemos uma sobrecarga e fazemos um construtor
    // Agora vamos herda os dados
//    public Cachorro(String nome, int quantidadeDePatas){
//        // Lembrar que esses atributos nome e quantidadeDePatas, estamos herdando da classe animal.
//        this.name = nome;
//        this.quantidadeDePatas = quantidadeDePatas;
//    }

    // Aqui estamos fazendo diferente
    // Aqui criamos o construtor la na classe PAI
    // E estamos herdando ele aqui na classe filha, passando os dados.
    public Cachorro(String nome, int quantidadeDePatas){
        // Usando o super é como se a gente estivesse instanciando a classe animal.
        super(nome, quantidadeDePatas);
    }
}


class Principal {
    public static void main(String[] args) {

        Cachorro cachorro = new Cachorro("Crixus", 4);

        System.out.println("Cachorro: " +  cachorro.name);
    }
}