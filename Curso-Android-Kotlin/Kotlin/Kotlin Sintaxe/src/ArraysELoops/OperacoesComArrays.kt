package ArraysELoops

class convidadosFormatura{
    companion object {
        val convidados = arrayOf("Alleph", "Fernanda", "Crixus", "Amora", "Anastacia", "Valeria", "Gabriel", "Glayce", "Guilherme", "Lorenzo", "Davi", "Livia")
    }
    /**
     * Temos esses tipos de array
     * arrayOf - intArray - doubleArray..
     * [] e get
     * plus = Cria uma nova lista, adicionando o novo usuario Ex plus("Bethovem")
     * Size = Pegar a quantidade de itens do array
     * indexOf = Aqui passamos o elemento EX: Alleph e ele retorna qual numero do elemento Ex: 0
     * First = pegar o primeiro elemento
     * Last = Ultimo elemento da lista.
     * Contains = Verificar se existe aquele dado no array
     * Shuffle = Embaralhar
     */
}

fun main() {

    println(convidadosFormatura.convidados.size) // Quantidade de convidados


    println(convidadosFormatura.convidados.first())


    println(convidadosFormatura.convidados.last()) // Ultimo elemento

    /**
     * Signifcado da palavra forEach = Para(FOR) - Cada(Each)
     * Então ele vai percorrer cada elemento dessa lista.
     */
    convidadosFormatura.convidados.forEach{
        item -> println(item)
    }
    println("\n\n")

    println(convidadosFormatura.convidados[5]) // Pegando um item da lista.

    /**
     * Agora vamos adicionar outro item dentro do array, mas lembra que o array é mutavel ele não pode adicionar novos itens
     * Então vamos fazer com o plus(mais)
     * Então para isso vamos precisar criar uma nova lista
     * Resumindo ele vai criar uma nova lista, vai pegar todos os itens da lista antiga e vai adicionar o item novo.
     */

    val novaLista = convidadosFormatura.convidados.plus("Babu")

    novaLista.shuffle()// Embaralhar a lista.
    novaLista.forEach{nomes -> println(nomes)}

    println("\n\n ${novaLista.indexOf("Crixus")}" )
    println(novaLista.last())
    println(novaLista.contains("Amora"))






}