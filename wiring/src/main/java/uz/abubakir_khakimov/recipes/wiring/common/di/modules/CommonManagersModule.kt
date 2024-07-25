package uz.abubakir_khakimov.recipes.wiring.common.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.common.managers.CheckNetworkManager
import uz.abubakir_khakimov.recipes.common.managers.Logger
import uz.abubakir_khakimov.recipes.common_impl.CheckNetworkManagerImpl
import uz.abubakir_khakimov.recipes.common_impl.LoggerImpl

@Module
@InstallIn(SingletonComponent::class)
class CommonManagersModule {

    @Provides
    fun provideLogger(): Logger = LoggerImpl()

    @Provides
    fun provideCheckNetworkManager(
        @ApplicationContext context: Context
    ): CheckNetworkManager = CheckNetworkManagerImpl(context = context)
}