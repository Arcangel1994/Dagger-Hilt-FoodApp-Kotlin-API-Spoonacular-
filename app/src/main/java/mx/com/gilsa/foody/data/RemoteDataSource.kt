package mx.com.gilsa.foody.data

import mx.com.gilsa.foody.data.network.FoodRecipesApi
import mx.com.gilsa.foody.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val foodRecipesApi: FoodRecipesApi) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe?> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe?> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

}