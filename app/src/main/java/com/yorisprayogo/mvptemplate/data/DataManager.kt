package com.yorisprayogo.mvptemplate.data

import com.yorisprayogo.mvptemplate.data.network.ApiHelper
import com.yorisprayogo.mvptemplate.data.pref.PreferencesHelper
import io.reactivex.Observable
import javax.inject.Singleton

interface DataManager : PreferencesHelper, ApiHelper {

    fun updateApiHeader(userId: Long?, accessToken: String)

    fun setUserAsLoggedOut()

    fun seedDatabaseQuestions(): Observable<Boolean>

    fun seedDatabaseOptions(): Observable<Boolean>

    fun updateUserInfo(
            accessToken: String,
            userId: Long?,
            loggedInMode: LoggedInMode,
            userName: String,
            email: String,
            profilePicPath: String)

    enum class LoggedInMode private constructor(val type: Int) {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3)
    }
}
