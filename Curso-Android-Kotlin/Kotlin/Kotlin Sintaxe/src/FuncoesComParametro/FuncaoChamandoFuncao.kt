package FuncoesComParametro


class Matematica {

    /**
     * Tudo que estiver dentro da classe(Matematica) é chamado de metodo.
     */
    fun somar(numero: Int, numero2: Int) : Int{
        return numero + numero2
    }

    fun calcularSoma( funcao: (Int, Int) -> Int) {
        val retorno = funcao(50,120)
        println(retorno)
    }
}


/**
 * Atençao, tudo que estiver fora da classe(Matematica) é chamado de funçao
 */
    fun somarNumero(numero1 : Int, numero2: Int) : Int {
        return numero1 + numero2
    }

/**
 * Uma funçao chamando a outra
 * Aqui estamos dizendo que nosso parametro (funcao) precisa receber 2 parametros.
 *
 * Funcao : Seria qual a funçao que vamos chamar.
 *
 * Aqui seria: calcular(nome da funçao que vamos chamar : (tipo do parametro que vai entrar: Int, Int) -> tipo de dado que vai sair Int){}
 */
    fun calcular (funcao : (Int, Int) -> Int){
        val retorno = funcao(50,20)
        println(retorno)
    }



fun main() {

    val matematica = Matematica()
    /**
     * Passando agora o parametro para uma funçao que chama outra funçao
     *
     * Aqui estamos chamando uma função que vai chamar outra funçao.
     */
        calcular(::somarNumero)

    /**
     * Uma funçao que chama agora um metodo
     */
    calcular(matematica::somar)

    /**
     * Um metodo que chama outro metodo
     * Matematica = classe
     * CalcularSoma = Metodo
     * Somar = metodo
     */
    matematica.calcularSoma(matematica::somar)

}