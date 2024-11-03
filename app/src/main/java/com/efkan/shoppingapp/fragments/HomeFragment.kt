package com.efkan.shoppingapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.efkan.shoppingapp.activites.MealActivity
import com.efkan.shoppingapp.adapters.MostPopularAdapter
import com.efkan.shoppingapp.databinding.FragmentHomeBinding
import com.efkan.shoppingapp.pojo.CategoryMeals
import com.efkan.shoppingapp.pojo.Meal
import com.efkan.shoppingapp.pojo.MealList
import com.efkan.shoppingapp.videoModel.HomeViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private var randomMeal: Meal? = null
    private lateinit var popularItemsAdapter:MostPopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]
        popularItemsAdapter= MostPopularAdapter()
    }
    companion object{
        const val MEAL_ID="com.efkan.shoppingapp.fragments.idMeal"
        const val MEAL_NAME="com.efkan.shoppingapp.fragments.nameMeal"
        const val MEAL_THUMB="com.efkan.shoppingapp.fragments.thumbMeal"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePopularItemsRecycler()
        homeMvvm.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()

        homeMvvm.getPopularItems()
        observePopularItemsLiveData()
        onPopularItemClick()

    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick={ meal->
           val intent=Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun preparePopularItemsRecycler() {
        binding.recViewMealsPopular.apply {
            layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter=popularItemsAdapter
        }
    }

    private fun observePopularItemsLiveData() {
        homeMvvm.observePopularItemsLiveData().observe(viewLifecycleOwner
        ) { mealsList -> popularItemsAdapter.setMeals(mealsList = mealsList as ArrayList<CategoryMeals>) }
    }

    private fun onRandomMealClick() {

        binding.imgRandomMealCard.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal?.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal?.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal?.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        homeMvvm.observeRandomMealLivedata().observe(viewLifecycleOwner, object : Observer<Meal> {
            override fun onChanged(value: Meal) {
                Picasso.get().load(value.strMealThumb).into(binding.imgRandomMeal)
                this@HomeFragment.randomMeal=value
            }
        })
    }

}