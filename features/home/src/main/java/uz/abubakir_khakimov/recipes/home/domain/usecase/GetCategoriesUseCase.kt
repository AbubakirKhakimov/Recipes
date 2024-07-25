package uz.abubakir_khakimov.recipes.home.domain.usecase

import uz.abubakir_khakimov.recipes.home.domain.models.Category
import uz.abubakir_khakimov.recipes.home.domain.repositories.CategoriesRepository
import uz.abubakir_khakimov.recipes.common.utils.Result
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) {

    suspend fun invoke(): Result<List<Category>> = categoriesRepository.getCategories()
}