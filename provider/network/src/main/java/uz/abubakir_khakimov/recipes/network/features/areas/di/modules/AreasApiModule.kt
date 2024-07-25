package uz.abubakir_khakimov.recipes.network.features.areas.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.abubakir_khakimov.recipes.network.features.areas.api.AreasApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AreasApiModule {

    @Provides
    @Singleton
    fun provideAreasApi(retrofit: Retrofit): AreasApi = retrofit.create(AreasApi::class.java)
}