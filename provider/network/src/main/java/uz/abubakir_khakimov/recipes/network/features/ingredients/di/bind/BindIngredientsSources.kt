package uz.abubakir_khakimov.recipes.network.features.ingredients.di.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.network.features.ingredients.IngredientsNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.ingredients.sources.IngredientsNetworkDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindIngredientsSources {

    @Binds
    @Singleton
    fun bindIngredientsNetworkDataSource(
        ingredientsNetworkDataSourceImpl: IngredientsNetworkDataSourceImpl
    ): IngredientsNetworkDataSource
}