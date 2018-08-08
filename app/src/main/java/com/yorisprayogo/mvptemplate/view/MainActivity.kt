package com.yorisprayogo.mvptemplate.view

import android.graphics.Movie
import android.os.Bundle
import com.yorisprayogo.mvptemplate.R
import com.yorisprayogo.mvptemplate.di.PerActivity
import com.yorisprayogo.mvptemplate.view.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.MainView {

    @Inject
    lateinit var presenter: MainContract.MainPresenter<MainContract.MainView>

    override fun updateData(list: List<Movie>) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getActivityComponent()?.inject(this)

        presenter.onAttach(this)
    }
}
