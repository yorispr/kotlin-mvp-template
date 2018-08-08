package com.yorisprayogo.mvptemplate.data.network

import android.content.Context
import com.example.mvp_google_test.BASE_URL
import com.yorisprayogo.mvptemplate.data.Api
import com.yorisprayogo.mvptemplate.data.model.Movie
import com.yorisprayogo.mvptemplate.di.ApplicationContext
import com.yorisprayogo.mvptemplate.di.Network
import com.yorisprayogo.mvptemplate.di.Networks
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class AppApiHelper @Inject constructor(
        @ApplicationContext val context: Context): ApiHelper {

    private var api: Api

    init {
        fun getInterceptor(): Interceptor {
            return Interceptor { chain ->
                var request = chain.request()
                val builder = request.newBuilder()
                builder.addHeader("Content-Type", "application/json")
                builder.addHeader("Cache-control", "no-cache")
//                if (BuildConfig.APP_TYPE == "debug") {
//                    val baseUrl = preferenceManager.getTopsheetBaseUrl()
//                    builder.url(baseUrl + request.url().encodedPath())
//                }
                request = builder.build()

                chain.proceed(request)
            }
        }

        fun getNetworkInterceptor(): Interceptor {
            return Interceptor { chain ->
                val url = chain.request().url().newBuilder().addQueryParameter("api_key", "ef68bfed72780ce7ae801b9daba23069").build()
                val req = chain.request().newBuilder().url(url).build()

                val originalResponse = chain.proceed(req)
                originalResponse.newBuilder()
//                        .body(ProgressResponseBody(originalResponse.body(), object : ProgressResponseBody.ProgressListener {
//                            override fun update(bytesRead: Long, contentLength: Long, done: Boolean) {
//                                //val percentage = (100 * bytesRead / contentLength).toInt()
//                            }
//
//                            override fun onError(error: String) {
//                                Log.d("usm_error", "error= " + error)
//                            }
//                        }))
                        .build()
            }
        }

        fun getOkHttpClient(cache: Cache,
                            connectionPool: ConnectionPool): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return  OkHttpClient.Builder()
                    .cache(cache)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(getInterceptor())
                    .addInterceptor(httpLoggingInterceptor)
                    .addNetworkInterceptor(getNetworkInterceptor())
                    .connectionPool(connectionPool)
                    .build()
        }

//        val baseUrl = if(BuildConfig.BUILD_TYPE == "uat" || BuildConfig.BUILD_TYPE == "debug") BASE_URL_UAT  else BASE_URL

//        if (BuildConfig.APP_TYPE == "debug") {
//            baseUrl = preferenceManager.getTopsheetBaseUrl()
//        } else if (BuildConfig.APP_TYPE == "training") {
//            baseUrl = BASE_URL_TRAINING
//        }

        @Networks val cacheSize = Network.CACHE_SIZE
        val cache = Cache(context.cacheDir, cacheSize.toLong())

        @Networks val maxIdleConnections = Network.MAX_IDLE_CONNECTIONS
        @Networks val keepAliveDurations = Network.KEEP_ALIVE_DURATION

        val connectionPool = ConnectionPool(maxIdleConnections.toInt(), keepAliveDurations, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
                .client(getOkHttpClient(cache, connectionPool))
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(Api::class.java)
    }

    override fun getMoviesCall(page: Int): Single<List<Movie>> {
        return api.getMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}