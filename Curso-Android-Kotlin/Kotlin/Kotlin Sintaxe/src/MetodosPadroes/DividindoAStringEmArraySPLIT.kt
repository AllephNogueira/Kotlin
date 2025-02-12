package MetodosPadroes

/* Dividindo a string em partes
Ex: 12:01:02:PM
Vamos dizer que sempre que tiver um : ele vai transformar em um indice o numero apos o :

Aqui nesse exemplo pegamos apenas a casa 01
 */

fun main() {
    val horario = "12:01:06:PM"

    val transformandoEmArray = horario.split(":")

    println(transformandoEmArray[1]) // SAIDA : 01

    // ***************************************** //

    val hora = transformandoEmArray[0]
    val minutos = transformandoEmArray[1]
    val segundos = transformandoEmArray[2]
    val amOuPm: String? = transformandoEmArray[3]

    println(hora + minutos + segundos + amOuPm)
}