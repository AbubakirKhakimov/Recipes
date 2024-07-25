package uz.abubakir_khakimov.recipes.features.home.mappers

import uz.abubakir_khakimov.recipes.common.mappers.EntityMapper
import uz.abubakir_khakimov.recipes.common.utils.Result
import uz.abubakir_khakimov.recipes.common.utils.Result.Companion.map
import uz.abubakir_khakimov.recipes.data.features.meals.entities.MealDataEntity
import uz.abubakir_khakimov.recipes.home.domain.models.Meal

class MealMapper: EntityMapper<MealDataEntity, Meal> {

    override fun mapTo(entity: MealDataEntity): Meal =
        Meal(
            dateModified = entity.dateModified,
            idMeal = entity.idMeal,
            strArea = entity.strArea,
            strCategory = entity.strCategory,
            strCreativeCommonsConfirmed = entity.strCreativeCommonsConfirmed,
            strDrinkAlternate = entity.strDrinkAlternate,
            strImageSource = entity.strImageSource,
            strIngredient1 = entity.strIngredient1,
            strIngredient2 = entity.strIngredient2,
            strIngredient10 = entity.strIngredient10,
            strIngredient11 = entity.strIngredient11,
            strIngredient12 = entity.strIngredient12,
            strIngredient13 = entity.strIngredient13,
            strIngredient14 = entity.strIngredient14,
            strIngredient15 = entity.strIngredient15,
            strIngredient16 = entity.strIngredient16,
            strIngredient17 = entity.strIngredient17,
            strIngredient18 = entity.strIngredient18,
            strIngredient19 = entity.strIngredient19,
            strIngredient20 = entity.strIngredient20,
            strIngredient3 = entity.strIngredient3,
            strIngredient4 = entity.strIngredient4,
            strIngredient5 = entity.strIngredient5,
            strIngredient6 = entity.strIngredient6,
            strIngredient7 = entity.strIngredient7,
            strIngredient8 = entity.strIngredient8,
            strIngredient9 = entity.strIngredient9,
            strInstructions = entity.strInstructions,
            strMeal = entity.strMeal,
            strMealThumb = entity.strMealThumb,
            strMeasure1 = entity.strMeasure1,
            strMeasure10 = entity.strMeasure10,
            strMeasure11 = entity.strMeasure11,
            strMeasure12 = entity.strMeasure12,
            strMeasure13 = entity.strMeasure13,
            strMeasure14 = entity.strMeasure14,
            strMeasure15 = entity.strMeasure15,
            strMeasure16 = entity.strMeasure16,
            strMeasure17 = entity.strMeasure17,
            strMeasure18 = entity.strMeasure18,
            strMeasure19 = entity.strMeasure19,
            strMeasure2 = entity.strMeasure2,
            strMeasure20 = entity.strMeasure20,
            strMeasure3 = entity.strMeasure3,
            strMeasure4 = entity.strMeasure4,
            strMeasure5 = entity.strMeasure5,
            strMeasure6 = entity.strMeasure6,
            strMeasure7 = entity.strMeasure7,
            strMeasure8 = entity.strMeasure8,
            strMeasure9 = entity.strMeasure9,
            strSource = entity.strSource,
            strTags = entity.strTags,
            strYoutube = entity.strYoutube
        )

    override fun mapToList(entityList: List<MealDataEntity>): List<Meal> =
        entityList.map { mapTo(entity = it) }

    override fun mapToResultList(
        entityResultList: Result<List<MealDataEntity>>
    ): Result<List<Meal>> = entityResultList.map { attendanceList ->
        mapToList(entityList = attendanceList)
    }

    override fun mapToResult(entityResult: Result<MealDataEntity>): Result<Meal> =
        entityResult.map { mapTo(entity = it) }
}