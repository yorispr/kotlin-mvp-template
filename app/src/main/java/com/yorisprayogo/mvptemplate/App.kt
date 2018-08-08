package com.yorisprayogo.mvptemplate

import android.app.Application
import com.yorisprayogo.mvptemplate.data.DataManager
import com.yorisprayogo.mvptemplate.di.component.AppComponent
import com.yorisprayogo.mvptemplate.di.component.DaggerAppComponent
import com.yorisprayogo.mvptemplate.di.module.ApplicationModule
import javax.inject.Inject

class App : Application(){

//    @Inject
//    lateinit var mDataManager: DataManager

    private var mApplicationComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        mApplicationComponent = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(this)).build()

        mApplicationComponent?.inject(this)
    }

    fun getComponent(): AppComponent? {
        return mApplicationComponent
    }

}