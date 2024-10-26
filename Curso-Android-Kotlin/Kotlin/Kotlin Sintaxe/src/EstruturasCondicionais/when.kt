package EstruturasCondicionais

fun main() {
    val opcao = 6

    when(opcao){
        1 -> println("Cartao de credito")
        2 -> println("Extrato bancario")
        in 3..5 -> println("Saldo") // Também podemos usar o in em WHEN como você pode ver.
        else -> println("Opcao não encontrada")
    }
}