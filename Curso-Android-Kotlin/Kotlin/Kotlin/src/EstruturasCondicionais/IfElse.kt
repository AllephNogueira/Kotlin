package EstruturasCondicionais

fun main() {
    var conta = 200
    var despesas = 0
    var sol = false

    if (conta > despesas && sol) {
        println("Você pode ir a praia")
    }else {
        println("O tempo não esta legal para praia hoje.")
    }

///////////////////////////////////// OUTRA OPCAO /////////////////////////////////////////

    val notaAluno = 7
    val condicao = notaAluno >= 6 // Nota do aluno é maior ou igual a 6? isso vai retornar um True ou False
    // e logo em baixo vamos confirmar se devemos entrar no if ou não
    if (condicao) {
        println("Aprovado")
    }else{
        println("Reprovado")
    }

    println(condicao)
    println("\n\n")


    //////////////////////// IF COM IN ////////////////

    // Observa que o IN ele diz assim
    // Se a opcao estiver entre(IN) 1 ou 3
    var opcao = 4
    if (opcao in 1..3) {
        println("Cartao de credito")
    }else if (opcao in 4..5){
        println("Pagamento com dinheiro")
    }else {
        println("Opcao invalida.")
    }
}