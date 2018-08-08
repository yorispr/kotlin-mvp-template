package com.yorisprayogo.mvptemplate.di.module

import android.app.Application
import android.content.Context
import com.example.mvp_google_test.PREF_NAME
import com.yorisprayogo.mvptemplate.data.AppDataManager
import com.yorisprayogo.mvptemplate.data.DataManager
import com.yorisprayogo.mvptemplate.data.network.AppApiHelper
import com.yorisprayogo.mvptemplate.data.network.ApiHelper
import com.yorisprayogo.mvptemplate.data.pref.AppPreferencesHelper
import com.yorisprayogo.mvptemplate.data.pref.PreferencesHelper
import com.yorisprayogo.mvptemplate.di.ApplicationContext
import com.yorisprayogo.mvptemplate.di.PerActivity
import com.yorisprayogo.mvptemplate.di.PreferenceInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return PREF_NAME
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }

//    @Provides
//    @Singleton
//    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
//        return appDbHelper
//    }

//    @Provides
//    @Singleton
//    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
//        return appPreferencesHelper
//    }


//    @Provides
//    @Singleton
//    fun provideProtectedApiHeader(@ApiInfo apiKey: String,
//                                           preferencesHelper: PreferencesHelper): ApiHeader.ProtectedApiHeader {
//        return ApiHeader.ProtectedApiHeader(
//                apiKey,
//                preferencesHelper.getCurrentUserId(),
//                preferencesHelper.getAccessToken())
//    }
//
//    @Provides
//    @Singleton
//    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
//        return CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//    }
}
