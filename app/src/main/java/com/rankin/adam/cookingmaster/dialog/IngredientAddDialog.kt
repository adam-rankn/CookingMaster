package com.rankin.adam.cookingmaster.dialog

import android.app.Dialog
import android.content.Context
import com.rankin.adam.cookingmaster.activity.AddRecipeActivity
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry
import android.os.Bundle
import android.os.Handler
import com.rankin.adam.cookingmaster.R
import com.rankin.adam.cookingmaster.activity.MainActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.rankin.adam.cookingmaster.adapter.IngredientLayoutAdapter
import com.rankin.adam.cookingmaster.model.Ingredient
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import java.util.ArrayList

/**
 * Created by Adam on 08-Jul-18.
 */
class IngredientAddDialog(context: AddRecipeActivity) : Dialog(context) {
    private val thisDialog: IngredientAddDialog = this
    private var ingredients: ArrayList<RecipeIngredientEntry>? = null
    private var unit: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_ingredients_dialog)
        window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        ingredients = ArrayList()
        ingredients!!.addAll(MainActivity.recipeController.ingredients)
        initialize()
    }

    private fun initialize() {
        val ingredientEdit = findViewById<EditText>(R.id.ingrDialog_txt_add_ingredient)
        val amountEdit = findViewById<EditText>(R.id.ingrDialog_edt_amount)
        val unitSpinner = findViewById<Spinner>(R.id.ingrDialog_spn_unit)
        val unitAdapter = ArrayAdapter.createFromResource(
            context,
            R.array.recipe_units_array, android.R.layout.simple_spinner_item
        )
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = unitAdapter
        unitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                unit = parent.getItemAtPosition(pos).toString()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
        val ingredientRecyclerView = findViewById<RecyclerView>(R.id.ingrDialog_recyclerView)
        val ingredientLinearLayoutManager = LinearLayoutManager(context)
        ingredientRecyclerView.layoutManager = ingredientLinearLayoutManager
        val ingredientAdapter = IngredientLayoutAdapter(ingredients, context)
        ingredientRecyclerView.adapter = ingredientAdapter

        // TODO add speech recognition for ingredients
        val addIngredientButton = findViewById<Button>(R.id.ingrDialog_btn_add_ingredient)
        addIngredientButton.setOnClickListener {
            val ingrString = ingredientEdit.text.toString()
            val amountString = amountEdit.text.toString()
            val ingredient = Ingredient(ingrString)
            val entry = RecipeIngredientEntry(ingredient)
            if (amountString.trim { it <= ' ' }.isNotEmpty()) {
                entry.amount = amountString.toInt().toFloat()
                entry.unit = unitSpinner.selectedItem.toString()
            }
            if (ingrString.trim { it <= ' ' }.isEmpty()) {
                ingredientEdit.error = "please enter an ingredient"
            } else {
                ingredients!!.add(entry)
                ingredientAdapter.addIngredient(entry)
                //TODO specific change event
                Handler(Looper.getMainLooper()).post({ ingredientAdapter.notifyDataSetChanged() })
                MainActivity.recipeController.addIngredient(entry)
                ingredientEdit.setText("")
                amountEdit.setText("")

                //close the keyboard
                val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
            }
        }
        val saveButton = findViewById<Button>(R.id.ingrDialog_btn_save)
        saveButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                MainActivity.recipeController.ingredients = ingredients
                thisDialog.dismiss()
            }
        })
    }
}