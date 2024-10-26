package POO.Classes


class Usuario {



    fun logar(email: String, senha: String){
        println("Usuario logado com: $email e senha: $senha")
    }

    fun logar(email: String, senha: String, telefone: String) {
        println("Usuario logado com: $email, senha: $senha e telefone: $telefone")
    }

    fun logar(email: String, telefone: Long) {
        println("Usuario logado com: $email e telefone: $telefone")
    }

}

fun main() {

    val usuario = Usuario();

    usuario.logar("allephn@hotmail.com.br", "1234")
    usuario.logar("allephn@hotmail.com.br", "12345", "21975575694")
    usuario.logar("allephn@hotmail.com.br", 21975575694)

}