package uz.abubakir_khakimov.recipes.network.features.areas.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.network.features.areas.AreasNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.areas.sources.AreasNetworkDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindAreasSources {

    @Binds
    @Singleton
    fun bindAreasNetworkDataSource(
        areasNetworkDataSourceImpl: AreasNetworkDataSourceImpl
    ): AreasNetworkDataSource
}