package mx.com.gilsa.foody.models

import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("results")
    var results: List<Result>?
)