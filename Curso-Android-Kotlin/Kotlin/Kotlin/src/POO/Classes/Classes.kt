package POO.Classes

class Jogador { // Classe
    // Atributos
    var nome: String = ""
    var nivel: Int = 0
    var personagem: String = ""
    var ataque: Int = 0


    // Metodo sem parametro
    fun soltarMagia(){
        println("Meteoro")
    }

    // Metodo com parametro e valor padrao
    fun atacar(forcaAtaque: Int = 0){
        println("Atacando... \nSeu ataque foi de: $forcaAtaque" )
    }

    // Metodo com retorno
//    fun defender(totalDefesa: Int = 0) : String {
//        return "Total de defesa foi $totalDefesa"
//    }

    fun defender(totalDefesa: Int = 0) : String = "Total de defesa foi $totalDefesa"

}

fun main() {

    val jogador1 = Jogador()
    jogador1.nome = "Alleph"
    jogador1.nivel = 150
    jogador1.personagem = "Mago"
    jogador1.atacar(50)
    val defesa = jogador1.defender(120)
    println(defesa)

    jogador1.soltarMagia()

}