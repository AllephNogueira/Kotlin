class Funcionario () {
    // Lembrar que dentro de uma classe, podemos criar atributos e metodos
    // somente atributos
    // somente metodos.



    double salario = 0; // Atributo

    // Metodo
    void calcularSalarioComDesconto (double salario){
        desconto = salario - 20%;
        System.out.println("Bruto: " + salario);
        System.out.println("Liquido: " + desconto);
    }

}



class Scratch {
    public static void main(String[] args) {
        // Metodos com retorno e sem retorno.

        // Imagina um APP e agora estamos na parte de calculo de salario.
        Funcionario alleph = new Funcionario();
        alleph.salario = 3600.00;
        alleph.calcularSalarioComDesconto(alleph.salario);
        
    }
}