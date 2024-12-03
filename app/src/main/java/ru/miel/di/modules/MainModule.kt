package ru.miel.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.miel.view.activity.MainActivity
import ru.miel.view.showcase.ShowcaseFragment
import ru.miel.view.auth.AuthFragment
import ru.miel.view.home.HomeFragment

@Module
interface MainModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindAuthFragment(): AuthFragment

    @ContributesAndroidInjector
    fun bindShowcaseFragment(): ShowcaseFragment

    @ContributesAndroidInjector
    fun bindHomeFragment(): HomeFragment
}