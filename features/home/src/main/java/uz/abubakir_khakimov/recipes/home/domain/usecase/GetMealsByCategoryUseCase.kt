package uz.abubakir_khakimov.recipes.home.domain.usecase

import uz.abubakir_khakimov.recipes.home.domain.models.Meal
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.home.domain.repositories.MealsRepository
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {

    suspend fun invoke(category: String): Result<List<Meal>> =
        mealsRepository.getMealsByCategory(category)
}