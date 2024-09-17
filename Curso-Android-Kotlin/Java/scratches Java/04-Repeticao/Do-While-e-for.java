class Scratch {
    public static void main(String[] args) {
        // Do while
        int numero = 101;

        do {// faça
            // A diferença do do é que primeiro ele vai fazer para depois ele olhar para ver se esta satisfeito a condição.
            System.out.println("Testando a condiçao: " + numero);

            // Agora vamos imaginar que usamos o numero 101 na variavel
            // Então como estamos usando o DO WHILE, primeiro ele vai incrementar para 102 e depois ele vai sair do loop
            // Ja no WHILE ele não iria entrar dentro do loop, porque o WHILE primeiro ele verifica antes de entrar.
            numero++;
        }while (numero < 100); // enquanto

        System.out.println("Testando condição fora: " + numero);

        //////////////////////////////// FOR ///////////////////////////////////////

        // For = para
        for (int i = 0; i <= 10; i++) {
            System.out.println("Numero: " + i);

        }
    }
}