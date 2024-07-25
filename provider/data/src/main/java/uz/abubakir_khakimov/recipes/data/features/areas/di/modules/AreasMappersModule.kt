package uz.abubakir_khakimov.recipes.data.features.areas.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.data.features.areas.entities.AreaDataEntity
import uz.abubakir_khakimov.recipes.data.features.areas.mappers.AreaNetworkMapper
import uz.abubakir_khakimov.recipes.network.features.areas.entities.AreaNetworkEntity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AreasMappersModule {

    @Provides
    @Singleton
    fun provideAreaNetworkMapper(): EntityMapper<AreaNetworkEntity, AreaDataEntity> = AreaNetworkMapper()
}