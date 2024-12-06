package ru.miel.di.modules

import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Module
import dagger.Provides
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase
import ru.miel.domain.usecase.GetCandidatesDbUseCase
import ru.miel.view.showcase.ShowcaseViewModel

@Module
class AppModule() {

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    fun provideHomeViewModelFactory(
        filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase,
        getCandidatesDbUseCase: GetCandidatesDbUseCase,
    ) = ShowcaseViewModel.Factory(
        filDbWithSampleDataUseCase = filDbWithSampleDataUseCase,
        getCandidatesDbUseCase = getCandidatesDbUseCase,

        )
}
