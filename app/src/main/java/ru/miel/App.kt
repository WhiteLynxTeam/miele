package ru.miel

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.miel.di.DaggerAppComponent

class App : DaggerApplication() {
        override fun applicationInjector(): AndroidInjector<App> =
        DaggerAppComponent.builder().withContext(applicationContext).build()
}