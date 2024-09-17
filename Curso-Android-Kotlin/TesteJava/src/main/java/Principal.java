import Veiculo.Carro;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Teste de Java");


        Carro carro = new Carro();

        carro.setCambio("Automatico");
        carro.setTipoDeCombustivel("Flex");
        carro.setMarca("Citroen");
        carro.setPlaca("PPN-6B11");

        carro.setTipoDeOleo(carro);


        System.out.println("Esse é o meu veiculo " + carro);
    }
}
