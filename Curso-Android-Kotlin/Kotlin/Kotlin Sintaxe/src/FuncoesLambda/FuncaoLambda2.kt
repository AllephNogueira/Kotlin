package FuncoesLambda

/**
 * Agora vamos simular o setOnClick
 * Lembrar que ele é um metodo
 * Botão é nossa classe
 *  ATENÇÃO: No codigo o findByID ele instancia nosso botao
 */

class btnExecutar {
    // Aqui vamos simular o botao do setOnClickListner

    fun setOnClickListener( funcao: () -> Unit ){
        // o nosso set, que seria um evento de clique é um metodo
        /**
         * Unit = quando a função nao vai retornar nada.
         * Quando criamos uma funçao que não retorna nada por padrao ela ja vem com a saida Unit, mas aqui devemos utilizar para dizer que não vai retornar nada.
         * Unit = vazio = não vai retornar nada.
         *
         * Agora sim fazendo dessa formra podemos passar uma função para o metodo.
         *  Podemos passar funçao de duas maneiras, chamando outra funçao ou passando uma lambda para ele.
         *  Vamos fazer as duas na pratica
         *
         *
         *  Precisamos colocar o funcao(), porque devemos chamar o nosso parametro.
         *  Ou seja aqui estamos esperando receber uma funçao.
         */

        funcao()

    }

    fun executarClique () {
        println("Executando clique do botao com funçao.")
    }


    fun setOnClickListener(nome: String, funcao: () -> Unit){
        println(nome)
        funcao()
    }
}

fun main() {
    // Reparar que la no Android Studio nao precisamos instanciar porque o findbyID ja faz isso para a gente
    // Aqui vamos precisar

    val btnExecutar = btnExecutar()

    // Aqui estamos dando um exemplo, imagina quando o usuario clicar no botao, vai chamar o metodo.
    // Aqui vamos passar oque o metodo vai fazer
    btnExecutar.setOnClickListener(btnExecutar::executarClique)

    // Agora vamos fazer passando uma funçao lambda
    btnExecutar.setOnClickListener { println("Executando com função lambda.") }


    // Funcao lambda com parametro,
    // Repara que a função lambda nao precisamos colocar ela como parametro, podemos colocar sempre do lado de fora.
    btnExecutar.setOnClickListener("Alleph") {
        println("Função lambda com parametro.")
    }


}