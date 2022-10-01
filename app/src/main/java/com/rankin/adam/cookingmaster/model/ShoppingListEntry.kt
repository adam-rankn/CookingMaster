package com.rankin.adam.cookingmaster.model


/**
 * Created by Adam on 16-Jul-18.
 */
class ShoppingListEntry {
    var ingredient: Ingredient
    var amount: Int
    var unit: String

    constructor(ingredient: Ingredient, amount: Int, unit: String) {
        this.ingredient = ingredient
        this.amount = amount
        this.unit = unit
    }

    constructor(ingredient: Ingredient) {
        this.ingredient = ingredient
        amount = 0
        unit = "lb"
    }

    fun addAmount(amount: Int) {
        this.amount += amount
    }

    val name: String
        get() = ingredient.name
}