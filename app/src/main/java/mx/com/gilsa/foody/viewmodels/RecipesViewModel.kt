package mx.com.gilsa.foody.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.com.gilsa.foody.data.DataStoreRespository
import mx.com.gilsa.foody.util.Constants

class RecipesViewModel @ViewModelInject constructor(application: Application, private val dataStoreRespository: DataStoreRespository): AndroidViewModel(application) {

    private var mealType = Constants.DEFAULT_MEAL_TYPE
    private var dietType = Constants.DEFAULT_DIET_TYPE


    var networkStatus = false
    var backOnline = false

    val readMealAndDietType = dataStoreRespository.readMealAndDietType
    val readBackOnline = dataStoreRespository.readBackOnline.asLiveData()

    fun saveMealAndDietType(mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int) =
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreRespository.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
            }

    fun saveBackOnline(backonline: Boolean) =
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreRespository.saveBackOnline(backonline)
            }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }

        queries[Constants.QUERY_NUMBER] = Constants.DEFAULT_RECIPES_NUMBER
        queries[Constants.QUERY_API_KEY] = Constants.API_KEY
        queries[Constants.QUERY_TYPE] = mealType
        queries[Constants.QUERY_DIET] = dietType
        queries[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[Constants.QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

    fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[Constants.QUERY_SEARCH] = searchQuery
        queries[Constants.QUERY_NUMBER] = Constants.DEFAULT_RECIPES_NUMBER
        queries[Constants.QUERY_API_KEY] = Constants.API_KEY
        queries[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[Constants.QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

    fun showNetworkStatus(){
        if(!networkStatus){
            Toast.makeText(getApplication(), "No Internet Connection.", Toast.LENGTH_LONG).show()
            saveBackOnline(true)
        }else if(networkStatus){
           if(backOnline){
               Toast.makeText(getApplication(), "We're back online.", Toast.LENGTH_LONG).show()
               saveBackOnline(false)
           }
        }
    }

}