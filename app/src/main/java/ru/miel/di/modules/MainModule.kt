package ru.miel.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.miel.view.activity.MainActivity

@Module
interface MainModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}