package uz.abubakir_khakimov.recipes.wiring.presentation.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.abubakir_khakimov.recipes.presentation.managers.ActivityViewKeeper
import uz.abubakir_khakimov.recipes.presentation.managers.ConnectivityManager
import uz.abubakir_khakimov.recipes.presentation.managers.HighPriorityBannerManager
import uz.abubakir_khakimov.recipes.presentation.managers.LocaleManager
import uz.abubakir_khakimov.recipes.presentation.managers.PlaceHolderManager
import uz.abubakir_khakimov.recipes.presentation.managers.ResourceManager
import uz.abubakir_khakimov.recipes.presentation.managers.ScreenManager
import uz.abubakir_khakimov.recipes.presentation.managers.ThemeManager
import uz.abubakir_khakimov.recipes.presentation.managers.ToastManager
import uz.abubakir_khakimov.recipes.presentation_impl.domain.repositories.CacheRepository
import uz.abubakir_khakimov.recipes.presentation_impl.managers.ActivityViewKeeperImpl
import uz.abubakir_khakimov.recipes.presentation_impl.managers.ConnectivityManagerImpl
import uz.abubakir_khakimov.recipes.presentation_impl.managers.HighPriorityBannerManagerImpl
import uz.abubakir_khakimov.recipes.presentation_impl.managers.LocaleManagerImpl
import uz.abubakir_khakimov.recipes.presentation_impl.managers.PlaceHolderManagerImpl
import uz.abubakir_khakimov.recipes.presentation_impl.managers.ResourceManagerImpl
import uz.abubakir_khakimov.recipes.presentation_impl.managers.ScreenManagerImpl
import uz.abubakir_khakimov.recipes.presentation_impl.managers.ThemeManagerImpl
import uz.abubakir_khakimov.recipes.presentation_impl.managers.ToastManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PresentationManagersModule {

    @Provides
    @Singleton
    fun provideActivityViewKeeper(): ActivityViewKeeper = ActivityViewKeeperImpl()

    @Provides
    fun provideConnectivityManager(
        @ApplicationContext context: Context,
        toastManager: ToastManager,
        highPriorityBannerManager: HighPriorityBannerManager
    ): ConnectivityManager = ConnectivityManagerImpl(
        context = context,
        toastManager = toastManager,
        highPriorityBannerManager = highPriorityBannerManager
    )

    @Provides
    fun provideHighPriorityBannerManager(
        @ApplicationContext context: Context,
        activityViewKeeper: ActivityViewKeeper
    ): HighPriorityBannerManager = HighPriorityBannerManagerImpl(
        applicationContext = context,
        activityViewKeeper = activityViewKeeper
    )

    @Provides
    fun provideLocaleManager(
        resourceManager: ResourceManager,
        cacheRepository: CacheRepository
    ): LocaleManager = LocaleManagerImpl(
        resourceManager = resourceManager,
        cacheRepository = cacheRepository
    )

    @Provides
    fun provideThemeManager(
        cacheRepository: CacheRepository,
        resourceManager: ResourceManager
    ): ThemeManager = ThemeManagerImpl(
        cacheRepository = cacheRepository,
        resourceManager = resourceManager
    )

    @Provides
    fun provideResourceManager(
        @ApplicationContext context: Context
    ): ResourceManager = ResourceManagerImpl(
        applicationContext = context
    )

    @Provides
    fun provideToastManager(
        @ApplicationContext context: Context,
        resourceManager: ResourceManager
    ): ToastManager = ToastManagerImpl(
        context = context,
        resourceManager = resourceManager
    )

    @Provides
    fun provideScreenManager(
        @ApplicationContext context: Context
    ): ScreenManager = ScreenManagerImpl(
        context = context
    )

    @Provides
    fun providePlaceHolderManager(
        screenManager: ScreenManager
    ): PlaceHolderManager = PlaceHolderManagerImpl(
        screenManager = screenManager
    )
}