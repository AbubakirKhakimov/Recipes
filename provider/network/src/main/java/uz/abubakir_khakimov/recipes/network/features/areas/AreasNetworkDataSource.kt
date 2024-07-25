package uz.abubakir_khakimov.recipes.network.features.areas

import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.network.features.areas.entities.AreaNetworkEntity

interface AreasNetworkDataSource {

    suspend fun getAreas(): Result<List<AreaNetworkEntity>>
}