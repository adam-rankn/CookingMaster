package com.rankin.adam.cookingmaster.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rankin.adam.cookingmaster.R
import com.rankin.adam.cookingmaster.controller.SaveLoadController
import com.rankin.adam.cookingmaster.dialog.IngredientViewDialog
import java.lang.Boolean
import kotlin.String

class ViewRecipeActivity : AppCompatActivity() {
    private val EDIT_RECIPE_FLAG = 1
    private val EDIT_RECIPE = 2
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recipe)
        val rating = findViewById<RatingBar>(R.id.viewRecipeAct_rating_bar)
        rating.rating = MainActivity.recipeController.rating
        val nameText = findViewById<TextView>(R.id.viewRecipeAct_txt_name)
        val timeText = findViewById<TextView>(R.id.viewRecipeAct_txt_time)
        val instructionsText = findViewById<TextView>(R.id.viewRecipeAct_edt_instructions)
        val recipeImage = findViewById<ImageView>(R.id.viewRecipeAct_photo)
        val expandedRecipeImage = findViewById<ImageView>(R.id.viewRecipeAct_expanded_photo)
        val name = MainActivity.recipeController.name
        val time = MainActivity.recipeController.time
        val instructions = MainActivity.recipeController.instructions

        // get image uri String from file, convert to URI
        val recipeUriString = MainActivity.recipeController.imageUri
        val uri = Uri.parse(recipeUriString)
        nameText.text = name
        timeText.text = time.toString()
        instructionsText.text = instructions
        recipeImage.setImageURI(uri)
        rating.onRatingBarChangeListener = OnRatingBarChangeListener { ratingBar, v, b ->
            MainActivity.recipeController.rating = v
        }
        val viewIngredientsButton = findViewById<Button>(R.id.viewRecipeAct_btn_view_ingredients)
        viewIngredientsButton.setOnClickListener {
            val ingredientViewDialog = IngredientViewDialog(this@ViewRecipeActivity)
            ingredientViewDialog.show()
        }
        val cookButton = findViewById<Button>(R.id.viewRecipeAct_btn_cook)
        cookButton.setOnClickListener {
            val cookIntent = Intent(this@ViewRecipeActivity, CookingActivity::class.java)
            startActivity(cookIntent)
        }
        val viewAllergensButton = findViewById<Button>(R.id.viewRecipeAct_btn_view_allergens)
        viewAllergensButton.setOnClickListener {
            val allergenList = MainActivity.recipeController.allergens
            val builderDialog = AlertDialog.Builder(this@ViewRecipeActivity)
            val arrayAdapter =
                ArrayAdapter<String>(this@ViewRecipeActivity, android.R.layout.select_dialog_item)
            arrayAdapter.addAll(allergenList)
            builderDialog.setAdapter(arrayAdapter) { dialogInterface, i -> }
            builderDialog.setPositiveButton("OK") { dialog, which -> }
            val alert = builderDialog.create()
            alert.show()
        }
        val editRecipeButton = findViewById<Button>(R.id.viewRecipeAct_btn_edit)
        editRecipeButton.setOnClickListener {
            val editRecipeIntent = Intent(this@ViewRecipeActivity, AddRecipeActivity::class.java)
            //Open addRecipeActivity in edit mode
            editRecipeIntent.putExtra("Flag", EDIT_RECIPE_FLAG)
            startActivityForResult(editRecipeIntent, EDIT_RECIPE)
        }
        val deleteRecipeButton = findViewById<Button>(R.id.viewRecipeAct_btn_delete)
        deleteRecipeButton.setOnClickListener {
            val builder =
                AlertDialog.Builder(this@ViewRecipeActivity, R.style.Theme_AppCompat_Dialog_Alert)
            builder.setTitle(R.string.app_name)
            builder.setMessage("Do you want to delete '" + nameText.text.toString() + "'?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { dialog, id ->
                MainActivity.recipeController.deleteCurrentRecipe()
                MainActivity.recipeController.deletedFlag = Boolean.TRUE
                dialog.dismiss()
                finish()
            }
            builder.setNegativeButton("No") { dialog, id -> dialog.dismiss() }
            val alert = builder.create()
            alert.show()
        }


        //TODO change default img from background to pic to prevent seeing background image in non square pics
        //enlarge button on image click
        recipeImage.setOnClickListener {
            expandedRecipeImage.visibility = View.VISIBLE
            recipeImage.visibility = View.INVISIBLE
            expandedRecipeImage.bringToFront()
            (expandedRecipeImage.parent as View).invalidate()
            (expandedRecipeImage.parent as View).requestLayout()
            viewIngredientsButton.visibility = View.INVISIBLE
            viewAllergensButton.visibility = View.INVISIBLE
        }

        //shrink image on enlarged image click
        expandedRecipeImage.setOnClickListener {
            expandedRecipeImage.visibility = View.INVISIBLE
            recipeImage.visibility = View.VISIBLE
            viewIngredientsButton.visibility = View.VISIBLE
            viewAllergensButton.visibility = View.VISIBLE
        }
    }

    override fun onRestart() {
        super.onRestart()
        invalidateOptionsMenu()
        //ensure that the recipes are displayed correctly
        recreate()
    }

    override fun onPause() {
        super.onPause()
        val saveLoadController = SaveLoadController(applicationContext)
        saveLoadController.saveRecipesToFile()
    }
}