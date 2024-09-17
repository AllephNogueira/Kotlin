class Scratch {
    public static void main(String[] args) {
        // Array multidimensional
        // Repara que precisamos utilizar 2 [] [] para dizer que "dentro do meu array tem outro array"
        String[][] conversas = {

                {"Alleph", "30"}, // posição 0
                {"Fernanda", "28"},
                {"Crixus", "7"},
                {"Amora", "6"}
        };

        // Exibindo os dados

        // Lembrar que quando colocamos 0 estamos exibindo nosso primeiro array
        // Agora na segunda casa estamos exibindo a posição dentro do array
        // Imagina um array [0] {"Alleph", "30"}, // posição 0
        // Agora imagina a posição [0][1] == {"30"}
        System.out.println("Nome e idade: " + conversas[0][0] + " - " + conversas[0][1]);


        // Agora imprimindo separado
        System.out.println("Nome: " + conversas[0][0]);
        System.out.println("Idade: " + conversas[0][1]);
    }
}