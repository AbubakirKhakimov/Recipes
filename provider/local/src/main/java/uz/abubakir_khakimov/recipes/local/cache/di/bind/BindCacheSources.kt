package uz.abubakir_khakimov.recipes.local.cache.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.local.cache.CacheDataSource
import uz.abubakir_khakimov.recipes.local.cache.sources.CacheDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindCacheSources {

    @Binds
    @Singleton
    fun bindCacheDataSource(cacheDataSourceImpl: CacheDataSourceImpl): CacheDataSource
}