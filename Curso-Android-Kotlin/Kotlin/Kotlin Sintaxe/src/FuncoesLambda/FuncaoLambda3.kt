package FuncoesLambda

class meuBotao {

    fun setOnClick ( funcao: (String) -> Unit) {
        /**
         * Minha função lambda recebe um parametro.
         */
        funcao("Alleph") // Valor padrao, se quiser chamar podemos utilizar o IT
    }
}

fun main() {
    val meuBotao = meuBotao()

    meuBotao.setOnClick { nome ->
        // Repara que temos um parametro, e com esse parametro podemos modificar varias coisas.
        println(nome.length)
    }

    // Repara que sempre quando tiver um parametro ele vai mostrar
    meuBotao.setOnClick {

        // Não precisamos definir um parametro
        // IT é um nome padrao para o parametro.
        println("Minha funçao lambda $it")
    }

}