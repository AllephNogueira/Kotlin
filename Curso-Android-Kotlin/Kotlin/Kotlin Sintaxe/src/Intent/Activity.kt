package Intent

import java.io.Serializable


class Intent {
    fun putExtra( chave: String, valor: Serializable) {}
}


class Usuario(nome: String, email: String) : Serializable{}

// Porque implementar a serializable, porque os desenvolvedores não sabe qual o tipo de dado que esta chegando.
// Nao podemos passar a classe sozinha e sim temos que passar um tipo de dado que a intent consiga ler e entender.

fun main() {
    // Vamos imaginar que aqui é nosso onCreate
    // Queremos abrir uma nova activity

    val intent = Intent()

    // Queremos passar alguns dados
    // Nao podemos passar diretamente a classe, então temos que fazer um Serializable
    // Vamos instanciar o usuario

    val usuario = Usuario("alleph", "allephn@hotmail.com.br")

    intent.putExtra("nome", usuario)

    // Agora na outra tela vamos pegar esses valores utilizando a chave.


}