package uz.abubakir_khakimov.recipes.home.domain.usecase

import uz.abubakir_khakimov.recipes.home.domain.models.Meal
import uz.abubakir_khakimov.recipes.home.domain.repositories.MealsRepository
import javax.inject.Inject

class AddFavouriteMealUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {

    suspend fun invoke(meal: Meal) = mealsRepository.addFavouriteMeal(meal = meal)
}