package com.rankin.adam.cookingmaster.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rankin.adam.cookingmaster.R
import com.rankin.adam.cookingmaster.controller.RecipeController
import com.rankin.adam.cookingmaster.controller.SaveLoadController
import com.rankin.adam.cookingmaster.controller.ShoppingListController
import com.rankin.adam.cookingmaster.model.RecipeList
import com.rankin.adam.cookingmaster.model.ShoppingList

class MainActivity : AppCompatActivity() {
    private val saveLoadController = SaveLoadController(this@MainActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (recipeList.size == 0) {
            Handler(Looper.getMainLooper()).post { saveLoadController.loadRecipesFromFile() }
        }
        if (shoppingListController.size == 0) {
            Handler(Looper.getMainLooper()).post { saveLoadController.loadShoppingListFromFile() }
        }
        val recipesButton = findViewById<Button>(R.id.mainAct_btn_recipe_book)
        recipesButton.setOnClickListener {
            val recipeBookIntent = Intent(this@MainActivity, RecipeBookActivity::class.java)
            startActivity(recipeBookIntent)
        }
        //val spiceButton = findViewById<Button>(R.id.mainAct_btn_spices)
        //spiceButton.setOnClickListener {
            //Intent spiceIntent = new Intent(MainActivity.this, Activity.class);
            //startActivity(spiceIntent);
            //TODO this will be changed or removed
        //}
        //val pantryButton = findViewById<Button>(R.id.mainAct_btn_pantry)
        //pantryButton.setOnClickListener {
         //   val pantryIntent = Intent(this@MainActivity, PantryActivity::class.java)
         //   startActivity(pantryIntent)
            //TODO add a pantry feature
        //}
        val shoppingButton = findViewById<Button>(R.id.mainAct_btn_shopping)
        shoppingButton.setOnClickListener {
            val shoppingIntent = Intent(this@MainActivity, ShoppingListActivity::class.java)
            startActivity(shoppingIntent)
        }
        val optionsButton = findViewById<Button>(R.id.mainAct_btn_options)
        optionsButton.setOnClickListener {
            //Intent optionsIntent = new Intent(MainActivity.this, Activity.class);
            //startActivity(optionsIntent);
            //TODO add options menu
        }
    }

    companion object {
        @JvmField
        val recipeList = RecipeList()
        @JvmField
        val recipeController = RecipeController()
        @JvmField
        val shoppingList = ShoppingList()
        @JvmField
        val shoppingListController = ShoppingListController()
    }
}