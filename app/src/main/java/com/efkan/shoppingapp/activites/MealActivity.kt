package com.efkan.shoppingapp.activites

import android.adservices.adid.AdId
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.efkan.shoppingapp.R
import com.efkan.shoppingapp.databinding.ActivityMealBinding
import com.efkan.shoppingapp.fragments.HomeFragment
import com.efkan.shoppingapp.pojo.Meal
import com.efkan.shoppingapp.videoModel.MealViewModel
import com.squareup.picasso.Picasso

class MealActivity : AppCompatActivity() {
    private lateinit var mealId: String
    private lateinit var mealName:String
    private lateinit var mealThumb:String
    private lateinit var binding:ActivityMealBinding
    private lateinit var youtubeLink:String
    private lateinit var mealMvvm:MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealMvvm = ViewModelProvider(this).get(MealViewModel::class.java)
        binding=ActivityMealBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        getMealInformationFromIntent()
        setInformationInViews()

        mealMvvm.getMailDetail(mealId)
        observerMealDetailsLiveData()

        onYoutubeImageClick()
    }

    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener{
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailsLiveData() {
        mealMvvm.observerMealDetailsLiveData().observe(this,object : Observer<Meal>{
            override fun onChanged(value: Meal) {
                onResponseCase()
                binding.tvCategory.text="Category : ${value!!.strCategory}"
                binding.tvArea.text="Area : ${value.strArea}"
                binding.tvInstructionsSt.text=value.strInstructions
                youtubeLink=value.strYoutube
            }

        })
    }

    private fun setInformationInViews() {
        Picasso.get().load(mealThumb).into(binding.imgMealDetail)
        binding.collapsingToolbar.title=mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent=intent
        mealId= intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealThumb=intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
        mealName=intent.getStringExtra(HomeFragment.MEAL_NAME)!!

    }
    private fun loadingCase(){
        binding.linearprogress.isVisible=true
        binding.btnAddToFav.isInvisible=true
        binding.tvInstructions.isInvisible=true
        binding.tvCategory.isInvisible=true
        binding.tvArea.isInvisible=true
        binding.imgYoutube.isInvisible=true
    }
    private fun onResponseCase(){
        binding.linearprogress.isInvisible=true
        binding.btnAddToFav.isVisible=true
        binding.tvInstructions.isVisible=true
        binding.tvCategory.isVisible=true
        binding.tvArea.isVisible=true
        binding.imgYoutube.isVisible=true
    }
}