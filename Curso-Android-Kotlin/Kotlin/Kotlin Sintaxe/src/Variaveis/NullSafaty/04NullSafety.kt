package Variaveis.NullSafaty

class CarroK {
    var cor = "Prata"


    fun acelerar(){}
}

fun main() {

    // Aqui estou dizendo que essa variavel é do tipo CARRO(Class)
    var carro: CarroK? = null

    // Se o carro for nulo e eu quiser exibir uma cor padrao?
    // ?: Isso é chamado e Elvis Operation
    // 1 Primeiro vamos testar se o carro é nulo carro?.cor
    // 2 Se o carro for nulo ele não vai acessar a cor do carro
    // 3 Então agora ele vai usar a segunda opção que é "Cor padrao"
    // Funciona bem parecido com o Operador Ternario, ele testa a primeira opção e tem a segunda "Cor padrao"
    val cor = carro?.cor ?: "Cor padrão"

    println(cor)

    println(carro?.cor) // Aqui se o carro for nulo vai exibir o nulo.
}