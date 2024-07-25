package uz.abubakir_khakimov.recipes.features.home.repositories

import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.data.features.categories.entities.CategoryDataEntity
import uz.abubakir_khakimov.recipes.home.domain.models.Category
import uz.abubakir_khakimov.recipes.home.domain.repositories.CategoriesRepository
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.data.features.categories.CategoriesDataRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoriesDataRepository: CategoriesDataRepository,
    private val categoryMapper: EntityMapper<CategoryDataEntity, Category>
) : CategoriesRepository {

    override suspend fun getCategories(): Result<List<Category>> =
        categoriesDataRepository.getCategories().let { categoryMapper.mapToResultList(it) }
}