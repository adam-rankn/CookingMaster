package com.rankin.adam.cookingmaster.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.Activities.Dialogs.IngredientViewDialog;
import com.rankin.adam.cookingmaster.R;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.recipeController;

public class ViewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        TextView nameText = findViewById(R.id.viewRecipeAct_txt_name);
        TextView timeText = findViewById(R.id.viewRecipeAct_txt_time);
        TextView instructionsText = findViewById(R.id.viewRecipeAct_txt_instructions);

        String name = recipeController.getName();
        String time = recipeController.getTime();
        String instructions = recipeController.getInstructions();

        nameText.setText(name);
        timeText.setText(time);
        instructionsText.setText(instructions);

        Button viewIngredientsButton = findViewById(R.id.viewRecipeAct_btn_view_ingredients);
        viewIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngredientViewDialog ingredientViewDialog = new IngredientViewDialog(ViewRecipeActivity.this);
                ingredientViewDialog.show();
            }
        });

        Button viewAllergensButton = findViewById(R.id.viewRecipeAct_btn_view_allergens);
        viewAllergensButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO show the allergens
            }
        });

        Button editRecipeButton = findViewById(R.id.viewRecipeAct_btn_edit);
        editRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO edit recipe activity
            }
        });

        Button deleteRecipeButton = findViewById(R.id.viewRecipeAct_btn_delete);
        deleteRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeController.deleteCurrentRecipe();
                finish();
            }
        });

    }
}
