package com.rankin.adam.cookingmaster.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.activity.dialog.IngredientViewDialog;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.controller.SaveLoadController;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

public class ViewRecipeActivity extends AppCompatActivity {

    private final int EDIT_RECIPE_FLAG = 1;
    private final int EDIT_RECIPE = 2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        TextView nameText = findViewById(R.id.viewRecipeAct_txt_name);
        TextView timeText = findViewById(R.id.viewRecipeAct_txt_time);
        TextView instructionsText = findViewById(R.id.viewRecipeAct_txt_instructions);
        ImageView recipeImage = findViewById(R.id.viewRecipeAct_photo);

        String name = recipeController.getName();
        String time = recipeController.getTime();
        String instructions = recipeController.getInstructions();

        // get image uri String from file, convert to URI
        String recipeUriString = recipeController.getImageUri();
        Uri uri = Uri.parse(recipeUriString);

        nameText.setText(name);
        timeText.setText(time);
        instructionsText.setText(instructions);
        recipeImage.setImageURI(uri);

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
                ArrayList<String> allergenList = recipeController.getAllergens();

                final AlertDialog.Builder builderDialog = new AlertDialog.Builder(ViewRecipeActivity.this);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ViewRecipeActivity.this, android.R.layout.select_dialog_item);
                arrayAdapter.addAll(allergenList);

                builderDialog.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


                builderDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alert = builderDialog.create();
                alert.show();

            }
        });

        Button editRecipeButton = findViewById(R.id.viewRecipeAct_btn_edit);
        editRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editRecipeIntent = new Intent(ViewRecipeActivity.this, AddRecipeActivity.class);
                editRecipeIntent.putExtra("Flag",EDIT_RECIPE_FLAG);
                startActivityForResult(editRecipeIntent,EDIT_RECIPE);
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


    @Override
    protected void onRestart() {
        super.onRestart();
        invalidateOptionsMenu();
        //ensure that the recipes are displayed correctly
        recreate();

    }

    @Override
    protected void onPause(){
        super.onPause();
        SaveLoadController saveLoadController = new SaveLoadController(getApplicationContext());
        saveLoadController.saveRecipesToFile();
    }


}
