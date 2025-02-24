package com.allephnogueira.configuracaoretrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    // Queremos acessar os recursos sem precisar instanciar a classe
    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/")
            .addConverterFactory(GsonConverterFactory.create()) // Conversor para JSON || XML
            .build()
    }
}