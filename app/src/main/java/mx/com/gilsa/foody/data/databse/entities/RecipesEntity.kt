package mx.com.gilsa.foody.data.databse.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.com.gilsa.foody.models.FoodRecipe
import mx.com.gilsa.foody.util.Constants

@Entity(tableName = Constants.RECIPES_TABLE)
class RecipesEntity(

        var foodRecipe: FoodRecipe

) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}