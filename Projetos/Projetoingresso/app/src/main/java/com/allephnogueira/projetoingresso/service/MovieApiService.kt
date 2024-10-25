package com.allephnogueira.projetoingresso.api

import android.graphics.Movie
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiService {
    @GET("movies/now_playing")
    fun getNowPlayingMovies(): Call<List<Movie>>
}