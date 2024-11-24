package ru.miel.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.miel.App
import ru.miel.di.modules.AppModule
import ru.miel.di.modules.DataModule
import ru.miel.di.modules.DatabaseModule
import ru.miel.di.modules.DomainModule
import ru.miel.di.modules.MainModule
import ru.miel.di.modules.MappersModule
import ru.miel.di.modules.RemoteModule
import ru.miel.di.modules.SharedPreferencesModule
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        AppModule::class,
        DatabaseModule::class,
        DataModule::class,
        DomainModule::class,
        MappersModule::class,
        RemoteModule::class,
        SharedPreferencesModule::class,
    ]
)

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder
        fun build(): AppComponent
    }
}

