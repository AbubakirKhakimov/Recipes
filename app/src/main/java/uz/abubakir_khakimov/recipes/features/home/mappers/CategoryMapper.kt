package uz.abubakir_khakimov.recipes.features.home.mappers

import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.common.utils.Result.Companion.map
import uz.abubakir_khakimov.recipes.data.features.categories.entities.CategoryDataEntity
import uz.abubakir_khakimov.recipes.home.domain.models.Category

class CategoryMapper: EntityMapper<CategoryDataEntity, Category> {

    override fun mapTo(entity: CategoryDataEntity): Category =
        Category(
            idCategory = entity.idCategory,
            strCategory = entity.strCategory,
            strCategoryDescription = entity.strCategoryDescription,
            strCategoryThumb = entity.strCategoryThumb
        )

    override fun mapToList(entityList: List<CategoryDataEntity>): List<Category> =
        entityList.map { mapTo(entity = it) }

    override fun mapToResultList(
        entityResultList: Result<List<CategoryDataEntity>>
    ): Result<List<Category>> = entityResultList.map { attendanceList ->
        mapToList(entityList = attendanceList)
    }

    override fun mapToResult(entityResult: Result<CategoryDataEntity>): Result<Category> =
        entityResult.map { mapTo(entity = it) }
}