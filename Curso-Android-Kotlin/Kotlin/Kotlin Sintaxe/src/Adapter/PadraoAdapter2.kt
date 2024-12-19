package Adapter

/**
 * Imagina que: Estamos com 1 tomada de 2 pinos.
 * E compramos um computador que precisa de 3 pinos, então vamos precisar de um adaptador.
 * Para transformar essa tomada de 3 pinos em 2 pinos.
 */


interface Conector {
    /**
     * Aqui criamos essa interface porque toda tomada vai ser um conector.
     * Essa interface é um contrato que define como um conector deve ser.
     */
    fun quantidadeDePinos() : Int
    fun ligarAparelho()
}

class TomadaAntiga (val conector: Conector) { // Imagina que aqui seja uma tomada que fique na parede.
    // Jogando nos parametros um variavel do tipo conector, agora podemos passar qualquer tipo de conector no futuro.
    fun passarEnergia() {

        val quantidadeDePinos = conector.quantidadeDePinos()
        if (quantidadeDePinos == 2 ) {
            // Lembra que falamos que a tomada antiga da casa precisa de 2 pinos?
            // E os novos padrões vem com 3 pinos?
            // Então vamos testar para verificar se esta vindo realmente com 2 pinos.
            conector.ligarAparelho()
            println("Quantidade de pinos: $quantidadeDePinos")
            println("Passando energia")
        }else {
            println("Essa tomada só funciona com 2 pinos, você tentou usar $quantidadeDePinos")
        }

    }
}

class ConectorNovoPadrao : Conector { // Aqui é o conector que vai na tomada
    override fun quantidadeDePinos() : Int {
        return 3
    }

    override fun ligarAparelho() {
        println("Aparelho esta ligado.")
    }
}



class ConectorAdaptador (val conectorNovoPadrao: ConectorNovoPadrao) : Conector {
    // Lembrar que ConectorAdaptador ele vai implementar porque ele também vai ser um CONECTOR
    // Aqui estamos dizendo que a saida do adaptador vai ser 2 pinos.
    override fun quantidadeDePinos(): Int {
        return 2
    }


    // Aqui em baixo estamos repetindo codigo, lembrar da regra de nunca fazer isso
    // Imagina que: Temos mais de 50 linhas de codigo.
    // Então vamos chamar o ConectorNovoPadrao e pegar o metodo dele.
    override fun ligarAparelho() {
        //println("Aparelho ligado") ++ Imagina que temos 50 linhas de codigo aqui

        conectorNovoPadrao.ligarAparelho() // Agora sim esta da maneira correta.
    }


}




fun main() {

    val conectorNovoPadrao = ConectorNovoPadrao()

    val conectorAdaptador = ConectorAdaptador(conectorNovoPadrao) // Agora aqui ele vai esperar receber o conector novo, porque estamos puxando os metodos dele la em cima para nao precisar escrever de novo

    // Agora aqui em baixo, vamos dizer oque vamos tentar colocar na tomadaAntiga (Tomada que fica na parede)

    val tomadaAntiga = TomadaAntiga(conectorAdaptador)

    tomadaAntiga.passarEnergia()

}