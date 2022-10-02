package com.rankin.adam.cookingmaster.activity

import androidx.appcompat.app.AppCompatActivity
import com.rankin.adam.cookingmaster.adapter.RecipeBookLayoutAdapter
import com.rankin.adam.cookingmaster.controller.SaveLoadController
import android.os.Bundle
import com.rankin.adam.cookingmaster.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
import android.os.Handler
import com.rankin.adam.cookingmaster.dialog.SearchRecipesDialog
import androidx.core.app.NavUtils
import com.rankin.adam.cookingmaster.model.Ingredient
import android.os.Looper
import android.view.MenuItem
import android.widget.Button
import com.rankin.adam.cookingmaster.model.Recipe
import java.util.*

class RecipeBookActivity : AppCompatActivity() {
    var recipeBookAdapter: RecipeBookLayoutAdapter? = null
    private val addRecipeRequest = 0
    private val adapterList = ArrayList<Recipe>()
    private val saveLoadController = SaveLoadController(this@RecipeBookActivity)
    private var isFiltered = java.lang.Boolean.FALSE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_book)
        adapterList.addAll(MainActivity.recipeList.recipeList)
        val recipeBookRecyclerView = findViewById<RecyclerView>(R.id.recycler_recipe_book)
        val recipeBookLinearLayoutManager = LinearLayoutManager(this)
        recipeBookRecyclerView.layoutManager = recipeBookLinearLayoutManager
        recipeBookAdapter = RecipeBookLayoutAdapter(adapterList, this)
        recipeBookRecyclerView.adapter = recipeBookAdapter
        val returnToMainButton = findViewById<Button>(R.id.btn_main)
        returnToMainButton.setOnClickListener { finish() }
        val addRecipeButton = findViewById<Button>(R.id.btn_add_recipe)
        addRecipeButton.setOnClickListener {
            val addRecipeIntent = Intent(this@RecipeBookActivity, AddRecipeActivity::class.java)
            startActivityForResult(addRecipeIntent, addRecipeRequest)
        }
        val searchRecipesButton = findViewById<Button>(R.id.recipeBookAct_btn_search)
        searchRecipesButton.setOnClickListener {
            val searchDialog = SearchRecipesDialog(this@RecipeBookActivity)
            searchDialog.show()
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == addRecipeRequest) {
            recreate()
        }
    }

    public override fun onPause() {
        super.onPause()
        saveLoadController.saveRecipesToFile()
    }

    override fun onRestart() {
        super.onRestart()
        invalidateOptionsMenu()
        if (MainActivity.recipeController.deletedFlag) {
            recreate()
            MainActivity.recipeController.deletedFlag = java.lang.Boolean.FALSE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun filterRecipeList(
        maxTime: Int,
        ingredients: ArrayList<Ingredient?>,
        allergens: ArrayList<String?>
    ) {
        val recipes = MainActivity.recipeController.recipesList
        val filteredList = ArrayList<Recipe>()
        var filterAllergens = java.lang.Boolean.FALSE
        var filterIngredients = java.lang.Boolean.FALSE
        var filterTime = java.lang.Boolean.FALSE
        var addRecipe: Boolean
        if (allergens.isNotEmpty()) {
            filterAllergens = java.lang.Boolean.TRUE
        }
        if (ingredients.isNotEmpty()) {
            filterIngredients = java.lang.Boolean.TRUE
        }
        if (maxTime > 0) {
            filterTime = java.lang.Boolean.TRUE
        }

        //build the filtered list
        for (recipe in recipes) {
            addRecipe = java.lang.Boolean.TRUE
            if (filterTime) {
                if (recipe.time!! > maxTime) {
                    addRecipe = java.lang.Boolean.FALSE
                }
            }
            if (addRecipe && filterAllergens) {
                if (!Collections.disjoint(allergens, recipe.allergens)) {
                    addRecipe = java.lang.Boolean.FALSE
                }
            }
            if (addRecipe && filterIngredients) {
                for (ingr in ingredients) {
                    if (!recipe.containsIngredient(ingr!!)) {
                        addRecipe = java.lang.Boolean.FALSE
                    }
                }
            }
            if (addRecipe) {
                filteredList.add(recipe)
            }
        }
        isFiltered = java.lang.Boolean.TRUE
        recipeBookAdapter!!.setRecipeList(filteredList)
        //refresh adapter
        Handler(Looper.getMainLooper()).post { recipeBookAdapter!!.notifyDataSetChanged() }
    }

    override fun onBackPressed() {
        if (isFiltered) {
            recipeBookAdapter!!.setRecipeList(adapterList)
            Handler(Looper.getMainLooper()).post { recipeBookAdapter!!.notifyDataSetChanged() }
            isFiltered = java.lang.Boolean.FALSE
        } else {
            super.onBackPressed()
        }
    }
}