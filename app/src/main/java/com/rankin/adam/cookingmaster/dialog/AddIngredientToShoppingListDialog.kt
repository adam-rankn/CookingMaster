package com.rankin.adam.cookingmaster.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.rankin.adam.cookingmaster.R
import com.rankin.adam.cookingmaster.activity.MainActivity
import com.rankin.adam.cookingmaster.controller.SaveLoadController
import com.rankin.adam.cookingmaster.model.ShoppingListEntry

/**
 * Created by Adam on 16-Jul-18.
 */
class AddIngredientToShoppingListDialog(private val viewRecipeContext: Context) : Dialog(
    viewRecipeContext
), AdapterView.OnItemSelectedListener {
    private var unit: String = "lbs"
    private val saveLoadController: SaveLoadController = SaveLoadController(viewRecipeContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_ingredient_to_shopping_list_dialog)
        window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val unitSpinner = findViewById<Spinner>(R.id.addIngrShopListDialog_spinner_units)
        val unitAdapter = ArrayAdapter.createFromResource(
            viewRecipeContext,
            R.array.units_array, android.R.layout.simple_spinner_item
        )
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = unitAdapter
        unitSpinner.onItemSelectedListener = this
        val amountEditText = findViewById<EditText>(R.id.addIngrShopListDialog_edit_amount)
        val ingredientTextView =
            findViewById<TextView>(R.id.addIngrShopListDialog_txt_ingredient_name)
        ingredientTextView.text = MainActivity.shoppingListController.currentIngredient.name
        val addButton = findViewById<Button>(R.id.addIngrShopListDialog_btn_add)
        addButton.setOnClickListener {
            if (amountEditText.text.toString().trim { it <= ' ' }.isEmpty()) {
                amountEditText.error = "please enter a quantity"
            } else {
                val amountString = amountEditText.text.toString()
                val ingredient = MainActivity.shoppingListController.currentIngredient
                val amount = amountString.toInt()
                val shoppingListEntry = ShoppingListEntry(ingredient, amount, unit)
                MainActivity.shoppingListController.addIngredient(shoppingListEntry)
                saveLoadController.saveShoppingListToFile()
                dismiss()
            }
        }
    }

    override fun onItemSelected(
        parent: AdapterView<*>, view: View,
        pos: Int, id: Long
    ) {
        unit = parent.getItemAtPosition(pos).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}