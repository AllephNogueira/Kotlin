package GetterESetter;

public class GetterESetterJava {
    private String nome = "";
    private int idade = 0;

    public String getNome() {
        return nome.toUpperCase();
    }

    public void setNome(String nome) {
        /**
         * Aqui vamos fazer um exemplo de verificar se o nome é maior que 10 letras
         * Antes de adicionar o nome
         */
        if (nome.length() > 10) {
            this.nome = nome;
        } else {
            System.out.println(STR."""
Nome precisa ter mais que 10 letras
Você digitou apenas: \{nome.length()}""");
        }

    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
