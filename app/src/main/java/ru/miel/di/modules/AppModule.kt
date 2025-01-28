package ru.miel.di.modules

import dagger.Module
import dagger.Provides
import ru.miel.domain.usecase.user.AuthApiUseCase
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase
import ru.miel.domain.usecase.candidates.GetCandidatesApiUseCase
import ru.miel.domain.usecase.candidates.GetCandidatesDbUseCase
import ru.miel.domain.usecase.candidates.GetFavoritesDbUseCase
import ru.miel.domain.usecase.candidates.GetQuotesByNowDbUseCase
import ru.miel.domain.usecase.candidates.MinusQuoteDbUseCase
import ru.miel.domain.usecase.candidates.SetFavoriteDbUseCase
import ru.miel.domain.usecase.candidates.SetInvitationDbUseCase
import ru.miel.domain.usecase.user.GetFullNamePrefUseCase
import ru.miel.domain.usecase.user.GetPhotoUserPrefUseCase
import ru.miel.view.activity.ActivityMainViewModel
import ru.miel.view.auth.AuthViewModel
import ru.miel.view.favorites.FavoritesViewModel
import ru.miel.view.greetings.GreetingViewModel
import ru.miel.view.showcase.ShowcaseViewModel

@Module
class AppModule() {

    @Provides
    fun provideActivityMainViewModelFactory(
        getFullNamePrefUseCase: GetFullNamePrefUseCase,
        getPhotoUserPrefUseCase: GetPhotoUserPrefUseCase,
    ) = ActivityMainViewModel.Factory(
        getFullNamePrefUseCase = getFullNamePrefUseCase,
        getPhotoUserPrefUseCase = getPhotoUserPrefUseCase,
        )

    @Provides
    fun provideShowcaseViewModelFactory(
        getCandidatesDbUseCase: GetCandidatesDbUseCase,
        getCandidatesApiUseCase: GetCandidatesApiUseCase,
        setFavoriteDbUseCase: SetFavoriteDbUseCase,
        setInvitationDbUseCase: SetInvitationDbUseCase,
        getQuotesByNowDbUseCase: GetQuotesByNowDbUseCase,
        minusQuoteDbUseCase: MinusQuoteDbUseCase,
    ) = ShowcaseViewModel.Factory(
        getCandidatesDbUseCase = getCandidatesDbUseCase,
        getCandidatesApiUseCase = getCandidatesApiUseCase,
        setFavoriteDbUseCase = setFavoriteDbUseCase,
        setInvitationDbUseCase = setInvitationDbUseCase,
        getQuotesByNowDbUseCase = getQuotesByNowDbUseCase,
        minusQuoteDbUseCase = minusQuoteDbUseCase,
        )

    @Provides
    fun provideFavoritesViewModelFactory(
        getFavoritesDbUseCase: GetFavoritesDbUseCase,
    ) = FavoritesViewModel.Factory(
        getFavoritesDbUseCase = getFavoritesDbUseCase,
        )

    @Provides
    fun provideAuthViewModelFactory(
        authApiUseCase: AuthApiUseCase,
        filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase,
    ) = AuthViewModel.Factory(
        authApiUseCase = authApiUseCase,
        filDbWithSampleDataUseCase = filDbWithSampleDataUseCase,
    )

    @Provides
    fun provideGreetingViewModelFactory(
        getFullNamePrefUseCase: GetFullNamePrefUseCase,
    ) = GreetingViewModel.Factory(
        getFullNamePrefUseCase = getFullNamePrefUseCase,
    )
}
