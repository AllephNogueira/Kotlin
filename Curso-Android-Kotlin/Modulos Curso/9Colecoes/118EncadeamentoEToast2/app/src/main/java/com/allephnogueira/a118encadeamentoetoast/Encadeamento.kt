package com.allephnogueira.a118encadeamentoetoast

/**
 * Olhando o toast por de baixo dos panos, como funciona.
 *
 * Repara que estamos fazendo um encadeamento de metodos.
 *
 * Repara que no primeiro metodo fizemos um metodo retornar um objeto, e depois vamos utilizar ele.
 *
 * makeText retorna um objeto, por isso não vamos precisar instanciar ele.
 *
 * E vamos criar o construtor para receber mensagem e duração
 * Quando chamar o metodo vamos passar como parametro a mensagem e duração, que vai ser passada para o construtor.
 */

class Toast (
    val mensagem: String,
    val duracao: Int
    ) {
    companion object {

        // Tipos de duração do nosso Toast
        const val DURACAO_CURTA = 0
        const val DURACAO_LONGA = 100


        fun makeText (mensagem: String, duracao: Int) : Toast  {
            return Toast(mensagem, duracao)
        }

    }

    fun show () {
        // Agora podemos acessar os dados que foram passado para o construtor.
        println("Mensagem - ${this.mensagem} - ${this.duracao}")
    }

}

fun main() {

    Toast.makeText("Toast aberto", Toast.DURACAO_LONGA).show()

}

/**
 * Resumindo, estamos criando um metodo com encadeamento
 * Vamos chamar a classe Toast, chamar o metodo que esta dentro dela, passar os parametros para esse metodo.
 * Esse metodo vai ser o tipo: Toast, que vai pegar os pararmetros e passar para o construtor.
 * Depois vamos recuperar esses dados em Show, onde vamos exibir para o usuario.
 *
 * Não vamos precisar isntanciar o SHOW, porque o proprio return do metodo makeText ja faz isso para a gente.
 *
 * Atenção para conseguir encadear algo, o metodo anterior tem que retornar o objeto (Instanciar o objeto)
 */