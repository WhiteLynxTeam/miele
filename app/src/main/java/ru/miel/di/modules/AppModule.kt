package ru.miel.di.modules

import dagger.Module
import dagger.Provides
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase
import ru.miel.domain.usecase.candidates.GetCandidatesApiUseCase
import ru.miel.domain.usecase.candidates.GetCandidatesDbUseCase
import ru.miel.domain.usecase.candidates.GetCountInvitationsApiUseCase
import ru.miel.domain.usecase.candidates.GetFavoritesDbUseCase
import ru.miel.domain.usecase.candidates.GetInvitationsApiUseCase
import ru.miel.domain.usecase.candidates.SetFavoriteApiUseCase
import ru.miel.domain.usecase.candidates.SetFavoriteDbUseCase
import ru.miel.domain.usecase.candidates.SetInvitationDbUseCase
import ru.miel.domain.usecase.candidates.SetInvitationsApiUseCase
import ru.miel.domain.usecase.quotes.GetQuotesApiUseCase
import ru.miel.domain.usecase.quotes.GetQuotesByNowDbUseCase
import ru.miel.domain.usecase.quotes.MinusQuoteDbUseCase
import ru.miel.domain.usecase.user.AuthApiUseCase
import ru.miel.domain.usecase.user.GetFullNamePrefUseCase
import ru.miel.domain.usecase.user.GetPhotoUserPrefUseCase
import ru.miel.view.activity.ActivityMainViewModel
import ru.miel.view.auth.AuthViewModel
import ru.miel.view.favorites.FavoritesViewModel
import ru.miel.view.greetings.GreetingViewModel
import ru.miel.view.invitations.InvitationsViewModel
import ru.miel.view.office.statistics.StatisticsViewModel
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
        setFavoriteApiUseCase: SetFavoriteApiUseCase,
        setFavoriteDbUseCase: SetFavoriteDbUseCase,
        setInvitationDbUseCase: SetInvitationDbUseCase,
        getQuotesByNowDbUseCase: GetQuotesByNowDbUseCase,
        minusQuoteDbUseCase: MinusQuoteDbUseCase,
        getQuotesApiUseCase: GetQuotesApiUseCase,
        setInvitationsApiUseCase: SetInvitationsApiUseCase,
    ) = ShowcaseViewModel.Factory(
        getCandidatesDbUseCase = getCandidatesDbUseCase,
        getCandidatesApiUseCase = getCandidatesApiUseCase,
        setFavoriteApiUseCase = setFavoriteApiUseCase,
        setFavoriteDbUseCase = setFavoriteDbUseCase,
        setInvitationDbUseCase = setInvitationDbUseCase,
        getQuotesByNowDbUseCase = getQuotesByNowDbUseCase,
        minusQuoteDbUseCase = minusQuoteDbUseCase,
        getQuotesApiUseCase = getQuotesApiUseCase,
        setInvitationsApiUseCase = setInvitationsApiUseCase,
    )

    @Provides
    fun provideInvitationsViewModelFactory(
        getCandidatesApiUseCase: GetCandidatesApiUseCase,
        getInvitationsApiUseCase: GetInvitationsApiUseCase,
    ) = InvitationsViewModel.Factory(
        getCandidatesApiUseCase = getCandidatesApiUseCase,
        getInvitationsApiUseCase = getInvitationsApiUseCase,
    )

    @Provides
    fun provideFavoritesViewModelFactory(
        getFavoritesDbUseCase: GetFavoritesDbUseCase,
        getCandidatesApiUseCase: GetCandidatesApiUseCase,
        setInvitationsApiUseCase: SetInvitationsApiUseCase,
    ) = FavoritesViewModel.Factory(
        getFavoritesDbUseCase = getFavoritesDbUseCase,
        getCandidatesApiUseCase = getCandidatesApiUseCase,
        setInvitationsApiUseCase = setInvitationsApiUseCase,
    )

    @Provides
    fun provideStatisticsViewModelFactory(
        getQuotesApiUseCase: GetQuotesApiUseCase,
        getCountInvitationsApiUseCase: GetCountInvitationsApiUseCase,
    ) = StatisticsViewModel.Factory(
        getQuotesApiUseCase = getQuotesApiUseCase,
        getCountInvitationsApiUseCase = getCountInvitationsApiUseCase,
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
