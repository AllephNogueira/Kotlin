package Interface;

abstract class Pessoa {
    public String nome;
    public int idade;

    // Metodos/Açoes
    void seAlimentar() {
        System.out.println("Alimentando");
    }

}


class DesenvolvedorAndroid extends Pessoa {
    void criarAplicativos(){};

}


class Jornalista extends Pessoa implements Presidente{
    void escreverNoticia (){};

    // Como eu implementei o contrato eu sou obrigado a ter o metodo aqui dentro.
    @Override // Estamos sobrescrevendo o metodo.
    public void candidatarSeACargoPolitico() {
        System.out.println("Candidando Politico");
    } // Imagina esses metodos

}

class Principal {
    public static void main(String[] args) {
        DesenvolvedorAndroid devAndroid = new DesenvolvedorAndroid();
        devAndroid.criarAplicativos();


        Jornalista jornalista = new Jornalista();
        jornalista.candidatarSeACargoPolitico();
    }
}

