package uz.abubakir_khakimov.recipes.data.features.areas.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.data.features.areas.AreasDataRepository
import uz.abubakir_khakimov.recipes.data.features.areas.repositories.AreasDataRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindAreasRepositories {

    @Binds
    @Singleton
    fun bindAreasDataRepository(
        areasDataRepositoryImpl: AreasDataRepositoryImpl
    ): AreasDataRepository
}