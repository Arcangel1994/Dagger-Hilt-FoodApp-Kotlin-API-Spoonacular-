package mx.com.gilsa.foody.data

import kotlinx.coroutines.flow.Flow
import mx.com.gilsa.foody.data.databse.RecipesDao
import mx.com.gilsa.foody.data.databse.entities.FavoritesEntity
import mx.com.gilsa.foody.data.databse.entities.RecipesEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val recipesDao: RecipesDao) {

    fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> {
        return recipesDao.readFavoriteRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) {
        recipesDao.insertFavoriteRecipes(favoritesEntity)
    }

    suspend fun deleteFavoriteRecipes(favoritesEntity: FavoritesEntity) {
        recipesDao.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipes()
    }

}