package ru.miel.di.modules

import dagger.Module
import dagger.Provides
import ru.miel.domain.usecase.AuthApiUseCase
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase
import ru.miel.domain.usecase.GetCandidatesApiUseCase
import ru.miel.domain.usecase.GetCandidatesDbUseCase
import ru.miel.domain.usecase.GetFavoritesDbUseCase
import ru.miel.domain.usecase.SetFavoriteDbUseCase
import ru.miel.view.auth.AuthViewModel
import ru.miel.view.favorites.FavoritesViewModel
import ru.miel.view.showcase.ShowcaseViewModel

@Module
class AppModule() {

    @Provides
    fun provideShowcaseViewModelFactory(
        getCandidatesDbUseCase: GetCandidatesDbUseCase,
        getCandidatesApiUseCase: GetCandidatesApiUseCase,
        setFavoriteDbUseCase: SetFavoriteDbUseCase,
    ) = ShowcaseViewModel.Factory(
        getCandidatesDbUseCase = getCandidatesDbUseCase,
        getCandidatesApiUseCase = getCandidatesApiUseCase,
        setFavoriteDbUseCase = setFavoriteDbUseCase,
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
}
