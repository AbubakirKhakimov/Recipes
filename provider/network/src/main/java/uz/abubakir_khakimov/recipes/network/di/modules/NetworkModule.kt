package uz.abubakir_khakimov.recipes.network.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.abubakir_khakimov.recipes.security.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(/* baseUrl = */ BuildConfig.RECIPES_API_BASE_URL)
        .addConverterFactory(/* factory = */ GsonConverterFactory.create())
        .build()
}