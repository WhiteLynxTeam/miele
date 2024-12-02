package ru.miel.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.miel.view.activity.MainActivity
import ru.miel.view.administrator.ShowcaseFragment
import ru.miel.view.auth.AuthFragment

@Module
interface MainModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindAuthFragment(): AuthFragment

    @ContributesAndroidInjector
    fun bindShowcaseFragment(): ShowcaseFragment
}