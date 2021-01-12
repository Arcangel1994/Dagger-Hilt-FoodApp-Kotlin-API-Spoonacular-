package mx.com.gilsa.foody.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.ingredients_row_layout.view.*
import mx.com.gilsa.foody.R
import mx.com.gilsa.foody.models.ExtendedIngredient
import mx.com.gilsa.foody.util.Constants
import mx.com.gilsa.foody.util.RecipesDiffUtil

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ingredients_row_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.ingredient_imageView.load(Constants.BASE_IMAGE_URL + ingredientsList[position].image){
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
        holder.itemView.ingredient_name_textView.text = ingredientsList[position].name?.capitalize() ?: ""
        holder.itemView.ingredient_amount_textView.text = ingredientsList[position].amount?.toString()
        holder.itemView.ingredient_unit_textView.text = ingredientsList[position].unit?.toString()
        holder.itemView.ingredient_consistency_textView.text = ingredientsList[position].consistency?.toString()
        holder.itemView.ingredient_original_textView.text = ingredientsList[position].original?.toString()
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    fun setData(newIngredients: List<ExtendedIngredient>){
        val ingredientsDiffUtil = RecipesDiffUtil(ingredientsList, newIngredients)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientsList = newIngredients
        diffUtilResult.dispatchUpdatesTo(this)
    }

}