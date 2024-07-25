package uz.abubakir_khakimov.recipes.home.domain.usecase

import uz.abubakir_khakimov.recipes.home.domain.models.Meal
import uz.abubakir_khakimov.recipes.home.domain.repositories.MealsRepository
import uz.abubakir_khakimov.recipes.common.utils.Result
import javax.inject.Inject

class GetMealsByAreaUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {

    suspend fun invoke(area: String): Result<List<Meal>> = mealsRepository.getMealsByArea(area)
}