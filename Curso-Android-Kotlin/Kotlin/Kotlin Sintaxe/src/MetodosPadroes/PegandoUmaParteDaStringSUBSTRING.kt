package MetodosPadroes

/* Imagina que temos uma string, e queremos pegar alguns dados dela
Exemplo String: 12:10:20PM
Queremos pegar os numeros dela e nao o PM
 */

fun main() {
    val horario = "12:10:20PM"

    val pegandoAsHoras = horario.substring(0,8)

    println(pegandoAsHoras) // SAIDA : 12:10:20


    val amOuPm = horario.substring(8,10)
    println(amOuPm) // SAIDA: PM
}