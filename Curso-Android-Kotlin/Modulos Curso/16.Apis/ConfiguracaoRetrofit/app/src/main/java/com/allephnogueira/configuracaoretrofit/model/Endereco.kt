package com.allephnogueira.configuracaoretrofit.model

import com.google.gson.annotations.SerializedName

data class Endereco (

    @SerializedName("cep")
    val cep : String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val ibge: Int,
    val gia: Int,
    val ddd: Int,
    val siafi: Int
)