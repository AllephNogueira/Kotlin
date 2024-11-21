package ClasseDeDadosDataClass

/**
 * Quando criamos um objeto, imagina que criamos uma caixa e vamos por tudo la dentro, metodos, atributos...
 * E quando perguntamos se objeto1 == objeto2 ele sempre vai da falso, porque o objeto 1 por mais que tenha os mesmos metodos e mesmos atributos, ele vai ter um "CPF" diferente.
 * EX: Objeto1@4144556465 Objeto2@54546565 observa que a numeração é diferente, mas se a gente quiser olhar somente para oque esta "dentro da caixa de objeto"
 * Utilizamos o data class, apenas para analisar os dados que estao dentro da caixa.
 *
 * Observa que o DATA CLASS - precisa ter pelomenos um construtor primario.
 * Outra coisa, reparar que utilizamos o date class sem as chaves{} porque ela é focada somente nos dados, e nao na classe em si.
 *              *** mas podemos também adicionar metodos nela, mas não é o recomendado.
 */

data class Pergunta (val pergunta: String, val respostaCerta: Int)

fun main() {

    val pergunta1 = Pergunta("Qual a pergunta?", 1)
    val pergunta2 = Pergunta("Qual a pergunta?", 1)


    println(pergunta1 == pergunta2)

}