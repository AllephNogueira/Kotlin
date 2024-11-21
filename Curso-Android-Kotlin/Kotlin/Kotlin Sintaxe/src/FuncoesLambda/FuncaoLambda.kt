package FuncoesLambda


fun main() {
    // Função normal.
    fun executar(){
        println("Funçao normal.")
    }

    // Função inline

    fun executar2() = println("Executando")

    // Funçao lambda
    // é indicada para ser passada como parametro, ela não tem nome.
    // Então ela precisa ser passada para uma variavel

    val minhaFuncaoLambda =  {
        println("Minha funçao lambda")
    }

    // Criando um parametro para uma funçao lambda

    val funcaoLambdaComParametro = {
        nome: String, idade: Int ->
        println("Função lambda com parametro\n" +
                "Nome: $nome\n" +
                "Idade: $idade")
    }

    // Agora como seria para executar essas funções

    println(executar())

    println(executar2())

    minhaFuncaoLambda()

    funcaoLambdaComParametro("Alleph Nogueira", 30)
}