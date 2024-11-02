package com.efkan.shoppingapp.retrofit

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api:MealApi by lazy{ //by lazy bellekte yer kaplamamasını sağlar ve yalnızca ilk erişildiğinde başlatılır.
    Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MealApi::class.java)
    }
}