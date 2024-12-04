package Interfaces

// DRY = Dont Repeat Yourself (Nao repita codigo) V
// Divisao de responsabilidades (Cada classe é responsavel pela sua ação e somente a sua ação) V
// Classes devem UTILIZAR outras classes e nao criar classes V
// Alto Acomplamento e Baixo acomplamento (Acomplar = ficar proximo / Juntar uma coisa na outra / Ligar uma tomada na outra)



//class Musico(val violao: Violao){ // Classes utilizam classes - Agora vamos passar para esse construtor a nossa classe.
//class Musico(val violao: Violao){ // E se quiser usar bateria? Nao poderiamos porque estamos esperando um tipo de vilao, mas tem uma forma de resolver.

/**
 * Agora vamos imaginar que queremos tratar esses dados como tipos iguais
 * Pra isso vamos criar uma interface
 */

interface Instrumento {
    // Open = Herança
    // Classe pai:
    // Violao pode herda de instrumento, porque ela é filha
    // Bateria pode herda de instrumento, porque ela é filha
    // Mas nunca ao contrario Instrumento nunca pode herda de violao ou bateria
    // Instrumento não pode ser do tipo: Violao
    // Violao pode ser do tipo: Instrumento

    // Precisamos colocar todos os metodos que queremos utilizar nas classes que estao herdando aqui, quem nao tiver aqui nao vai ser considerado como instrumento
    fun sendoTocado()

    //** ATENÇÃO aqui estavamos utilizando uma class abstrata, mas podemos mudar para interface que é muito melhor

}


class Musico(val instrumento: Instrumento){ //Aqui vamos passar o instrumento que é algo mais generico, assim podemos receber todos os tipos de instrumentos(Violao, bateria, guitarra)..
    fun tocar() {
        /**
         * Isso é um problema, classes devem utilizar outras classes e nao criar
         * val violao = Violao() // Alto Acomplamento
         */

        println("Musico tocando!")

        // lembrar que instrumento é a classe pai, e os metodos estao na classe filha, então como podemos chamar?
        // Precisamos fazer instrumento ser uma classe abstrata e também precisamos que todas as classes que herdem instrumento tenha seus metodos
        // Resumindo todo mundo que herda de uma classe abstrata precisa utilizar seus metodos.
        // O mesmo serve para interfaces
        instrumento.sendoTocado()
    }
}

class Violao: Instrumento {
    // Class filha
    override fun sendoTocado(){
        println("Utilizar cordas")
        println("Para tocar o violão")
    }

    fun ajustarCordas(){
        println("Ajustando cordas")
    }
}


class Bateria: Instrumento {
    // Class filha
    override fun sendoTocado(){
        println("Tocando bateria")
    }

    fun ajustarBanco(){
        println("Ajustando o banco")
    }
}


class Guitarra : Instrumento {
    // Aqui estamos sobreescrevendo os metodos que a interface instrumento manda.
    override fun sendoTocado() {
        println("Tocando guitarra")
    }
}



fun main() {


    val violao: Instrumento = Violao()


    // Tela 1
    println("MUSICO 1")
    val violao1 = Violao() // Instanciando a classe dentro de uma variavel, para passar como parametro.
    val musico = Musico(violao1)
    musico.tocar()

    // Tela 2
    println("\nMUSICO 2")
    val violao2 = Violao() // Instanciando a classe dentro de uma variavel, para passar como parametro.
    val musico2 = Musico(violao2)
    musico2.tocar()

    // Tela 3
    println("\nMUSICO 3")
    val bateria1 = Bateria()
    val musico3 = Musico(bateria1)
    musico3.tocar()


    // Tela 4
    println("\nMUSICO 4")
    val guitarra1 = Guitarra()
    val musico4 = Musico(guitarra1)
    musico4.tocar()


}