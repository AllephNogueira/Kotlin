package Vetores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vetor {
    public static void main(String[] args) {

        // VETOR
        // Tipo do dado
        // Nome do vetor
        // novo vetor - tipo do dado
        // Quantidade que pode ser adicionado.

        // >>>> ISSO É CHAMADO DE VETOR DIRECIONAL <<< //
        String listaDeNomes[] = new String[5];
        listaDeNomes[0] = "Alleph";
        listaDeNomes[1] = "Fernanda";
        listaDeNomes[2] = "Crixus";
        listaDeNomes[3] = "Amora";
        listaDeNomes[4] = "Anastacia";

        String mesesDoAno[] = new String[12];
        mesesDoAno[0] = "January";
        mesesDoAno[1] = "February";
        mesesDoAno[2] = "March";
        mesesDoAno[3] = "April";
        mesesDoAno[4] = "May";
        mesesDoAno[5] = "June";
        mesesDoAno[6] = "July";
        mesesDoAno[7] = "August";
        mesesDoAno[8] = "September";
        mesesDoAno[9] = "October";
        mesesDoAno[10] = "November";
        mesesDoAno[11] = "December";

        Arrays.stream(mesesDoAno).forEach(System.out::println);
        System.out.println("\n\n");

        // Criando vetor e instanciando ao mesmo tempo

        String listaDeCarros[] =  {"Gol", "Celta", "Siena", "New Fiesta", "Ford Ka", "New Fiesta", "C4 Lounge"};
        Arrays.stream(listaDeCarros).forEach(System.out::println);


        // Criando um List
        List<String> listaDeVeiculos = new ArrayList<>();
        listaDeVeiculos.add("Gol quadrado");
        listaDeVeiculos.add("GM Celta");

        listaDeVeiculos.stream().toList().forEach(System.out::println);

        // AGORA VAMOS CRIAR UM BI-DIRECIONAL //

        String listaVeiculo2[][] = new String[3][2]; // 3 Linhas 2 colunas

        // Reparar que estamos usando o primeiro espaço para marca
        // Segundao espaço para modelo.
        // Aqui vamos poder adicionar 3 marcas de carros
        listaVeiculo2[0][0] = "GM";
        listaVeiculo2[0][1] = "Celta";

        listaVeiculo2[1][0] = "Fiat";
        listaVeiculo2[1][1] = "Siena";

        listaVeiculo2[2][0] = "Ford";
        listaVeiculo2[2][1] = "Ford KA";

        System.out.println("Lista de modelo: " + listaVeiculo2[0][1]);


        Arrays.stream(listaVeiculo2).forEach(System.out::println);

        // Usando streams para imprimir todos os elementos do array
        Arrays.stream(listaVeiculo2) // Cria um stream a partir do array bidimensional
                .flatMap(Arrays::stream) // Achata o array bidimensional em um stream unidimensional
                .forEach(System.out::println); // Imprime cada elemento do stream




        // AGORA VAMOS CRIAR UM VETOR SE BASEANDO POR UMA CLASSE //

        CadastroUsuario usuario[] = new CadastroUsuario[3];

        // Vamos inicializar o objeto
        usuario[0] = new CadastroUsuario();

        // Adicionando os dados do objeto
        usuario[0].setNome("Alleph");
        usuario[0].setEmail("allephn@hotmail.com.br");
        usuario[0].setSenha("1234");

        System.out.println(usuario[0].getNome());


        // Agora vamos fazer uma lista de 10 nomes
        // Agora podemos adicionar os usuarios 1,2,3,4,5,6...
        CadastroUsuario usuarios[] = new CadastroUsuario[10];
        for (int i = 0; i < 10; i++) {
            usuarios[i] = new CadastroUsuario(); // Inicializando varios objetos
        }

        usuarios[1].setNome("Alleph Nogueira");
        usuarios[1].setEmail("allephn@hotmail.com.br");
        usuarios[1].setSenha("1234");

        System.out.println(usuarios[1]);



        Conta minhasContas[] = new Conta[10];
        minhasContas[0].setSaldo(1000);
        minhasContas[1].setSaldo(3200);

        System.out.println(minhasContas[1]);






    }
}
