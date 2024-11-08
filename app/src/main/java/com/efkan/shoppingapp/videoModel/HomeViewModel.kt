package com.efkan.shoppingapp.videoModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.efkan.shoppingapp.databinding.CategoryItemBinding
import com.efkan.shoppingapp.pojo.Category
import com.efkan.shoppingapp.pojo.CategoryList
import com.efkan.shoppingapp.pojo.MealsByCategoryList
import com.efkan.shoppingapp.pojo.MealsByCategory
import com.efkan.shoppingapp.pojo.Meal
import com.efkan.shoppingapp.pojo.MealList
import com.efkan.shoppingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private  var randomMealLiveData:MutableLiveData<Meal>
    init {
        randomMealLiveData = MutableLiveData()
    }
    private var popularItemsLiveData=MutableLiveData<List<MealsByCategory>>()
    private var categoriesLiveData=MutableLiveData<List<Category>>()
    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object :retrofit2.Callback<MealList>{
            override fun onResponse(call: retrofit2.Call<MealList>, response: Response<MealList>) {
                if (response.isSuccessful) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value=randomMeal

                } else {
                    Log.d("HomeFragment", "Veri boş veya hata: ${response.errorBody()?.string()}")
                }
            }
            override fun onFailure(call: retrofit2.Call<MealList>, t: Throwable) {
                Log.e("HomeFragment", "Veri çekme hatası: ${t.message}")
            }

        })
    }
    fun observeRandomMealLivedata():LiveData<Meal>{
        return randomMealLiveData
    }
    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<MealsByCategoryList>{
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {
                if(response.isSuccessful){
                    popularItemsLiveData.value= response.body()!!.meals
                }
                else{
                    Log.d("HomeFragment", "Veri boş veya hata: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d("HomeFragment", "Veri çekme hatası : ${t.message}")
            }

        })
    }
    fun getCategories() {
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let { categoryList ->
                    categoriesLiveData.postValue(categoryList.categories)
                } ?: run {
                    Log.d("HomeFragment", "Veri boş veya hata: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeFragment", "Veri çekme hatası: ${t.message}")
            }
        })
    }


    fun observePopularItemsLiveData():LiveData<List<MealsByCategory>>{
    return popularItemsLiveData
    }
    fun observeCategoriesLiveData():LiveData<List<Category>>{
        return categoriesLiveData
    }
}