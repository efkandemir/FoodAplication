package com.efkan.shoppingapp.activites

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.efkan.shoppingapp.R
import com.efkan.shoppingapp.adapters.CategoryMealsAdapter
import com.efkan.shoppingapp.databinding.ActivityCategoryMealsBinding
import com.efkan.shoppingapp.fragments.HomeFragment
import com.efkan.shoppingapp.videoModel.CategoryMealsViewModel
import com.efkan.shoppingapp.videoModel.MealViewModel

class CategoryMealsActivity : AppCompatActivity() {
     lateinit var binding:ActivityCategoryMealsBinding
     lateinit var categoryMealsViewModel: CategoryMealsViewModel
     lateinit var categoryMealsAdapter:CategoryMealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityCategoryMealsBinding.inflate(layoutInflater)
        val view=binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)
        prepareRecyclerView()

        categoryMealsViewModel= ViewModelProvider(this).get(CategoryMealsViewModel::class.java)
        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)
        categoryMealsViewModel.observeMealsLiveData().observe(this,Observer{mealsList->
        mealsList.forEach {
            binding.tvCategoryCount.text=mealsList.size.toString()
            categoryMealsAdapter.setMealsList(mealsList)
        }
        })
    }

    private fun prepareRecyclerView() {
    categoryMealsAdapter= CategoryMealsAdapter()
        binding.rvMeals.apply {
            layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter=categoryMealsAdapter
        }
    }
}