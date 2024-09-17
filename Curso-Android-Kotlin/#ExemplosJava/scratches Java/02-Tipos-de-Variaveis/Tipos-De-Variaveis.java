class Scratch {
    public static void main(String[] args) {
        /*
        TIPOS DE VARIAVEIS

        byte = numeros entre -128 e 127
        short = numeros entre -32.768 e 32.767
        int
        long
        float = numeros com ate 7 casas decimais
        double = numeros com ate 15 casas decimais
        boolean = verdadeiro ou falso
        char = armazena um caractere

        */

        // TIPOS PRIMITIVOS = mais antigos.
        byte idade = 30;
        int numeroCasa = 26;
        float tabelaFipeDoCarro = 52.562f; // Lembrar que no float você precisa definir o f no final
        double tabelaDoCarro = 52.562; // Aqui não precisamos do f
        boolean botaoVisivel = true; // Aqui estamos dizendo que o botao vai esta visivel.
        char caractere = 'A'; // Lembrar que aqui vamos utilizar em aspas simples.

        // classes wrapper

        // Aqui vamos ter mais opçoes.
            // EX: Comparações...
        // Lembrar que o tipos primitivos sempre começam com a letra Maiuscula.
        Byte idadeC = 30;
        Integer numeroCasaC = 26;
        String nomeDaRua = "Travessa Duarte Branco 26";


        // Saida
        System.out.println("Idade de Alleph é: " + idade);
        System.out.println("Mora na rua: " + nomeDaRua + "numero: " + numeroCasa);
        System.out.println("Preço do meu carro: " + tabelaFipeDoCarro + "R$");


        // Agora vamos imaginar que eu queira mudar o ponto pelo virgula, então vamos utilizar o replace.
        String precoCarro = "Preço do meu carro: " + tabelaFipeDoCarro + "R$";
        System.out.println(precoCarro.replace(".", ","));

    }
}