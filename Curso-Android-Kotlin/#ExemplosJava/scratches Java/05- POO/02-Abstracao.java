        /*
         * Oque é abstração?
         *   É oque vamos trazer do mundo real para o nosso sistema.
         *       Entender oque realmente é importante para trazer.
         *
         * */



class Jogador { // Classe utilizam o UpperCamelCase

    // Atributos utlizam o LowerCamelCase
    // Atributos é oque nosso personagem vai ter
    // Imagina um Pikeman do priston.
    String armamento;
    String escudo;
    String armadura;
    String nick; // entidade

    // Metodos
        // Oque é metodo? é oque nosso personagem vai fazer, usar magia, usar escudo..

    void dadosUsuario () {
        System.out.println("Nick: " + nick);
    }
    void atacar(){
        System.out.println("Atacar: " + armamento + "\n");
    }


    // Entidades = Imagina que entidades são as pessoas que estao utilizando o serviço
    // Jogador é um entidade
    // Amigos do jogador é uma entidade
    // Partidas ganhas? é uma entidade..


}



class Scratch {
    public static void main(String[] args) {

        // Vamos imaginar que temos 2 jogador, um é o usuario normal e outro e o inimigo de ICE 1

        // Identidade  (Instancia)
        // é onde vamos atribuir todos os dados ao jogador.
        Jogador alleph = new Jogador();
        alleph.nick = "AllephBR";
        alleph.armamento ="Foice Fênix";
        alleph.escudo = "Escudo Fênix";
        alleph.armadura = "Armadura Fênix";

        alleph.dadosUsuario();
        alleph.atacar();


        // Agora vamos imaginar o inimigo
        Jogador inimigo = new Jogador();
        inimigo.nick = "Bruxa do Templo";
        inimigo.armamento = "Foice da Bruxa";
        inimigo.dadosUsuario();
        inimigo.atacar();
    }
}