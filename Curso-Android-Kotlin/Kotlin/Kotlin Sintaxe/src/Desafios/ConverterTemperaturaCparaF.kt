package Desafios

/*
Desafio: Conversor de Temperatura
Crie um programa em Kotlin que converta temperaturas de Celsius para Fahrenheit e vice-versa. O programa deve permitir ao usuário escolher a conversão desejada e, em seguida, fornecer a temperatura convertida.
 */

fun main() {
    println("Escolha a opção " +
            "1 - Converter Celsius para Fahrenheit " +
            "2 - Converter Fahrenheit para Celsius")
    val opcao = readLine()!!.toInt()

    when(opcao) {
        1 -> celsiusParaFahrenheit()
        2 -> fahrenheitParaCelsius()
        else -> println("Opção invalida!")
    }
}

fun fahrenheitParaCelsius() {
    println("Digite a temperatura Fahrenheit")
    val temperaturaF = readLine()!!.toDoubleOrNull()
    if (temperaturaF != null) {
        val temperaturaConvertidaParaC = (temperaturaF - 32) * 5/9
        println("Temperatura em Celsius seria: $temperaturaConvertidaParaC")
    }else {
        println("ERRO: Temperatura não preenchida.")
    }


}

fun celsiusParaFahrenheit() {
    println("Digite a temperatura Celsius")
    val temperaturaC = readLine()!!.toDoubleOrNull()
    if (temperaturaC != null) {
        val temperaturaConvertidaParaF = (temperaturaC * 9/5) + 32
        println("Temperatura em Fahrenheit seria: $temperaturaConvertidaParaF")
    }else {
        println("ERRO: Temperatura não preenchida.")
    }

}
