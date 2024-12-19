//package Adapter
//
///**
// * Resumo, imagina que temos uma tomada com 2 pinos, é vantagem jogar o aparelho fora? não, o ideal e usar um adaptador
// * Mesma coisa no codigo, vamos refazer ele todo do zero? não, vamos usar um adapter
// */
//interface Conector {
//    fun quantidadeDePinos() : Int
//    fun ligarAparelho()
//}
//
//class TomadaAntiga (val conector: Conector) {
//    // Dessa forma agora vamos poder passar qualquer conector.
//    // Imagina que a tomada antiga so funciona 2 pinos, então precisamos testar antes de inserir ela
//    // Se ela tiver 2 pinos vamos ligar a tomada.
//    fun passarEnergia(){
//        val qntPinos = conector.quantidadeDePinos()
//        if (qntPinos == 2 ) {
//            conector.ligarAparelho()
//            println("Quantidade de pinos: $qntPinos")
//            println("Passando energia!")
//        }else {
//            println("Essa tomada só funciona com 2 pinos, voce passou $qntPinos")
//        }
//    }
//}
//
//class ConectorAdaptado : Conector {
//    override fun quantidadeDePinos(): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun ligarAparelho() {
//        TODO("Not yet implemented")
//    }
//
//}
//
//class ConectorNovoPadrao : Conector {
//    override fun quantidadeDePinos() : Int = 3
//
//   override fun ligarAparelho() = println("Aparelho ligado!")
//}
//
//
//fun main() {
//
//    val conectorNovoPadrao = ConectorNovoPadrao()
//    val tomadaAntiga = TomadaAntiga(conectorNovoPadrao)
//    tomadaAntiga.passarEnergia()
//
//}