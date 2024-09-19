package Heranca;

public class Cliente extends ContaBancaria {
    public void exibirSaldo() {
        // Lembrar que para instanciar precisamos fazer isso dentro de um metodo
        // Ou instanciar dentro de um metodo Main.
        Cliente cliente = new Cliente();
        cliente.saldo = 100.00;
        System.out.println(cliente.saldo);
    }

}

class Principal{
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.exibirSaldo();
    }
}

