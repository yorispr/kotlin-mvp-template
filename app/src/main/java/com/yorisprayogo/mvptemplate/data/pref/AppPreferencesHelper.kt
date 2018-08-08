package com.yorisprayogo.mvptemplate.data.pref

import android.content.Context
import android.content.SharedPreferences
import com.example.mvp_google_test.NULL_INDEX
import com.example.mvp_google_test.PREF_NAME
import com.yorisprayogo.mvptemplate.data.DataManager
import com.yorisprayogo.mvptemplate.di.ApplicationContext
import com.yorisprayogo.mvptemplate.di.PreferenceInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferencesHelper @Inject
constructor(@ApplicationContext val context: Context) : PreferencesHelper {

    private val mPrefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override var currentUserId: Long?
        get() {
            val userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, NULL_INDEX)
            return if (userId == NULL_INDEX) null else userId
        }
        set(userId) {
            val id = userId ?: NULL_INDEX
            mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply()
        }

    override var currentUserName: String?
        get() = mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null)
        set(userName) = mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply()

    override var currentUserEmail: String?
        get() = mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
        set(email) = mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()

    override var currentUserProfilePicUrl: String?
        get() = mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null)
        set(profilePicUrl) =
            mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply()

    override val currentUserLoggedInMode: Int
        get() = mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type)

    override var accessToken: String?
        get() = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
        set(accessToken) = mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()

    override fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.type).apply()
    }

    companion object {

        private val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"
        private val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
        private val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
        private val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
        private val PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL"
        private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    }
}
