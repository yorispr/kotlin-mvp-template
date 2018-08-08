package com.yorisprayogo.mvptemplate.data

import com.yorisprayogo.mvptemplate.data.model.Movie
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie")
    fun getMovies(@Query("page") page: Int): Single<List<Movie>>
}
