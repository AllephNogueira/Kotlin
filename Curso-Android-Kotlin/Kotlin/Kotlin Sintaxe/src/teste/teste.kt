package teste

/** Tenho um livro que tirando a primeira e folha, as demais todas elas tem 2 paginas.
 *
 * Resumindo como cheguei a essa logica: A cada 2 numeros de diferença = 1 pagina
 * Ex: 1 ~~ 3 = Seriam 2 pagina
 * Ex: 1 ~~ 10  = 5 paginas
 *
 * Agora imagina que o usuario quer abrir do lado mais rapido, vamos supor que ele queira abrir a pagina 9, então é muito mais rapido se ele abrir do outro lado do livro.
 */

fun main() {


    val totalPaginas = 10
    val paginaQueQueroIr = 8


    // Aqui vamos calcular depois qual melhor lado, se é abrir o livro pela frente ou pelo final
    // Ele vai retornar o menor valor.
    val porOndeAbrirOLivro = calcularQualLadoMaisRapido(iniciandoPeloInicio(totalPaginas, paginaQueQueroIr), iniciandoPeloFinal(totalPaginas, paginaQueQueroIr))

    println("Total de paginas que vamos precisar abrir: $porOndeAbrirOLivro")


}

fun iniciandoPeloInicio(totalPaginas: Int, paginaQueQueroIr: Int): Int {
    // Fazendo a contagem pela frente
    // primeira pagina 1 + Pagina que quero ir 4 = !3
     val quantidadeDePaginasAVirarComecandoDoInicio =
        if (paginaQueQueroIr % 2 == 0) (paginaQueQueroIr / 2) else (paginaQueQueroIr - 1) / 2 // Começando pela frente

    return quantidadeDePaginasAVirarComecandoDoInicio
}

fun iniciandoPeloFinal(totalDePaginas : Int, folhaQueQueremosIr: Int) : Int {
    // PAGINA 1:    - PAGINA 2:    - PAGINA 3:   -  PAGINA 5
    // FOLHA /1      - FOLHA 2/3    - FOLHA 3/4   - FOLHA 5/
    // Imagina que, estamos com um livro na pagina 5 e queremos abrir a folha 3
    // Então precisariamos voltar apenas 1 pagina

    // IRIAMOS PRECISAR RETORNAR SO UMA FOLHA NESSA SITUAÇÃO
    // AGORA VAMOS IMAGINAR QUE TEMOS 10 FOLHAS E ABRIMOS O LIVRO NA PAGINA 6 IRIAMOS PRECISAR RETORNAR 4 FOLHAS - PQ A CADA 2 FOLHAS = 1 PAGINA

    // se for par vamos dividir isso
    // Folha que queremos ir 6 // Total de paginas 10
    var teste = 1000000000
    if (folhaQueQueremosIr % 2 == 0) {
        teste = (totalDePaginas - folhaQueQueremosIr) / 2 // SAIDA 2 ----- PRECISAMOS RETORNAR 2 PAGINAS
    }else {
        teste = ((totalDePaginas - folhaQueQueremosIr) +1 )/ 2
    }

    return teste
}

fun calcularQualLadoMaisRapido(abrirPeloComeco: Int, abrirPeloFinal: Int): Int {

    //minOf ele vai pegar os 2 numeros e vai retornar o menor entre eles.
    return minOf(abrirPeloComeco, abrirPeloFinal)
}




