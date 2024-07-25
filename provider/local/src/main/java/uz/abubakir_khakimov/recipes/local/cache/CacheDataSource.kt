package uz.abubakir_khakimov.recipes.local.cache

interface CacheDataSource {

    fun <T> getData(key: String): T

    fun <T> getData(key: String, defaultValue: T): T

    fun <T> saveData(key: String, data: T)

    fun deleteData(key: String)

    fun containsData(key: String): Boolean
}