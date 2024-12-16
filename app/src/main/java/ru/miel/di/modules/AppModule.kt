package ru.miel.di.modules

import dagger.Module
import dagger.Provides
import ru.miel.domain.usecase.AuthApiUseCase
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase
import ru.miel.domain.usecase.GetCandidatesApiUseCase
import ru.miel.domain.usecase.GetCandidatesDbUseCase
import ru.miel.view.auth.AuthViewModel
import ru.miel.view.showcase.ShowcaseViewModel

@Module
class AppModule() {

    @Provides
    fun provideShowcaseViewModelFactory(
        filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase,
        getCandidatesDbUseCase: GetCandidatesDbUseCase,
        getCandidatesApiUseCase: GetCandidatesApiUseCase,
    ) = ShowcaseViewModel.Factory(
        filDbWithSampleDataUseCase = filDbWithSampleDataUseCase,
        getCandidatesDbUseCase = getCandidatesDbUseCase,
        getCandidatesApiUseCase = getCandidatesApiUseCase,
        )

    @Provides
    fun provideAuthViewModelFactory(
        authApiUseCase: AuthApiUseCase,
    ) = AuthViewModel.Factory(
        authApiUseCase = authApiUseCase,
    )
}
