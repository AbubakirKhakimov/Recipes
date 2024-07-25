package uz.abubakir_khakimov.recipes.network.features.ingredients.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.abubakir_khakimov.recipes.network.features.ingredients.api.IngredientsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class IngredientsApiModule {

    @Provides
    @Singleton
    fun provideIngredientsApi(retrofit: Retrofit): IngredientsApi =
        retrofit.create(IngredientsApi::class.java)
}