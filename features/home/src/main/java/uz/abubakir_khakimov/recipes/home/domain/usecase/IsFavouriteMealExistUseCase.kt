package uz.abubakir_khakimov.recipes.home.domain.usecase

import uz.abubakir_khakimov.recipes.home.domain.repositories.MealsRepository
import uz.abubakir_khakimov.recipes.common.utils.Result
import javax.inject.Inject

class IsFavouriteMealExistUseCase @Inject constructor(
    private val mealsRepository: MealsRepository
) {

    suspend fun invoke(id: String): Result<Boolean> = mealsRepository.isFavouriteMealExist(id)
}