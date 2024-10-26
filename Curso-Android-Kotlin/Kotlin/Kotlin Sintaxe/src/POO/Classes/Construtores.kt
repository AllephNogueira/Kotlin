package POO.Classes


// Construtor primario
class NewUsuario constructor( var nome: String = "", var sobrenome: String = "", var cpf: String = "") {


    init {// init = inicializar

        println("Objeto inicializado.")
        println("nome: $nome, sobrenome: $sobrenome, cpf: $cpf")
    }

    constructor(nome: String): this(nome, sobrenome = "", cpf = "") {//Construtor secundario
        println("Construtor secundario")
     }
}

fun main() {
    val usuario = NewUsuario("alleph", "nogueira", "15679025701")
    val usuario2 = NewUsuario()
    val usuario3 = NewUsuario(sobrenome = "Nogueira")

    val usuarioConstrutorSecundario = NewUsuario("alleph")

}