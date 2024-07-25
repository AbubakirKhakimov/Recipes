package uz.abubakir_khakimov.recipes.local.cache.sources

import com.orhanobut.hawk.Hawk
import uz.abubakir_khakimov.recipes.local.cache.CacheDataSource
import javax.inject.Inject

class CacheDataSourceImpl @Inject constructor(): CacheDataSource {

    override fun <T> getData(key: String): T = Hawk.get(key)

    override fun <T> getData(key: String, defaultValue: T): T = Hawk.get(key, defaultValue)

    override fun <T> saveData(key: String, data: T) {
        Hawk.put(key, data)
    }

    override fun deleteData(key: String) {
        Hawk.delete(key)
    }

    override fun containsData(key: String): Boolean = Hawk.contains(key)
}