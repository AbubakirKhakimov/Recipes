package uz.abubakir_khakimov.recipes.network.features.meals.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.abubakir_khakimov.recipes.network.features.meals.api.MealsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MealsApiModule {

    @Provides
    @Singleton
    fun provideMealsApi(retrofit: Retrofit): MealsApi = retrofit.create(MealsApi::class.java)
}