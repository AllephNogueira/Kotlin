package com.example.aulacomponenteslistagemcolecoes.teste

data class Produto(
    var nome: String,
    var preco: Double
){

    fun desativar(){
        println( "Produto $nome com preço: $preco foi desativado" )
    }

}

fun salvarProduto( produto: Produto ){

}

class AlertaMensagem {

    fun configurarTitulo( titulo: String ) = println(titulo)
    fun configurarDescricao( descricao: String ) = println(descricao)

    fun configurarCancelar() = println("Ação de cancelar")
    fun configurarConfirmar() = println("Ação de confirmar")

}

fun main() {

    val alertaMensagem = AlertaMensagem()
    /*alertaMensagem.configurarTitulo("Confirmar salvar?")
    alertaMensagem.configurarDescricao("Você tem certeza...")
    alertaMensagem.configurarCancelar()
    alertaMensagem.configurarConfirmar()*/

    /*alertaMensagem.apply {
        configurarTitulo("Confirmar salvar?")
        configurarDescricao("Você tem certeza...")
        configurarCancelar()
        configurarConfirmar()
    }*/

    val lista = listOf("jamilton", "ana", "pedro")

    lista
        .map { it.uppercase() }
        .run {
            println(this)
        }

    /*var produto: Produto? = null


    //Usuário é que pode escolher ou não um produto
    produto = Produto("Notebook", 1200.00)*/

    /*produto?.let { produto ->
        salvarProduto(produto)
        produto
    }*/

    /*produto?.run {
        desativar()
    }*/

    /*val novoObjeto = with( produto ){
       desativar()
       this
    }*/

    /*if( produto != null ){
        produto.preco = 1100.00
        salvarProduto( produto )
    }*/

    /*val novoProduto = produto?.let {
        it.preco = 1100.00
        //salvarProduto( produto )
        it
    }*/
    /*produto?.let {
        it.preco = 1100.00
        salvarProduto(it)
    }

    println( produto?.nome )
    println( produto?.preco )*/


}