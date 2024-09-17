package Metodos.Void;

class Funcionario {
    /*
    * Classe podemos ter apenas metodos ou apenas atributos
    *  */

    double salario = 0; // Atributo

    // Metodos (Void = Metodos sem retorno)
    void calcularSalarioComDesconto (){
        double valorLiquido = salario - 300;
        System.out.println("Valor liquido: " + valorLiquido);
    }

}



class Metodo {
    public static void main(String[] args) {
        // Metodos com retorno e sem retorno.

        // Parte do APP onde jogamos o salario bruto
        Funcionario alleph = new Funcionario();
        alleph.salario = 3600.00;

        // Parte do app que exibimos o salario liquido
        alleph.calcularSalarioComDesconto();



    }
}