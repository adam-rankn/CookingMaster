package com.rankin.adam.cookingmaster.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.rankin.adam.cookingmaster.R
import com.rankin.adam.cookingmaster.activity.MainActivity
import android.content.Intent
import android.net.Uri
import com.rankin.adam.cookingmaster.activity.ViewRecipeActivity
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.rankin.adam.cookingmaster.model.Recipe
import java.lang.NullPointerException
import java.util.ArrayList

class RecipeBookLayoutAdapter(recipeList: ArrayList<Recipe>?, context: Context) :
    RecyclerView.Adapter<RecipeBookLayoutAdapter.ViewHolder>() {
    private var recipeList: ArrayList<Recipe>
    private val recipeBookContext: Context

    init {
        this.recipeList = ArrayList()
        this.recipeList.addAll(recipeList!!)
        recipeBookContext = context
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val recipeName: TextView
        val recipeImage: ImageView

        init {
            itemView.setOnClickListener(this)
            recipeName = itemView.findViewById(R.id.recipe_row_name)
            recipeImage = itemView.findViewById(R.id.recipe_book_row_image)
        }

        override fun onClick(view: View) {
            val recipe = recipeList[adapterPosition]
            MainActivity.recipeController.currentRecipe = recipe
            val viewRecipeIntent = Intent(recipeBookContext, ViewRecipeActivity::class.java)
            recipeBookContext.startActivity(viewRecipeIntent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_book_row_layout, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeList[position]
        val name = recipe.name
        holder.recipeName.text = name
        try {
            val recipeUriString = MainActivity.recipeController.recipesList[position].imageUri
            val uri = Uri.parse(recipeUriString)
            holder.recipeImage.setImageURI(uri)
        } catch (exception: NullPointerException) {
            //no image to display
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    fun addRecipe(recipe: Recipe) {
        recipeList.add(recipe)
    }

    fun removeIngredient(position: Int) {
        recipeList.removeAt(position)
    }

    fun setRecipeList(recipeList: ArrayList<Recipe>) {
        this.recipeList = recipeList
    }
}