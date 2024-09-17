class Scratch {
    public static void main(String[] args) {
        // Criando um array

        String[] familia = {"Alleph", "Fernanda", "Crixus", "Amora"};

        // Exibindo posição 1
        System.out.println("Homem da familia: " + familia[0]);

        // Quantidade de pessoas adicionadas
        System.out.println("Total de membros da familia: " + familia.length);

        // Exibindo todos os usuarios da familia.
        for (int i = 0; i < familia.length; i++) {
            System.out.println("Usuario n " + i + " é: " + familia[i]);
        }
    }
}