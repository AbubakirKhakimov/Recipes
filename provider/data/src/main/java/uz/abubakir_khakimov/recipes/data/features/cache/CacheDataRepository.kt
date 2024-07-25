package uz.abubakir_khakimov.recipes.data.features.cache

interface CacheDataRepository {

    fun <T> getData(key: String): T

    fun <T> getData(key: String, defaultValue: T): T

    fun <T> saveData(key: String, data: T)

    fun deleteData(key: String)

    fun containsData(key: String): Boolean
}