package com.yorisprayogo.mvptemplate.data.network

import com.yorisprayogo.mvptemplate.data.model.Movie
import io.reactivex.Single

interface ApiHelper {

//    val apiHeader: ApiHeader

    fun getMoviesCall(page: Int): Single<List<Movie>>

}