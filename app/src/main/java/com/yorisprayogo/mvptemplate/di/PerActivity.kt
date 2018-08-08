package com.yorisprayogo.mvptemplate.di

import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by janisharali on 27/01/17.
 */

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

