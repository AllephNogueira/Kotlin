package com.allephnogueira.recuperandolista.model

import com.google.gson.annotations.SerializedName

data class Postagem(

    @SerializedName("body")
    val descricao: String, // body = corpo da postagem, mas vamos alterar o nome
    val id: Int,
    val title: String,
    val userId: Int
)