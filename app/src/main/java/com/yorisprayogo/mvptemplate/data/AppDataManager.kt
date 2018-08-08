package com.yorisprayogo.mvptemplate.data

import android.content.Context
import com.yorisprayogo.mvptemplate.data.model.Movie
import com.yorisprayogo.mvptemplate.data.network.ApiHelper
import com.yorisprayogo.mvptemplate.data.network.AppApiHelper
import com.yorisprayogo.mvptemplate.data.pref.AppPreferencesHelper
import com.yorisprayogo.mvptemplate.data.pref.PreferencesHelper
import com.yorisprayogo.mvptemplate.di.ApplicationContext
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

class AppDataManager @Inject constructor(
        @ApplicationContext var context: Context,
        private var apiHelper: AppApiHelper,
        private var preferencesHelper: AppPreferencesHelper
) : DataManager {

    override fun updateApiHeader(userId: Long?, accessToken: String) {
    }

    override fun setUserAsLoggedOut() {
        updateUserInfo(
                "", null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT, "", "", "")
    }

    override fun seedDatabaseQuestions(): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun seedDatabaseOptions(): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserInfo(accessToken: String, userId: Long?, loggedInMode: DataManager.LoggedInMode, userName: String, email: String, profilePicPath: String) {

        currentUserId = userId
        this.accessToken = accessToken

        setCurrentUserLoggedInMode(loggedInMode)

        currentUserName = userName
        currentUserEmail = email
        currentUserProfilePicUrl = profilePicPath
    }

    override val currentUserLoggedInMode: Int
        get() = preferencesHelper.currentUserLoggedInMode

    override var currentUserId: Long?
        get() = preferencesHelper.currentUserId
        set(value) { preferencesHelper.currentUserId = value }

    override var currentUserName: String?
        get() = preferencesHelper.currentUserName
        set(value) { preferencesHelper.currentUserName = value }

    override var currentUserEmail: String?
        get() = preferencesHelper.currentUserEmail
        set(value) { preferencesHelper.currentUserEmail = value }

    override var currentUserProfilePicUrl: String?
        get() = preferencesHelper.currentUserProfilePicUrl
        set(value) { preferencesHelper.currentUserProfilePicUrl = value }

    override var accessToken: String?
        get() = preferencesHelper.accessToken
        set(value) { preferencesHelper.accessToken = value }

    override fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode) {
        preferencesHelper.setCurrentUserLoggedInMode(mode)
    }

    override fun getMoviesCall(page: Int): Single<List<Movie>> {
        return apiHelper.getMoviesCall(page)
    }

}