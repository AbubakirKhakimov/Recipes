package uz.abubakir_khakimov.recipes.home.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.elevation.SurfaceColors
import uz.abubakir_khakimov.recipes.home.databinding.CategoriesItemLayoutBinding
import uz.abubakir_khakimov.recipes.home.domain.models.Category
import uz.abubakir_khakimov.recipes.presentation.extensions.getColorCompat
import uz.abubakir_khakimov.recipes.presentation.extensions.px
import uz.abubakir_khakimov.recipes.resource.R as ResModuleR

class CategoriesAdapter(
    private val context: Context,
    private val callBack: CallBack
) : ListAdapter<Category, CategoriesAdapter.ItemHolder>(CategoryDiffItemCallback()) {

    private var selectedPosition: Int? = null

    interface CallBack{

        fun categorySelectedListener(category: String, position: Int)
    }

    inner class ItemHolder(
        private val binding: CategoriesItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setItem(category: Category, position: Int) {
            binding.root.layoutParams = getParams(position = position)

            Glide.with(/* context = */ context)
                .load(/* string = */ category.strCategoryThumb)
                .override(50, 50)
                .into(/* view = */ binding.image)

            binding.name.text = category.strCategory
            changeRootCardBackgroundColorByPosition(position = position)

            binding.rootCard.setOnClickListener {
                callBack.categorySelectedListener(
                    category = category.strCategory ?: return@setOnClickListener,
                    position = position
                )
            }
        }

        private fun changeRootCardBackgroundColorByPosition(position: Int){
            binding.rootCard.setCardBackgroundColor(
                /* color = */ if (selectedPosition == position)
                    context.getColorCompat(colorRes = ResModuleR.color.colorPrimary)
                else SurfaceColors.SURFACE_0.getColor(/* context = */ context)
            )
        }

        private fun getParams(position: Int): ViewGroup.LayoutParams = LinearLayout
            .LayoutParams(
                /* width = */ LinearLayout.LayoutParams.WRAP_CONTENT,
                /* height = */ LinearLayout.LayoutParams.WRAP_CONTENT
            ).also { params ->
                if (position == 0 || position == 1) {
                    params.marginStart = 12.px
                } else if (position == itemCount - 1) {
                    params.marginEnd = 12.px
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder(
            CategoriesItemLayoutBinding.inflate(
                /* inflater = */ LayoutInflater.from(/* context = */ parent.context),
                /* parent = */ parent,
                /* attachToParent = */ false
            )
        )

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setItem(
            category = getItem(/* position = */ position)!!,
            position = position
        )
    }

    fun changeSelectedPosition(position: Int?, notifyNewPosition: Boolean = true){
        val oldSelectedPosition = selectedPosition
        selectedPosition = position
        if (oldSelectedPosition != null) notifyItemChanged(/* position = */ oldSelectedPosition)
        if (position != null && notifyNewPosition) notifyItemChanged(/* position = */ position)
    }
}

class CategoryDiffItemCallback : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem.idCategory == newItem.idCategory
}