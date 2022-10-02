package com.rankin.adam.cookingmaster.model

/**
 * Created by Adam on 26-Jun-18.
 */
@Suppress("EqualsOrHashCode")
class Ingredient(var name: String) {
    private val allergens: ArrayList<String>? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as Ingredient
        return name == that.name
    }
}