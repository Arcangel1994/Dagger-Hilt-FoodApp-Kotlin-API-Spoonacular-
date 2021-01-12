package mx.com.gilsa.foody.data.databse.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.com.gilsa.foody.models.Result
import mx.com.gilsa.foody.util.Constants

@Entity(tableName = Constants.FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var result: Result
)