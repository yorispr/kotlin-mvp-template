package com.yorisprayogo.mvptemplate.view.base

import com.yorisprayogo.mvptemplate.data.AppDataManager
import com.yorisprayogo.mvptemplate.data.DataManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BasePresenter<V : MvpView>
@Inject
constructor(private val dataManager: DataManager,
            private val compositeDisposable: CompositeDisposable) : MvpPresenter<V> {

    var view: V? = null
        private set

    val isViewAttached: Boolean
        get() = view != null

    override fun onAttach(mvpView: V) {
        this.view = mvpView
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

//    fun handleApiError(error: ANError?) {
//
//        if (error == null || error!!.getErrorBody() == null) {
//            mvpView!!.onError(R.string.api_default_error)
//            return
//        }
//
//        if (error!!.getErrorCode() === AppConstants.API_STATUS_CODE_LOCAL_ERROR && error!!.getErrorDetail().equals(ANConstants.CONNECTION_ERROR)) {
//            mvpView!!.onError(R.string.connection_error)
//            return
//        }
//
//        if (error!!.getErrorCode() === AppConstants.API_STATUS_CODE_LOCAL_ERROR && error!!.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
//            mvpView!!.onError(R.string.api_retry_error)
//            return
//        }
//
//        val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
//        val gson = builder.create()
//
//        try {
//            val apiError = gson.fromJson(error!!.getErrorBody(), ApiError::class.java)
//
//            if (apiError == null || apiError!!.getMessage() == null) {
//                mvpView!!.onError(R.string.api_default_error)
//                return
//            }
//
//            when (error!!.getErrorCode()) {
//                HttpsURLConnection.HTTP_UNAUTHORIZED, HttpsURLConnection.HTTP_FORBIDDEN -> {
//                    setUserAsLoggedOut()
//                    mvpView!!.openActivityOnTokenExpire()
//                    mvpView!!.onError(apiError!!.getMessage())
//                }
//                HttpsURLConnection.HTTP_INTERNAL_ERROR, HttpsURLConnection.HTTP_NOT_FOUND -> mvpView!!.onError(apiError!!.getMessage())
//                else -> mvpView!!.onError(apiError!!.getMessage())
//            }
//        } catch (e: JsonSyntaxException) {
//            Log.e(TAG, "handleApiError", e)
//            mvpView!!.onError(R.string.api_default_error)
//        } catch (e: NullPointerException) {
//            Log.e(TAG, "handleApiError", e)
//            mvpView!!.onError(R.string.api_default_error)
//        }
//
//    }

    override fun setUserAsLoggedOut() {
//        dataManager.setAccessToken(null)
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter")

    companion object {

        private val TAG = "BasePresenter"
    }
}

