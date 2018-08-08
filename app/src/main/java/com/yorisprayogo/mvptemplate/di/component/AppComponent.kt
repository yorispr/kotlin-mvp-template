package com.yorisprayogo.mvptemplate.di.component

import android.app.Application
import android.content.Context

import com.yorisprayogo.mvptemplate.App
import com.yorisprayogo.mvptemplate.di.ApplicationContext
import com.yorisprayogo.mvptemplate.di.module.ApplicationModule

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface AppComponent {

    fun inject(app: App)

    @ApplicationContext
    fun context(): Context

    fun application(): Application
}
