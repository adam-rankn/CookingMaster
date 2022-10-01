package com.rankin.adam.cookingmaster.model

import java.util.ArrayList

/**
 * Created by Adam on 15-Jul-18.
 */
class ShoppingList {
    var shoppingList: ArrayList<ShoppingListEntry> = ArrayList()

    fun addEntry(shoppingListEntry: ShoppingListEntry) {
        shoppingList.add(shoppingListEntry)
    }

    fun removeEntry(position: Int) {
        shoppingList.removeAt(position)
    }

    fun clearShoppingList() {
        shoppingList.clear()
    }

    fun findIngredient(ingredient: Ingredient): ShoppingListEntry? {
        //TODO optimize
        for (element in shoppingList) {
            if (element.ingredient.name == ingredient.name) {
                return element
            }
        }
        return null
    }

    fun increaseAmount(position: Int, amount: Int) {
        shoppingList[position].addAmount(amount)
    }
}