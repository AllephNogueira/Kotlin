package ArraysELoops

fun main() {
    val postagens = arrayOf(
        "Ir a praia",
        "Comprei um carro",
        "Fui ao cinema",
        "Comprei um hamburguer"
    )

    // Pegando somente a postagem
    for (postagem in postagens){
        println(postagem)
    }

    // Pegando somente o indice

    for (postagem in postagens.withIndex()) {
        println("Pegando somente os indices $postagem")
    }


    // Pegando o indice mais a postagem
    for ((indice, postagem) in postagens.withIndex()){
        println("Titulo: $indice $postagem")
    }


}