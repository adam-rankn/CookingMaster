package com.rankin.adam.cookingmaster.dialog

import android.app.Dialog
import com.rankin.adam.cookingmaster.activity.ViewRecipeActivity
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry
import android.os.Bundle
import com.rankin.adam.cookingmaster.R
import android.view.ViewGroup
import android.widget.Button
import com.rankin.adam.cookingmaster.activity.MainActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.rankin.adam.cookingmaster.adapter.IngredientViewLayoutAdapter
import java.util.ArrayList

/**
 * Created by Adam on 15-Jul-18.
 */
class IngredientViewDialog(context: ViewRecipeActivity?) : Dialog(
    context!!
) {
    private val thisDialog: IngredientViewDialog = this
    private val ingredients: ArrayList<RecipeIngredientEntry> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingredient_view_dialog)
        window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        ingredients.addAll(MainActivity.recipeController.ingredients)
        initialize()
        //TODO add an add all ingr to shop list
    }

    private fun initialize() {
        val ingredientViewRecyclerView =
            findViewById<RecyclerView>(R.id.ingrViewDialog_recyclerView_ingredients)
        val ingredientViewLinearLayoutManager = LinearLayoutManager(context)
        ingredientViewRecyclerView.layoutManager = ingredientViewLinearLayoutManager
        val ingredientViewAdapter = IngredientViewLayoutAdapter(ingredients, context)
        ingredientViewRecyclerView.adapter = ingredientViewAdapter
        val doneButton = findViewById<Button>(R.id.ingrViewDialog_btn_done)
        doneButton.setOnClickListener { thisDialog.dismiss() }
    }
}