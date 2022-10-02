package com.rankin.adam.cookingmaster.model

/**
 * Created by Adam on 14-Jul-18.
 */
class RecipeList {

    var recipeList: ArrayList<Recipe> = ArrayList()


    fun addRecipe(recipe: Recipe) {
        recipeList.add(recipe)
    }

    fun editRecipe(position: Int, recipe: Recipe) {
        recipeList[position] = recipe
    }

    fun deleteRecipe(recipe: Recipe) {
        recipeList.remove(recipe)
    }

    fun getRecipe(position: Int): Recipe {
        return recipeList[position]
    }

    val size: Int
        get() = recipeList.size
}