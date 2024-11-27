package CollectionsListEstruturas.PaireTripleEstruturaDeDados.Triple

// Imagina que queremos uma estrutura de dados onde tenha 3 informações

fun main() {
    val dadosUsuario = Triple("Alleph", "Nogueira", "Oliveira")

    println("Nome: ${dadosUsuario.first}\nSobrenome: ${dadosUsuario.second}\nUltimo nome: ${dadosUsuario.third}")


    // Agora imagina que queremos a localização de uma pessoa
    // Estamos criando um aplicativo de localização

    val localizacao = Triple(1546654, 456564456, "Alleph")
    println("Cordenadas: ${localizacao.first}, ${localizacao.second} - ${localizacao.third}")


    val local = 45564 to 554656 to "Alleph" // Aqui é outra forma de fazer
}