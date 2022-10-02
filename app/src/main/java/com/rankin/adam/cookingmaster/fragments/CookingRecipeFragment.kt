package com.rankin.adam.cookingmaster.fragments

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rankin.adam.cookingmaster.R
import com.rankin.adam.cookingmaster.activity.MainActivity
import com.rankin.adam.cookingmaster.adapter.IngredientViewLayoutAdapter
import com.rankin.adam.cookingmaster.dialog.RecipeTimerPopup
import com.rankin.adam.cookingmaster.model.Recipe
import java.lang.Boolean
import kotlin.IndexOutOfBoundsException
import kotlin.Int
import kotlin.String
import kotlin.let

class CookingRecipeFragment : Fragment() {
    var pinned: kotlin.Boolean = Boolean.FALSE
        private set
    private var recipe: Recipe? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_cook, container, false)
        val bundle = this.requireArguments()
        val currentRecipe = bundle.getInt("recipe", -1)
        val showIngredientsButton =
            view.findViewById<Button>(R.id.cookRecipeFrag_btn_show_ingredients)
        val showInstructionsButton = view.findViewById<Button>(R.id.cookRecipeFrag_btn_show_instructions)
        val pinRecipeButton = view.findViewById<Button>(R.id.cookRecipeFrag_btn_pin_recipe)
        val scaleButton = view.findViewById<Button>(R.id.cookRecipeFrag_btn_scale)
        val timerButton = view.findViewById<Button>(R.id.cookRecipeFrag_btn_start_timer)
        recipe = try {
            if (currentRecipe != -1) {
                MainActivity.recipeController.pinnedRecipes[currentRecipe]
            } else {
                MainActivity.recipeController.currentRecipe
            }
        } catch (e: IndexOutOfBoundsException) {
            MainActivity.recipeController.pinnedRecipes[MainActivity.recipeController.pinnedSize - 1]
        }
        if (MainActivity.recipeController.isRecipePinned(recipe)) {
            pinned = Boolean.TRUE
            pinRecipeButton.setText(R.string.unpin)
        }

        //build instructions string with links and set to textView
        val instructions = recipe!!.instructions
        val builder = highlightTimers(instructions)
        val instructionsTextView = view.findViewById<TextView>(R.id.cookRecipeFrag_txt_instructions)
        instructionsTextView.movementMethod = LinkMovementMethod.getInstance()
        instructionsTextView.text = builder
        instructionsTextView.visibility = View.GONE
        val recipeTitleTextView = view.findViewById<TextView>(R.id.cookRecipeFrag_txt_title)
        recipeTitleTextView.text = recipe!!.name
        val ingredientsRecyclerView = view.findViewById<RecyclerView>(R.id.cookRecipeFrag_recycler_ingredients)
        val ingredientViewLinearLayoutManager = LinearLayoutManager(context)
        ingredientsRecyclerView.layoutManager = ingredientViewLinearLayoutManager
        val ingredientViewAdapter = context?.let {
            IngredientViewLayoutAdapter(
                recipe!!.ingredientList, it
            )
        }
        ingredientsRecyclerView.adapter = ingredientViewAdapter
        showInstructionsButton.setOnClickListener {
            instructionsTextView.visibility = View.VISIBLE
            ingredientsRecyclerView.visibility = View.GONE
        }
        showIngredientsButton.setOnClickListener {
            ingredientsRecyclerView.visibility = View.VISIBLE
            instructionsTextView.visibility = View.GONE
        }
        pinRecipeButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                if (!pinned) {
                    pinned = Boolean.TRUE
                    if (!MainActivity.recipeController.isRecipePinned(recipe)) {
                        MainActivity.recipeController.pinRecipe(recipe)
                    }
                    pinRecipeButton.setText(R.string.pin)
                } else if (pinned) {
                    pinned = Boolean.FALSE
                    pinRecipeButton.setText(R.string.unpin)
                }
            }
        })
        timerButton.setOnClickListener {
            val builder = AlertDialog.Builder(
                requireContext()
            )
            builder.setTitle("Timer duration in minutes")

            // Set up the input
            val timerEdit = EditText(context)
            timerEdit.hint = "5"

            // Specify the type of input expected
            timerEdit.inputType = InputType.TYPE_CLASS_DATETIME
            builder.setView(timerEdit)

            // Set up buttons
            builder.setPositiveButton(R.string.OK) { dialog, which ->
                val time: Int = if (timerEdit.text.toString().trim { it <= ' ' }.isEmpty()) {
                    300
                } else {
                    val timeString = timerEdit.text.toString()
                    timeString.toInt() * 60
                }
                val layoutInflater = context
                    ?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val popupView = layoutInflater.inflate(R.layout.popup_cook_timer, null)
                val popupWindow = RecipeTimerPopup(
                    popupView,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    time,
                    showInstructionsButton,
                    recipe!!.name
                )
            }
            builder.setNegativeButton(R.string.Cancel) { dialog, which -> dialog.cancel() }
            builder.show()
        }
        scaleButton.setOnClickListener {
            val builder = AlertDialog.Builder(
                requireContext()
            )
            builder.setTitle(getText(R.string.scaleRecipeDialogTitle))

            // Set up the input
            val factorEdit = EditText(context)
            factorEdit.hint = "2"

            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            factorEdit.inputType = InputType.TYPE_CLASS_NUMBER
            builder.setView(factorEdit)

            // Set up buttons
            builder.setPositiveButton(R.string.OK) { dialog, which ->
                if (factorEdit.text.toString().trim { it <= ' ' }.isEmpty()) {
                    factorEdit.error = getText(R.string.scaleRecipeFactor)
                }
                val scaleFactor = factorEdit.text.toString().toInt()
                ingredientViewAdapter?.setScaleFactor(scaleFactor)
            }
            builder.setNegativeButton(R.string.Cancel) { dialog, which -> dialog.cancel() }
            builder.show()
        }
        return view
    }

    private fun highlightTimers(text: String?): SpannableStringBuilder {

        //split on one or more whitespaces
        val splitInstructions = text!!.split("\\s+").toTypedArray()
        val builder = SpannableStringBuilder()
        for (word in splitInstructions) {
            //check if the current word is a valid time

            //match XX minutes
            if (word.matches("\\d+".toRegex())) {
                val ss = SpannableString(word)
                val time = word.toInt() * 60
                ss.setSpan(
                    timerClickableSpan(time),
                    0,
                    word.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                builder.append(ss)
            } else if (word.matches("[\\d]\\S[:]\\S[\\d+]".toRegex())) {
                val ss = SpannableString(word)

                //get the time in seconds
                var time = word.substring(0, word.indexOf(":")).toInt() * 60
                time += word.substring(word.indexOf(":") + 1).toInt()
                ss.setSpan(
                    timerClickableSpan(time),
                    0,
                    word.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                builder.append(ss)
            } else {
                builder.append(word)
            }
            //add space
            builder.append(" ")
        }
        return builder
    }

    inner class timerClickableSpan(var time: Int?) : ClickableSpan() {
        override fun onClick(view: View) {
            val showInstructionsButton = view.findViewById<Button>(R.id.cookRecipeFrag_btn_show_instructions)

            //open timer
            val layoutInflater = context
                ?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = layoutInflater.inflate(R.layout.popup_cook_timer, null)
            val popupWindow = RecipeTimerPopup(
                popupView,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                time!!,
                showInstructionsButton!!,
                recipe!!.name
            )
        }
    }
}