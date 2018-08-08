package com.yorisprayogo.mvptemplate.view

import android.graphics.Movie
import com.yorisprayogo.mvptemplate.di.PerActivity
import com.yorisprayogo.mvptemplate.view.base.MvpPresenter
import com.yorisprayogo.mvptemplate.view.base.MvpView

interface MainContract {

    interface MainView: MvpView {
        fun updateData(list: List<Movie>)
    }

    @PerActivity
    interface MainPresenter<V: MainView>: MvpPresenter<V> {
        fun fetchMovies()
    }
}