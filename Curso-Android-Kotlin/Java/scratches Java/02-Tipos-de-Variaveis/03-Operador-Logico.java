class Scratch {
    public static void main(String[] args) {
        boolean operadorLogico = 10 >= 15; // 10 maior ou igual a 15?
        System.out.println("Teste de operador logico: " + operadorLogico);

        // Programa mercado livre, compras maior que 300,00R$ ganham 30% de desconto

        double valorDaCompra = 300;
        int idade = 41;

        boolean opcao = valorDaCompra > 300;


        System.out.println("Eu tenho o desconto? " + opcao);

        if (valorDaCompra >= 300 || idade >= 50) {
            System.out.println("Voce tem 30% de desconto");
        }
    }
}