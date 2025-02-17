package Desafios

/* Nesse desafio vamos converter 23:05 para 11:05
    Também vamos converter 11:05 para 23:05
 */


fun timeConversion (s : String) : String {

    // Temos um padrao 00:00:00AM = então sao 8 casas
    // Pegamos as primeiras casas com a hora
    val tempoArray = s.substring(0,8).split(":").toTypedArray()

    // Agora vamos pegar as ultimas casas com o PM ou AM
    val amOuPm: String? = s.substring(8,10)

    // Agora vamos configurar se for depois das 12horas e tiver o PM nao fazemos nada
    var hora = tempoArray[0].toInt()
    val minutos = tempoArray[1]
    val segundos = tempoArray[2]

    // formatando a hora para sempre exibir 2 casas



    val dataCorreta = if (hora >= 12 && amOuPm?.contains("PM") == true) {
        "${corrigirValorHora(hora)}:$minutos:$segundos "
    } else if (hora >= 12 && amOuPm?.contains("AM") == true) {
        hora = hora - 12
        "${corrigirValorHora(hora)}:$minutos:$segundos "
    } else if (hora < 12 && amOuPm?.contains("PM") == true) {
        hora = hora + 12
        "${corrigirValorHora(hora)}:$minutos:$segundos "
    } else if (hora > 12 && amOuPm?.contains("AM") == true) {
        hora = hora -12
        "${corrigirValorHora(hora)}:$minutos:$segundos "
    }else if(hora < 12 && amOuPm?.contains("AM") == true) {
        "${corrigirValorHora(hora)}:$minutos:$segundos "
    }else {
        "Formato da hora incorreto!"
    }


    return "$dataCorreta"

}

fun corrigirValorHora(hora : Int) : String {
    /* Funçao que vai atualizar o valor da hora

    Antes estavamos com o problema de
    Entrada : 12:40:22
    Saida : 0:40:22

    Agora

    Entrada : 12:40:22
    Saida : 00:40:22

     */
    val horaFormatada = String.format("%02d", hora)
    return horaFormatada
}

fun main(args: Array<String>) {
    val s = "12:05:39AM"

    val result = timeConversion(s)

    println(result)
}