package com.yorisprayogo.mvptemplate

import com.yorisprayogo.mvptemplate.data.AppDataManager
import com.yorisprayogo.mvptemplate.data.DataManager
import com.yorisprayogo.mvptemplate.data.model.Movie
import com.yorisprayogo.mvptemplate.view.MainContract
import com.yorisprayogo.mvptemplate.view.MainPresenter
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    var mockMainView: MainContract.MainView = Mockito.mock(MainContract.MainView::class.java)

    var dataManager: AppDataManager = Mockito.mock(AppDataManager::class.java)

    var loginPresenter = MainPresenter<MainContract.MainView>(dataManager, CompositeDisposable())

    @Before
    @Throws(Exception::class)
    fun setUp() {
        loginPresenter.onAttach(mockMainView)
    }

    @Test
    fun fetchMoviesSuccess(){

    }

    @Test
    fun fetchMoviesError(){

        val response = Throwable(message = "Error")
        doReturn(Observable.just(response))
                .`when`(dataManager).getMoviesCall(1)

        loginPresenter.fetchMovies()
        verify(mockMainView).showMessage("Error")
    }
}
