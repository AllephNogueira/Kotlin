class Scratch {
    public static void main(String[] args) {
        // Criando um array
        // Imagina uma rede social, e esses de baixo sao os status do pessoal.
        String[] status = {
                "Fui a praia",
                "Fui na casa da minha tia",
                "Vou viajar",
                "Estou estudando kotlin"
        };

        // Fazendo uma postagem individual
        System.out.println("Alleph: " + status[3] );

        //Fazendo a postagem de todos

        for (int c=0; c < status.length; c++) {
            System.out.println(status[c]);
        }

//        for (String s : status) {
//            System.out.println(s);
//        }




    }
}