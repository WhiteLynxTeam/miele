package ru.miel.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.miel.view.activity.MainActivity
import ru.miel.view.showcase.ShowcaseFragment
import ru.miel.view.auth.AuthFragment
import ru.miel.view.chat.ChatFragment
import ru.miel.view.favorites.FavoritesFragment
import ru.miel.view.home.HomeFragment
import ru.miel.view.office.OfficeFragment
import ru.miel.view.office.address.AddressFragment
import ru.miel.view.office.administrator.AdministratorFragment
import ru.miel.view.office.statistics.StatisticsFragment

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

    @ContributesAndroidInjector
    fun bindOfficeFragment(): OfficeFragment

    @ContributesAndroidInjector
    fun bindFavoritesFragment(): FavoritesFragment

    @ContributesAndroidInjector
    fun bindAddressFragment(): AddressFragment

    @ContributesAndroidInjector
    fun bindAdministratorFragment(): AdministratorFragment

    @ContributesAndroidInjector
    fun bindStatisticsFragment(): StatisticsFragment

    @ContributesAndroidInjector
    fun bindChatFragment(): ChatFragment

}