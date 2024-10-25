package com.allephnogueira.projetoingresso.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class RetrofitClient {

    companion object { // Para poder acessar os recursos sem precisar instanciar.

        const val URL_BASE = "https://api-content.ingresso.com/v0/events/coming-soon/partnership/desafio"

        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            // Aqui é onde vamos colocar nosso conversor
            // O metodo create ele vai criar automaticamente.
            .addConverterFactory( GsonConverterFactory.create() )
            .build() // Para construir

    }
}
