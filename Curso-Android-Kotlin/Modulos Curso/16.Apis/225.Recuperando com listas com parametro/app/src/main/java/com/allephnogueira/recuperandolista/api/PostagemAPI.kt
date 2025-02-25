package com.allephnogueira.recuperandolista.api


import com.allephnogueira.recuperandolista.model.Postagem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostagemAPI {

    @GET("posts")
    suspend fun recuperarPostagens(): Response<List<Postagem>>


    /** itemProcurado = o parametro ID
     * id = id da postagem mesmo, como definimos la na classe dela
     * o itemProcurado pode ter qualquer nome, oque vale é o parametro que passamos no caso o Id
     */
    @GET("posts/{itemProcurado}")
    suspend fun recuperarPostagemUnica(
        @Path("itemProcurado") id: Int
    ): Response<Postagem>

}
