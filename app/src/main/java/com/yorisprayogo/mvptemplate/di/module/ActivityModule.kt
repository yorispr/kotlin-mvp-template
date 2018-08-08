package com.yorisprayogo.mvptemplate.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.yorisprayogo.mvptemplate.di.ActivityContext
import com.yorisprayogo.mvptemplate.di.PerActivity
import com.yorisprayogo.mvptemplate.view.MainContract
import com.yorisprayogo.mvptemplate.view.MainPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(val mActivity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return mActivity
    }

    @Provides
    fun provideActivity(): AppCompatActivity {
        return mActivity
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideMainPresenter(
            presenter: MainPresenter<MainContract.MainView>): MainContract.MainPresenter<MainContract.MainView> {
        return presenter
    }

}