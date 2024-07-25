package uz.abubakir_khakimov.recipes.network.features.meals.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.network.features.meals.MealsNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.meals.sources.MealsNetworkDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindMealsSources {

    @Binds
    @Singleton
    fun bindMealsNetworkDataSource(
        mealsNetworkDataSourceImpl: MealsNetworkDataSourceImpl
    ): MealsNetworkDataSource
}