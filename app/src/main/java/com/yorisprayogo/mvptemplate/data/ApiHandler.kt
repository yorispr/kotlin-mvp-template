//package com.amartha.disbursement.data.rest
//
//import com.amartha.investor.data.model.BaseResponse
//import com.amartha.investor.data.rest.getErrorBodyMessage
//import com.amartha.investor.utils.DisposableManager
//import io.reactivex.SingleObserver
//import io.reactivex.disposables.Disposable
//import javax.inject.Inject
//
///**
// * Created by YORIS on 30/01/18.
// */
//
//object ApiHandler {
//    fun <T> createSingleHandler(callback: ApiCallback<T>): SingleObserver<BaseResponse<T>> {
//        return object : SingleObserver<BaseResponse<T>> {
//
//            override fun onSubscribe(d: Disposable){
//                //disposableManager.add(d)
//            }
//
//            override fun onSuccess(response: BaseResponse<T>) {
//                callback.onSuccess(response.data)
//            }
//
//            override fun onError(e: Throwable) {
//                callback.onError(getErrorBodyMessage(e))
//            }
//        }
//    }
//
////    fun <T> createMaybeHandler(callback: ApiCallback<T>): MaybeObserver<BaseResponse<T>> {
////        return object : MaybeObserver<BaseResponse<T>> {
////            override fun onSubscribe(d: Disposable) {
////                println("HANDLERZ : onSubscribe" )
////            }
////            override fun onComplete() {
////                println("HANDLERZ : onComplete" )
////            }
////            override fun onSuccess(t: BaseResponse<T>) {
////                println("HANDLERZ : onSuccess" )
////            }
////            override fun onError(e: Throwable) {
////                println("HANDLERZ : onError" )
////            }
////        }
////    }
////
////    fun <T> createObservable(callback: ApiCallback<T>): Observer<BaseResponse<T>> {
////        return object : Observer<BaseResponse<T>> {
////            override fun onComplete() {
////                println("HANDLERZ : onComplete" )
////            }
////
////            override fun onSubscribe(d: Disposable) {
////                println("HANDLERZ : onSubscribe" )
////            }
////
////            override fun onNext(t: BaseResponse<T>) {
////                callback.onSuccess(t.data)
////                println("HANDLERZ : onNext" )
////            }
////
////            override fun onError(e: Throwable) {
////                callback.onError(e.message)
////                println("HANDLERZ : onError" )
////            }
////        }
////    }
//
//}
