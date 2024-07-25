package uz.abubakir_khakimov.recipes.home.domain.usecase

import uz.abubakir_khakimov.recipes.home.domain.repositories.MealsRepository
import javax.inject.Inject

class DeleteFavouriteMealByIdUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {

    suspend fun invoke(id: String) = mealsRepository.deleteFavouriteMealById(id = id)
}