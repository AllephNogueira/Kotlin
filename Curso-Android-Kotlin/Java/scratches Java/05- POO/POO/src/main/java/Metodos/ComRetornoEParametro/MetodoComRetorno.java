package Metodos.ComRetornoEParametro;

public class MetodoComRetorno {

    double salario;

    // Aqui eu digito o tipo de dado que eu quero retornar
    double calcularValorDoSalario() {
        double valorReal = salario - 300;
        return valorReal;
    }


    // Devemos fazer o main se quiser executar os codigos aqui dentro.
    public static void main(String[] args) {
        MetodoComRetorno alleph = new MetodoComRetorno();

        alleph.salario = 3600;
        System.out.println(alleph.calcularValorDoSalario());
    }
}


