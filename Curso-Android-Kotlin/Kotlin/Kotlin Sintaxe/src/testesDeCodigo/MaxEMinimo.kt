package testesDeCodigo

/* Nesse codigo esperamos enviar ex:
1 2 3 4 5
Esperamos que voce calcule os primeiros numeros menos o ultimo
Ex: 1+2+3+4 = 10
Também esperamos que voce calcule todos os itens menos o primeiro
Ex: 2+3+4+5 = 14

Deve imprimir na mesma linha as duas respostas

Entrada: 1 2 3 4 5
Saida: 10 14
 */


fun miniMaxSum(arr: Array<Int>): Unit {


    val sortedArr = arr.sorted().map { it.toLong() } // Colocando o array em Ordem - Convertendo para LONG


    val minSum: Long = sortedArr.take(4).sum() // Recebe os primeiro 4 indices / SUM = soma os primeiro 4 indices


    val maxSum: Long = sortedArr.takeLast(4).sum() // Recebe os ultimos 4 indices / SUM = Soma os 4 ultimos indices


    println("$minSum $maxSum") // Imprime os 2 resultado.
}

fun main(args: Array<String>) {
    val arr = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
    miniMaxSum(arr)
}


