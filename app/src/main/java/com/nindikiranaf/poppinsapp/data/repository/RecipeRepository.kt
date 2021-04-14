package com.nindikiranaf.poppinsapp.data.repository

import com.nindikiranaf.poppinsapp.data.model.ActionState
import com.nindikiranaf.poppinsapp.data.model.Recipe
import com.nindikiranaf.poppinsapp.data.remote.RecipeService
import com.nindikiranaf.poppinsapp.data.remote.RetrofitApi
import retrofit2.await

class RecipeRepository {
    private val recipeService: RecipeService by lazy{
        RetrofitApi.recipeService()
    }

    suspend fun listRecipe() : ActionState<List<Recipe>>{
        return try {
            val list = recipeService.listRecipe().await()
            ActionState(list.results)
        }catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun categoryRecipe(category: String) : ActionState<Recipe>{
        return try {
            val list = recipeService.detailRecipe(category ).await()
            ActionState(list.results.first())
        }catch (e:Exception){
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun searchRecipe(query: String) : ActionState<List<Recipe>>{
        return try{
            val list = recipeService.searchRecipe(query).await()
            ActionState(list.results)
        }catch (e:Exception){
            ActionState(message = e.message,isSuccess = false)
        }
    }
}