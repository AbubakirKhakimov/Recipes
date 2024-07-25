package uz.abubakir_khakimov.recipes.data.features.areas

import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.data.features.areas.entities.AreaDataEntity
import uz.abubakir_khakimov.recipes.data.features.categories.entities.CategoryDataEntity
import uz.abubakir_khakimov.recipes.network.features.areas.entities.AreaNetworkEntity
import uz.abubakir_khakimov.recipes.network.features.categories.entities.CategoryNetworkEntity

interface AreasDataRepository {

    suspend fun getAreas(): Result<List<AreaDataEntity>>
}