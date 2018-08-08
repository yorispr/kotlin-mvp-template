package com.yorisprayogo.mvptemplate.data.pref

import com.yorisprayogo.mvptemplate.data.DataManager

interface PreferencesHelper {

    val currentUserLoggedInMode: Int

    var currentUserId: Long?

    var currentUserName: String?

    var currentUserEmail: String?

    var currentUserProfilePicUrl: String?

    var accessToken: String?

    fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode)

}
