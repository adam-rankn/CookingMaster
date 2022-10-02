package com.rankin.adam.cookingmaster.dialog

import android.app.Dialog
import com.rankin.adam.cookingmaster.activity.RecipeBookActivity
import android.widget.TextView
import android.widget.EditText
import android.os.Bundle
import com.rankin.adam.cookingmaster.R
import android.view.ViewGroup
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.content.DialogInterface
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.rankin.adam.cookingmaster.model.Ingredient
import java.lang.StringBuilder
import java.util.*

class SearchRecipesDialog(private val context: RecipeBookActivity) : Dialog(
    context
) {
    private val thisDialog: Dialog

    init {
        thisDialog = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_search_recipes)
        window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val ingredientEdit1 = findViewById<EditText>(R.id.searchRecipesDialog_edt_add_ingredient_1)
        val ingredientEdit2 = findViewById<EditText>(R.id.searchRecipesDialog_edt_add_ingredient_2)
        val ingredientEdit3 = findViewById<EditText>(R.id.searchRecipesDialog_edt_add_ingredient_3)
        val allergenList =
            ArrayList(listOf(*context.resources.getStringArray(R.array.allergens)))
        val allergensText = findViewById<TextView>(R.id.searchRecipesDialog_txt_allergens)
        val timeEdit = findViewById<EditText>(R.id.searchRecipesDialog_edt_max_time)

        // get list of allergens
        allergensText.setOnClickListener {
            val count = allergenList.size
            val dialogList = allergenList.toTypedArray<CharSequence>()
            val builderDialog = AlertDialog.Builder(
                context
            )
            builderDialog.setTitle("Select Allergens")
            val isChecked = BooleanArray(count)
            builderDialog.setMultiChoiceItems(dialogList, isChecked,
                { dialog, whichButton, isChecked -> })
            builderDialog.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    val list = (dialog as AlertDialog).listView
                    // build the comma separated string
                    val stringBuilder = StringBuilder()
                    for (i in 0 until list.count) {
                        val checked = list.isItemChecked(i)
                        if (checked) {
                            if (stringBuilder.isNotEmpty()) stringBuilder.append(", ")
                            stringBuilder.append(list.getItemAtPosition(i))
                        }
                    }
                    if ((stringBuilder.toString().trim { it <= ' ' } == "")) {
                        (findViewById<View>(R.id.searchRecipesDialog_txt_allergens) as TextView).setText(
                            R.string.no_allergens
                        )
                        stringBuilder.setLength(0)
                    } else {
                        (findViewById<View>(R.id.searchRecipesDialog_txt_allergens) as TextView).text =
                            stringBuilder
                    }
                }
            })
            builderDialog.setNegativeButton(
                "Cancel"
            ) { dialog, which ->
                (findViewById<View>(R.id.searchRecipesDialog_txt_allergens) as TextView).setText(
                    R.string.no_allergens
                )
            }
            val alert = builderDialog.create()
            alert.show()
        }
        val searchButton = findViewById<Button>(R.id.searchRecipesDialog_btn_search)
        searchButton.setOnClickListener {
            val ingredients = ArrayList<Ingredient?>()
            val allergens = ArrayList(
                listOf(
                    *allergensText.text.toString().split(", ").toTypedArray()
                )
            )
            if (ingredientEdit1.text.toString().isNotEmpty()) {
                ingredients.add(Ingredient(ingredientEdit1.text.toString()))
            }
            if (ingredientEdit2.text.toString().isNotEmpty()) {
                ingredients.add(Ingredient(ingredientEdit2.text.toString()))
            }
            if (ingredientEdit3.text.toString().isNotEmpty()) {
                ingredients.add(Ingredient(ingredientEdit3.text.toString()))
            }
            val time: Int
            if (timeEdit.text.toString().trim { it <= ' ' }.isEmpty()) {
                time = 999999999
            } else {
                time = timeEdit.text.toString().toInt()
            }
            context.filterRecipeList(time, ingredients, allergens)
            thisDialog.dismiss()
        }
    }
}