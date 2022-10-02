package com.rankin.adam.cookingmaster.model

/**
 * Created by Adam on 18-Jul-18.
 */
class RecipeIngredientEntry {
    var ingredient: Ingredient
    var amount: Float? = null
    var unit: String? = null

    constructor(ingredient: Ingredient) {
        this.ingredient = ingredient
    }

    constructor(ingredient: Ingredient, amount: Float?, unit: String?) {
        this.ingredient = ingredient
        this.amount = amount
        this.unit = unit
    }

    val ingredientName: String
        get() = ingredient.name
}