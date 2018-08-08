package com.yorisprayogo.mvptemplate.di.component

import com.yorisprayogo.mvptemplate.di.PerActivity
import com.yorisprayogo.mvptemplate.di.module.ActivityModule
import com.yorisprayogo.mvptemplate.view.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}
