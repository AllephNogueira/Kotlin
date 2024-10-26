package Lateinit


class ProdutoL (){
    lateinit var descricao: String

}

fun main() {
    val produtoL = ProdutoL()
    produtoL.descricao = "Carro grande sedan"
    println(produtoL.descricao)
}