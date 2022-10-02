package com.rankin.adam.cookingmaster.controller

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rankin.adam.cookingmaster.activity.MainActivity
import com.rankin.adam.cookingmaster.model.Recipe
import com.rankin.adam.cookingmaster.model.ShoppingListEntry
import java.io.*
import java.lang.RuntimeException
import java.util.ArrayList

/**
 * Created by Adam on 17-Jul-18.
 */
class SaveLoadController(private val context: Context) {
    fun loadRecipesFromFile() {
        try {
            val fis = context.openFileInput("Recipes")
            val `in` = BufferedReader(InputStreamReader(fis))
            val gson = Gson()
            val listType = object : TypeToken<ArrayList<Recipe?>?>() {}.type
            val recipeLoadList = gson.fromJson<ArrayList<Recipe>>(`in`, listType)
            MainActivity.recipeList.recipeList = recipeLoadList
        } catch (e: FileNotFoundException) {
            Log.e("loadError", "recipes failed to load")
            //throw new RuntimeException();
        }
    }

    fun saveRecipesToFile() {
        try {
            val fos = context.openFileOutput(
                "Recipes",
                Context.MODE_PRIVATE
            )
            val writer = OutputStreamWriter(fos)
            val gson = Gson()
            gson.toJson(MainActivity.recipeList.recipeList, writer)
            writer.flush()
            fos.close()
        } catch (e: IOException) {
            Log.e("SaveError", "recipes failed to save")
            throw RuntimeException()
        }
    }

    fun loadShoppingListFromFile() {
        try {
            val fis = context.openFileInput("Shopping List")
            val `in` = BufferedReader(InputStreamReader(fis))
            val gson = Gson()
            val listType = object : TypeToken<ArrayList<ShoppingListEntry?>?>() {}.type
            val shoppingLoadList = gson.fromJson<ArrayList<ShoppingListEntry>>(`in`, listType)
            MainActivity.shoppingList.shoppingList = shoppingLoadList
        } catch (e: FileNotFoundException) {
            Log.e("SaveError", "shopping list failed to save")
            //throw new RuntimeException();
        }
    }

    fun saveShoppingListToFile() {
        try {
            val fos = context.openFileOutput(
                "Shopping List",
                Context.MODE_PRIVATE
            )
            val writer = OutputStreamWriter(fos)
            val gson = Gson()
            gson.toJson(MainActivity.shoppingList.shoppingList, writer)
            writer.flush()
            fos.close()
        } catch (e: IOException) {
            Log.e("loadError", "shoppingList failed to load")
            throw RuntimeException()
        }
    }
}