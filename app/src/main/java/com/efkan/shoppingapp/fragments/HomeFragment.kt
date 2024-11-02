package com.efkan.shoppingapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.efkan.shoppingapp.activites.MealActivity
import com.efkan.shoppingapp.databinding.FragmentHomeBinding
import com.efkan.shoppingapp.pojo.Meal
import com.efkan.shoppingapp.videoModel.HomeViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private var randomMeal: Meal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]
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
        homeMvvm.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()
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