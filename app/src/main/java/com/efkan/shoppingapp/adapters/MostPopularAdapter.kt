package com.efkan.shoppingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.efkan.shoppingapp.databinding.PopularItemsBinding
import com.efkan.shoppingapp.pojo.CategoryMeals
import com.squareup.picasso.Picasso

class MostPopularAdapter():RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {
    lateinit var onItemClick:((CategoryMeals) ->Unit)
    private var mealsList = ArrayList<CategoryMeals>()

    fun setMeals(mealsList:ArrayList<CategoryMeals>){
        this.mealsList=mealsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Picasso.get().load(mealsList[position].strMealThumb).into(holder.binding.imgPopularMealItem)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealsList[position])
        }
    }


    class PopularMealViewHolder(val binding:PopularItemsBinding):RecyclerView.ViewHolder(binding.root)
}