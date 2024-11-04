package com.efkan.shoppingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.efkan.shoppingapp.databinding.CategoryItemBinding
import com.efkan.shoppingapp.databinding.PopularItemsBinding
import com.efkan.shoppingapp.pojo.Category
import com.efkan.shoppingapp.pojo.CategoryList
import com.squareup.picasso.Picasso

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    private var categoriesList=ArrayList<Category>()
    var onItemClick: ((Category)->Unit)?= null

    fun setCategoryList(categoriesList:List<Category>){
        this.categoriesList=categoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
      return CategoryViewHolder(
          CategoryItemBinding.inflate(LayoutInflater.from(parent.context))
      )
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Picasso.get().load(categoriesList[position].strCategoryThumb).into(holder.binding.imgCategory)
        holder.binding.tvCategoryName.text=categoriesList[position].strCategory
        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(categoriesList[position])
        }
    }
    inner class CategoryViewHolder(val binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root)
}