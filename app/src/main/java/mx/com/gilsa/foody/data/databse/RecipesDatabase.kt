package mx.com.gilsa.foody.data.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mx.com.gilsa.foody.data.databse.entities.FavoritesEntity
import mx.com.gilsa.foody.data.databse.entities.RecipesEntity

@Database(entities = [RecipesEntity::class, FavoritesEntity::class], version = 2, exportSchema = false)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}