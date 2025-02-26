package com.allephnogueira.recuperandolista.api


import com.allephnogueira.recuperandolista.model.Postagem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
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


    @POST("posts/")
    /* Body = o corpo do que vamos salvar
    Nesse caso estamos salvando uma postagem.
     */
    suspend fun salvarPostagem(@Body postagem: Postagem) : Response<Postagem>


    @FormUrlEncoded
    @POST("posts")
    suspend fun salvarPostagemPorFomulario(
        /* Precisamos utilizar os mesmos nomes que estao na api */
        @Field("userID") userID : Int,
        @Field("id") id : Int,
        @Field("title") title : String,
        @Field("body") body : String
    ) : Response<Postagem>


    @PUT("posts/{id}")
    suspend fun atualizarPostagem (
        @Path("id") id: Int, @Body postagem: Postagem
    ) : Response<Postagem>


    @PATCH("posts/{id}")
    suspend fun atualizarPostagemPatch(
        @Path("id") id: Int,
        @Body postagem: Postagem
    ) : Response<Postagem>


    @DELETE("posts/{id}")
    suspend fun removerPostagem(
        @Path("id") id: Int
    ) : Response<Unit>

}
