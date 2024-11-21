package GetterESetter

/**
 * Getter = conseguir || recuperar
 * Setter = configurar ||
 */

class Usuario (){
    var apelido: String = ""
        // Lembrar que no caso do get, vamos usar apenas para pegar o valor
        get() {
            println("Estamos retornando o campo: $field")
            return field.uppercase() // Field é um valor padrao que signifca que vai retornar o campo ou seja, ele vai retornar o "apelido"
        }

        // Vamos utilizar para configurar o valor do apelido
        set(value) {
            field = "set: $value"   // Aqui é assim: Vamos pegar o field que é o campo(Apelido) e vamos adicionar nele o valor que vamos configurar.
        }


    /**
     * Isso aqui é chamado de get personalizado.
     * Ele vai pegar o atributo maiorIdade e vai testar a idade para ver se e maior que 18
     */
    var idade: Int = 0
    var maiorIdade = false
        get() = idade >= 18
}


fun main() {
    /**
     * Pegando a classe do java
     * Em java como colocamos os atributos private, estamos acessando os metodos.
     */
    val pessoa = GetterESetterJava()
    pessoa.nome = "AllephNogueira"
    pessoa.idade = 30

    println("Usuario: " + pessoa.nome)


    /**
     * Pegando a classe do Kotlin
     * Em Kotlin ja fazemos isso de forma automatica, nao precisamos criar getter e setter
     *
     * Mas segue o exemplo em cima se quiser fazer.
     *
     */

    val pessoaK = Usuario()
    pessoaK.apelido = "Alleph"
    pessoaK.idade = 30
    println("${pessoaK.apelido}, é maior de idade? ${pessoaK.maiorIdade}")
}