package com.rankin.adam.cookingmaster.model

/**
 * Created by Adam on 26-Jun-18.
 */
class Recipe(var name: String) {
    var time: Int? = null
    var ingredientList: ArrayList<RecipeIngredientEntry>? = ArrayList()
    var instructions: String? = null
    var allergens: ArrayList<String>? = ArrayList()
    var imageUri: String? = null
    var rating = 0f

    fun addIngredient(recipeIngredientEntry: RecipeIngredientEntry) {
        ingredientList?.add(recipeIngredientEntry)
    }

    fun removeIngredient(position: Int) {
        ingredientList?.removeAt(position)
    }

    fun containsIngredient(ingredient: Ingredient): Boolean {
        for (entry in ingredientList!!) {
            if (ingredient.name == entry.ingredient.name) {
                return java.lang.Boolean.TRUE
            }
        }
        return java.lang.Boolean.FALSE
    }
}