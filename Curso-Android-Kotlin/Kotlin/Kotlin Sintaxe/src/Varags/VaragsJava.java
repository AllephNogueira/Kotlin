package Varags;

public class VaragsJava {
    private String nome;


    /**
     *
     * para usar o varags, vamos utilizar os 3 pontos...
     * Atenção ele deve ser sempre utilizado no final, como ultimo parametro.
     */

    public void salvarTelefone(int codigoPostal, String... telefones){
        for (String telefone : telefones) {
            System.out.println(telefone);
        }
//        System.out.println("Telefone salvo!" + telefones);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
