package uz.abubakir_khakimov.recipes.features.home.repositories

import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.data.features.meals.MealsDataRepository
import uz.abubakir_khakimov.recipes.data.features.meals.entities.MealDataEntity
import uz.abubakir_khakimov.recipes.home.domain.models.Meal
import uz.abubakir_khakimov.recipes.home.domain.repositories.MealsRepository
import uz.abubakir_khakimov.recipes.common.utils.Result
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val mealsDataRepository: MealsDataRepository,
    private val mealMapper: EntityMapper<MealDataEntity, Meal>,
    private val mealToDataMapper: EntityMapper<Meal, MealDataEntity>,
): MealsRepository {

    override suspend fun getMealsByCategory(category: String): Result<List<Meal>> =
        mealsDataRepository.getMealsByCategory(category = category)
            .let { mealMapper.mapToResultList(it) }

    override suspend fun getMealsByArea(area: String): Result<List<Meal>> =
        mealsDataRepository.getMealsByArea(area = area).let { mealMapper.mapToResultList(it) }

    override suspend fun getMealsByIngredient(ingredient: String): Result<List<Meal>> =
        mealsDataRepository.getMealsByIngredient(ingredient = ingredient)
            .let { mealMapper.mapToResultList(it) }

    override suspend fun getMealsBySearch(search: String): Result<List<Meal>> =
        mealsDataRepository.getMealsBySearch(search = search).let { mealMapper.mapToResultList(it) }

    override suspend fun isFavouriteMealExist(id: String): Result<Boolean> =
        mealsDataRepository.isFavouriteMealExist(id = id)

    override suspend fun addFavouriteMeal(meal: Meal)  =
        mealsDataRepository.addFavouriteMeal(meal = mealToDataMapper.mapTo(entity = meal))

    override suspend fun deleteFavouriteMealById(id: String) =
        mealsDataRepository.deleteFavouriteMealById(id = id)
}