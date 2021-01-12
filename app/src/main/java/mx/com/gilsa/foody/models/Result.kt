package mx.com.gilsa.foody.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Result(
    @SerializedName("aggregateLikes")
    var aggregateLikes: Int?,
    @SerializedName("cheap")
    var cheap: Boolean?,
    @SerializedName("dairyFree")
    var dairyFree: Boolean?,
    @SerializedName("extendedIngredients")
    var extendedIngredients: @RawValue List<ExtendedIngredient>?,
    @SerializedName("glutenFree")
    var glutenFree: Boolean?,
    @SerializedName("id")
    var recipeId: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("readyInMinutes")
    var readyInMinutes: Int?,
    @SerializedName("sourceName")
    var sourceName: String?,
    @SerializedName("sourceUrl")
    var sourceUrl: String?,
    @SerializedName("spoonacularSourceUrl")
    var spoonacularSourceUrl: String?,
    @SerializedName("summary")
    var summary: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("vegan")
    var vegan: Boolean?,
    @SerializedName("vegetarian")
    var vegetarian: Boolean?,
    @SerializedName("veryHealthy")
    var veryHealthy: Boolean?
): Parcelable