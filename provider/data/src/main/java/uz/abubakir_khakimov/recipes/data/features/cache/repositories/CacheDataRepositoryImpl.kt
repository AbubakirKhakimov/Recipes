package uz.abubakir_khakimov.recipes.data.features.cache.repositories

import uz.abubakir_khakimov.recipes.data.features.cache.CacheDataRepository
import uz.abubakir_khakimov.recipes.local.cache.CacheDataSource
import javax.inject.Inject

class CacheDataRepositoryImpl @Inject constructor(
    private val cacheDataSource: CacheDataSource
): CacheDataRepository {

    override fun <T> getData(key: String): T = cacheDataSource.getData(key)

    override fun <T> getData(key: String, defaultValue: T): T =
        cacheDataSource.getData(key, defaultValue)

    override fun <T> saveData(key: String, data: T) {
        cacheDataSource.saveData(key, data)
    }

    override fun deleteData(key: String) {
        cacheDataSource.deleteData(key)
    }

    override fun containsData(key: String): Boolean = cacheDataSource.containsData(key)
}