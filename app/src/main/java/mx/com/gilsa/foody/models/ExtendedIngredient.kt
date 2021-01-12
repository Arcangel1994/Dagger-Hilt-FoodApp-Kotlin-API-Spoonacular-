package mx.com.gilsa.foody.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExtendedIngredient(
    @SerializedName("amount")
    var amount: Double?,
    @SerializedName("consistency")
    var consistency: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("original")
    var original: String?,
    @SerializedName("unit")
    var unit: String?
): Parcelable