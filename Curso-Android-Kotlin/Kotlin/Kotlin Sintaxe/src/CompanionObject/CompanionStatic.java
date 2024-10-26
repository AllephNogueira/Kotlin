package CompanionObject;

public class  CompanionStatic {

    static boolean verificarUsuarioLogado () { // Metodo
        return true;
    }

    final static int QUANTIDADE_DE_ACESSO = 10; // Atributo

}


class Principal {
    public static void main(String[] args) {

        CompanionStatic teste = new CompanionStatic(); // Instanciando.

        boolean retorno = CompanionStatic.verificarUsuarioLogado(); // Sem instancia por causa do static
        int quantidadeDeAcesso = CompanionStatic.QUANTIDADE_DE_ACESSO; // Repara que também podemos utilizar atributo de uma classe

        System.out.println("Usuario esta logado: " + retorno);
        System.out.println("Quantidade de acesso: " + quantidadeDeAcesso);

    }
}