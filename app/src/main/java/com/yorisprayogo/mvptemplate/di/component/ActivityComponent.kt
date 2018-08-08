package com.yorisprayogo.mvptemplate.di.component

import com.yorisprayogo.mvptemplate.di.PerActivity
import com.yorisprayogo.mvptemplate.di.module.ActivityModule
import com.yorisprayogo.mvptemplate.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@PerActivity
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}
