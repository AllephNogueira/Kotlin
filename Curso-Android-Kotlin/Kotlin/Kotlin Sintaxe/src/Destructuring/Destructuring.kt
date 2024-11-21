package Destructuring


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

    /**
     * Isso aqui é a mesma logica de:
     *
     * Aqui estamos pegando o primeiro e o segunda valor da instancia, se tivesse mais valores no construtor iriamos pegar os outros
     * Lembrar que para isso nossa classe é do tipo data class (Onde vamos observa somente os dados dentro dela e nao a classe)
     */
    val (pergunta, resposta) = pergunta1

    /**
     * Disso aqui:
     */
//    val p = pergunta1.pergunta
//    val r = pergunta1.respostaCerta

    /**
     * Agora voce pode fazer dessa forma, pegar somente a informação que quiser dentro de um construtor, sem precisar chama toda a classe novamente.
     * Vamos imaginar que temos uma data class Login, onde la vai ter todos os dados, mas queremos apenas o nome para exibir.
     * Então fazemos o destructor para pegar apenas o nome do usuario.
     */
    println(pergunta)
    println(resposta)


}