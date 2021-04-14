package com.nindikiranaf.poppinsapp.ui.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nindikiranaf.poppinsapp.data.model.ActionState
import com.nindikiranaf.poppinsapp.data.model.Recipe
import com.nindikiranaf.poppinsapp.data.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val repo: RecipeRepository by lazy { RecipeRepository()}

    val loading = MutableLiveData(false)
    val actionState = MutableLiveData<ActionState<*>>()

    val listResp = MutableLiveData<List<Recipe>>()
    val categoryResp = MutableLiveData<Recipe>()
    val searchResp = MutableLiveData<List<Recipe>>()

    val category = MutableLiveData("")
    val query = MutableLiveData("")

    fun listRecipe(){
        viewModelScope.launch {
            loading.value = true
            val resp = repo.listRecipe()
            actionState.value = resp
            listResp.value = resp.data
            loading.value = false
        }
    }

    fun categoryRecipe(category: String? = this.category.value){
        category?.let {
            viewModelScope.launch {
                loading.value = true
                val resp = repo.categoryRecipe(it)
                actionState.value = resp
                categoryResp.value = resp.data
                loading.value = false
            }
        }
    }

    fun searchRecipe(query: String? = this.query.value){
        query?.let {
            viewModelScope.launch {
                loading.value = true
                val resp = repo.searchRecipe(it)
                actionState.value = resp
                searchResp.value = resp.data
                loading.value = false
            }
        }
    }
}