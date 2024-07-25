package uz.abubakir_khakimov.recipes.presentation_impl.domain.repositories

interface CacheRepository {

    fun <T> getDataFromCache(key: String): T

    fun <T> getDataFromCache(key: String, defaultValue: T): T

    fun <T> saveDataToCache(key: String, data: T)
}