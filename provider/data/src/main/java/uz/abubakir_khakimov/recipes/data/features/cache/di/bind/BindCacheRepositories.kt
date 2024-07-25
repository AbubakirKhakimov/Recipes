package uz.abubakir_khakimov.recipes.data.features.cache.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.data.features.cache.CacheDataRepository
import uz.abubakir_khakimov.recipes.data.features.cache.repositories.CacheDataRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindCacheRepositories {

    @Binds
    @Singleton
    fun bindCacheDataRepository(cacheDataRepositoryImpl: CacheDataRepositoryImpl): CacheDataRepository
}