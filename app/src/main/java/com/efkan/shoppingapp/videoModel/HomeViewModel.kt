package com.efkan.shoppingapp.videoModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.efkan.shoppingapp.pojo.Meal
import com.efkan.shoppingapp.pojo.MealList
import com.efkan.shoppingapp.retrofit.RetrofitInstance
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private  var randomMealLiveData:MutableLiveData<Meal>
    init {
        randomMealLiveData = MutableLiveData()
    }
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
}