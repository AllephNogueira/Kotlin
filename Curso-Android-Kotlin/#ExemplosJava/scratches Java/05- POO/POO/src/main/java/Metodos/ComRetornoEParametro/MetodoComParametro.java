package Metodos.ComRetornoEParametro;

public class MetodoComParametro {

    double salario;

    // Aqui eu digito o tipo de dado que eu quero retornar
    double calcularValorDoSalario(double bonus) {
        double valorReal = salario - 300;

        valorReal = valorReal + bonus;
        return valorReal;
    }


    // Devemos fazer o main se quiser executar os codigos aqui dentro.
    public static void main(String[] args) {

        MetodoComParametro alleph = new MetodoComParametro();

        alleph.salario = 3600;

        // Adicionando um bonus para o usuario
        double bonus = 70;
        System.out.println(alleph.calcularValorDoSalario(bonus));
    }
}


