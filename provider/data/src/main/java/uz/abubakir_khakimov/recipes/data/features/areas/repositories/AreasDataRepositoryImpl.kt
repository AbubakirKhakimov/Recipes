package uz.abubakir_khakimov.recipes.data.features.areas.repositories

import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.data.features.areas.AreasDataRepository
import uz.abubakir_khakimov.recipes.data.features.areas.entities.AreaDataEntity
import uz.abubakir_khakimov.recipes.network.features.areas.AreasNetworkDataSource
import uz.abubakir_khakimov.recipes.network.features.areas.entities.AreaNetworkEntity
import javax.inject.Inject

class AreasDataRepositoryImpl @Inject constructor(
    private val areasNetworkDataSource: AreasNetworkDataSource,
    private val networkMapper: EntityMapper<AreaNetworkEntity, AreaDataEntity>,
): AreasDataRepository {

    override suspend fun getAreas(): Result<List<AreaDataEntity>> =
        areasNetworkDataSource.getAreas().let {
            networkMapper.mapToResultList(it)
        }
}