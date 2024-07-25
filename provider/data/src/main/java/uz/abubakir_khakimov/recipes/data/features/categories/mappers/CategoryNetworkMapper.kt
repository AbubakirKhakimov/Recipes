package uz.abubakir_khakimov.recipes.data.features.categories.mappers

import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.common.utils.Result.Companion.map
import uz.abubakir_khakimov.recipes.data.features.categories.entities.CategoryDataEntity
import uz.abubakir_khakimov.recipes.data.features.meals.entities.MealDataEntity
import uz.abubakir_khakimov.recipes.network.features.categories.entities.CategoryNetworkEntity
import uz.abubakir_khakimov.recipes.network.features.meals.entities.MealNetworkEntity

internal class CategoryNetworkMapper: EntityMapper<CategoryNetworkEntity, CategoryDataEntity> {

    override fun mapTo(entity: CategoryNetworkEntity): CategoryDataEntity =
        CategoryDataEntity(
            idCategory = entity.idCategory,
            strCategory = entity.strCategory,
            strCategoryDescription = entity.strCategoryDescription,
            strCategoryThumb = entity.strCategoryThumb
        )

    override fun mapToList(entityList: List<CategoryNetworkEntity>): List<CategoryDataEntity> =
        entityList.map { mapTo(entity = it) }

    override fun mapToResultList(
        entityResultList: Result<List<CategoryNetworkEntity>>
    ): Result<List<CategoryDataEntity>> = entityResultList.map { attendanceList ->
        mapToList(entityList = attendanceList)
    }

    override fun mapToResult(entityResult: Result<CategoryNetworkEntity>): Result<CategoryDataEntity> =
        entityResult.map { mapTo(entity = it) }
}