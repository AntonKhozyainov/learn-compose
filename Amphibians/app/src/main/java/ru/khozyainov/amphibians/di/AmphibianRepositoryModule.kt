package ru.khozyainov.amphibians.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.khozyainov.amphibians.data.AmphibianRepository
import ru.khozyainov.amphibians.data.AmphibianRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class AmphibianRepositoryModule {
    @Binds
    abstract fun providesRepository(repositoryImpl: AmphibianRepositoryImpl): AmphibianRepository
}