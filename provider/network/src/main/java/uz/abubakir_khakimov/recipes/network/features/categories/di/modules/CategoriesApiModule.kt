package uz.abubakir_khakimov.recipes.network.features.categories.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.abubakir_khakimov.recipes.network.features.categories.api.CategoriesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CategoriesApiModule {

    @Provides
    @Singleton
    fun provideCategoriesApi(retrofit: Retrofit): CategoriesApi =
        retrofit.create(CategoriesApi::class.java)
}