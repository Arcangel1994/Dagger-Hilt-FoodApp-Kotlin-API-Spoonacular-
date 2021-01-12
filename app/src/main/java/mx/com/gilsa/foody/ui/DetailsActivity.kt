package mx.com.gilsa.foody.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_details.*
import mx.com.gilsa.foody.R
import mx.com.gilsa.foody.adapters.PagerAdapter
import mx.com.gilsa.foody.data.databse.entities.FavoritesEntity
import mx.com.gilsa.foody.databinding.ActivityDetailsBinding
import mx.com.gilsa.foody.ui.fragments.ingredients.IngredientsFragment
import mx.com.gilsa.foody.ui.fragments.instructions.InstructionsFragment
import mx.com.gilsa.foody.ui.fragments.overview.OverviewFragment
import mx.com.gilsa.foody.util.Constants
import mx.com.gilsa.foody.util.Features
import mx.com.gilsa.foody.viewmodels.MainViewModel

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityDetailsBinding

    private var recipesSaved = false
    private var savedRecipeId = 0

    var features: Features? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        features = Features()

        setSupportActionBar(binding.toolbar)
        if(!features!!.checkIsNight(this@DetailsActivity)){
            binding.toolbar.setTitleTextColor(ContextCompat.getColor(this@DetailsActivity, R.color.white))
        }else{
            binding.toolbar.setTitleTextColor(ContextCompat.getColor(this@DetailsActivity, R.color.black))
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable(Constants.RECIPE_RESULT_KEY, args.result)

        //viewpager
        //val adapter = PagerAdapter(resultBundle, fragments, titles, supportFragmentManager)
        //binding.viewPager.adapter = adapter
        //binding.tabLayout.setupWithViewPager(binding.viewPager)

        val pagerAdapter = PagerAdapter(resultBundle, fragments, this)

        binding.viewPager.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = titles[position]
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        val menuItem = menu?.findItem(R.id.save_to_favorites_menu)
        checkSaveRecipes(menuItem)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            finish()
        } else if(item.itemId == R.id.save_to_favorites_menu && !recipesSaved){
            saveToFavorites(item)
        }else if(item.itemId == R.id.save_to_favorites_menu && recipesSaved){
            removeFromFavorites(item)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun checkSaveRecipes(menuItem: MenuItem?) {
        mainViewModel.readFavoriteRecipes.observe(this@DetailsActivity, { favoritesEntity ->
            try{
                for(savedRecipe in favoritesEntity){
                    if(savedRecipe.result.recipeId == args.result.recipeId){
                        changeMenuItemColor(menuItem!!, R.color.yellow)
                        savedRecipeId = savedRecipe.id
                        recipesSaved = true
                    }else{
                        changeMenuItemColor(menuItem!!, R.color.white)
                        recipesSaved = false
                    }
                }
            }catch (e: Exception){
                Log.d("DetailsActivity", e.message.toString())
            }
        })
    }

    private fun saveToFavorites(item: MenuItem) {
        val favoritesEntity = FavoritesEntity(0, args.result)
        mainViewModel.insertFavoriteRecipes(favoritesEntity)
        changeMenuItemColor(item, R.color.yellow)
        showSnackBar("Recipe saved.")
        recipesSaved = true
    }

    private fun removeFromFavorites(item: MenuItem) {
        val favoritesEntity = FavoritesEntity(savedRecipeId, args.result)
        mainViewModel.deleteFavoriteRecipes(favoritesEntity)
        changeMenuItemColor(item, R.color.white)
        showSnackBar("Removed from favorites.")
        recipesSaved = false
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(detailsLayout, message,Snackbar.LENGTH_LONG).setAction("Okay"){

        }.show()
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this, color))
    }

}