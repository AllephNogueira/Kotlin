package Adapter.Exercicio

/**
 * Primeiro dizemos que o MeuAdapter teria que receber uma lista de Strings.
 * Apos isso pegamos essa lista de String e colocamos dentro de um atributo
 *
 *
 * Agora vamos implementar a interface adapter e seus metodos.
 *
 * Dentro de quantidade de itens, passamos a propria lista que estava chegando e usamos o size para pegar o tamanho dela
 *
 *
 * em montarLayoutParaItem
 * Usamos a propria lista passando a posição de cada item.
 *
 * Agora para pegar a  posição do item fazemos assim
 * Usamos o metodo indexOf, ele espera que voce passe o item que você quer saber a posição.
 * Exemplo: Queremos saber a posição de "Alleph"
 * Mas como queremos algo automatico de cada item, então passamos a propria lista
 *  ** Passamos a propria lista, porque foi feito um for, então ele iria processar cada informação por vez **
 *
 *
 * ComponenteDeListagem = espera receber um adaptador então fizemos o nosso proprio, instanciamos e adicionamos nele.
 *
 * E Depois utilizamos seu metodo, executar.
 *
 */