package CompanionObject



class CarroC(var modelo: String = "", var velocidade: Int = 0) {

    companion object {
        const val VELOCIDADE_MAX_PERMITIDA = 120
        fun multarVeiculo() = println("Veiculo multado.")
    }

    fun exibirInformacoes() = println("Informações $modelo e $velocidade km max")

}

class UsuarioC() {

    companion object regra{
        fun verificarUsuarioLogado() : Boolean = true
    }
}
fun main() {

    val retorno = UsuarioC.regra.verificarUsuarioLogado()
    println("Usuario esta: $retorno")


    CarroC.VELOCIDADE_MAX_PERMITIDA
    println("Velocidade maxima permitida ${CarroC.VELOCIDADE_MAX_PERMITIDA}")
    CarroC.multarVeiculo()


    val c4 = CarroC("C4", 220) // Aqui estamos criando uma instancia ou objeto
    c4.exibirInformacoes() // Repara que esse metodo é um metodo de instancia, ele esta relacionado ao objeto carro.


    val fiesta = CarroC("Fiesta", 180) // Aqui estamos criando uma instancia ou objeto
    fiesta.exibirInformacoes() // Repara que esse metodo é um metodo de instancia, ele esta relacionado ao objeto carro.

    println("PI: " + Math.PI)

}