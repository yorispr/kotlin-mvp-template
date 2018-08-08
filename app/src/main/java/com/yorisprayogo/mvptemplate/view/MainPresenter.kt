package com.yorisprayogo.mvptemplate.view

import com.yorisprayogo.mvptemplate.data.AppDataManager
import com.yorisprayogo.mvptemplate.data.DataManager
import com.yorisprayogo.mvptemplate.data.model.Movie
import com.yorisprayogo.mvptemplate.view.base.BasePresenter
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainPresenter<V: MainContract.MainView>
@Inject constructor(var dataManager: DataManager,
             var compositeDisposable: CompositeDisposable) : BasePresenter<V>(dataManager, compositeDisposable), MainContract.MainPresenter<V> {


    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)

//        dataManager.currentUserEmail = "yorisprayogo@gmail.com"
//        println("PREFS ${dataManager.currentUserEmail}")
//        fetchMovies()
    }

    override fun fetchMovies() {
        dataManager.getMoviesCall(1)
            .subscribe(object : SingleObserver<List<Movie>> {
                override fun onSuccess(t: List<Movie>) {
                    println(t)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    view?.showMessage(e.message?:"")
                }

            })
    }

}