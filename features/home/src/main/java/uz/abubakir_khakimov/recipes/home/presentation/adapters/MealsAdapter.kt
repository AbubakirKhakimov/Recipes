package uz.abubakir_khakimov.recipes.home.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.abubakir_khakimov.recipes.home.databinding.MealsItemLayoutBinding
import uz.abubakir_khakimov.recipes.home.domain.models.Meal
import uz.abubakir_khakimov.recipes.presentation.extensions.px
import uz.abubakir_khakimov.recipes.resource.R as ResModuleR

typealias Position = Int

class MealsAdapter(
    private val context: Context,
    private val callBack: CallBack
) : ListAdapter<Meal, MealsAdapter.ItemHolder>(MealDiffItemCallback()) {

    private val favouriteMeals = mutableListOf<Position>()

    interface CallBack{

        fun isFavouriteMealExist(id: String, position: Int)

        fun addToFavourite(meal: Meal)

        fun deleteFromFavourite(id: String)
    }

    inner class ItemHolder(
        private val binding: MealsItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(meal: Meal, position: Int) {
            binding.root.layoutParams = getParams(position = position)

            Glide.with(/* context = */ context)
                .load(/* string = */ "${meal.strMealThumb}/preview")
                .into(/* view = */ binding.image)

            binding.name.text = meal.strMeal
            binding.area.text = meal.strArea
            binding.tags.text = meal.strTags

            favouriteMeals.contains(element = position).also { isFavouriteMeal ->
                if (!isFavouriteMeal) callBack.isFavouriteMealExist(
                    id = meal.idMeal ?: return@also, position = position
                )

                binding.addOrDeleteFromFavourite.setImageResource(
                    /* resId = */ if (isFavouriteMeal) ResModuleR.drawable.ic_heart_filled
                    else ResModuleR.drawable.ic_heart_outlined
                )
                binding.addOrDeleteFromFavourite.tag = isFavouriteMeal
            }

            binding.addOrDeleteFromFavourite.setOnClickListener {
                if (binding.addOrDeleteFromFavourite.tag as Boolean) {
                    callBack.deleteFromFavourite(id = meal.idMeal ?: return@setOnClickListener)
                    favouriteMeals.remove(element = position)
                    binding.addOrDeleteFromFavourite.setImageResource(
                        /* resId = */ ResModuleR.drawable.ic_heart_outlined
                    )
                    binding.addOrDeleteFromFavourite.tag = false
                } else {
                    callBack.addToFavourite(meal = meal)
                    favouriteMeals.add(element = position)
                    binding.addOrDeleteFromFavourite.setImageResource(
                        /* resId = */ ResModuleR.drawable.ic_heart_filled
                    )
                    binding.addOrDeleteFromFavourite.tag = true
                }
            }
        }

        private fun getParams(position: Int): ViewGroup.LayoutParams = LinearLayout
            .LayoutParams(
                /* width = */ LinearLayout.LayoutParams.MATCH_PARENT,
                /* height = */ LinearLayout.LayoutParams.WRAP_CONTENT
            ).also { params ->
                if (position == 0) {
                    params.marginStart = 4.px
                } else if (position == itemCount - 1) {
                    params.marginEnd = 4.px
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder(
            MealsItemLayoutBinding.inflate(
                /* inflater = */ LayoutInflater.from(/* context = */ parent.context),
                /* parent = */parent,
                /* attachToParent = */false
            )
        )

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setItem(
            meal = getItem(/* position = */ position)!!,
            position = position
        )
    }

    fun addFavouriteMeal(position: Int) {
        favouriteMeals.add(element = position)
        notifyItemChanged(/* position = */ position)
    }
}

class MealDiffItemCallback : DiffUtil.ItemCallback<Meal>() {

    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem.idMeal == newItem.idMeal
}