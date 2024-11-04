package com.efkan.shoppingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.efkan.shoppingapp.databinding.MealItemBinding
import com.efkan.shoppingapp.pojo.MealsByCategory
import com.squareup.picasso.Picasso

class CategoryMealsAdapter:RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewModel>(){
    private var mealsList=ArrayList<MealsByCategory>()
    fun setMealsList(mealsList:List<MealsByCategory>){
        this.mealsList=mealsList as ArrayList<MealsByCategory>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewModel {
     return CategoryMealsViewModel(
         MealItemBinding.inflate(LayoutInflater.from(parent.context))
     )
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: CategoryMealsViewModel, position: Int) {
        Picasso.get().load(mealsList[position].strMealThumb).into(holder.binding.imgMeal)
        holder.binding.tvMealName.text=mealsList[position].strMeal
    }

    class CategoryMealsViewModel(val binding: MealItemBinding):RecyclerView.ViewHolder(binding.root)
}