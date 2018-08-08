package com.yorisprayogo.mvptemplate.di

import android.support.annotation.LongDef

@Retention(AnnotationRetention.SOURCE)
@LongDef(
        Network.MAX_IDLE_CONNECTIONS,
        Network.KEEP_ALIVE_DURATION,
        Network.CACHE_SIZE
)
annotation class Networks

object Network {
    const val MAX_IDLE_CONNECTIONS = 15L
    const val KEEP_ALIVE_DURATION = 30L * 1000L
    const val CACHE_SIZE = 30L * 1024L * 1024L
}