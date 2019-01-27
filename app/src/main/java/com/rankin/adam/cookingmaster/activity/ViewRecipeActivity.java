package com.rankin.adam.cookingmaster.activity;

import android.annotation.SuppressLint;
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
import android.widget.RatingBar;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.dialog.IngredientViewDialog;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.controller.SaveLoadController;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

public class ViewRecipeActivity extends AppCompatActivity {

    private final int EDIT_RECIPE_FLAG = 1;
    private final int EDIT_RECIPE = 2 ;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        RatingBar rating = findViewById(R.id.viewRecipeAct_rating_bar);
        rating.setRating(recipeController.getRating());
        final TextView nameText = findViewById(R.id.viewRecipeAct_txt_name);
        TextView timeText = findViewById(R.id.viewRecipeAct_txt_time);
        TextView instructionsText = findViewById(R.id.viewRecipeAct_edt_instructions);
        final ImageView recipeImage = findViewById(R.id.viewRecipeAct_photo);
        final ImageView expandedRecipeImage = findViewById(R.id.viewRecipeAct_expanded_photo);

        String name = recipeController.getName();
        Integer time = recipeController.getTime();
        String instructions = recipeController.getInstructions();

        // get image uri String from file, convert to URI
        String recipeUriString = recipeController.getImageUri();
        Uri uri = Uri.parse(recipeUriString);

        nameText.setText(name);
        timeText.setText(time.toString());
        instructionsText.setText(instructions);
        recipeImage.setImageURI(uri);

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                recipeController.setRating(v);
            }
        });

        final Button viewIngredientsButton = findViewById(R.id.viewRecipeAct_btn_view_ingredients);
        viewIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngredientViewDialog ingredientViewDialog = new IngredientViewDialog(ViewRecipeActivity.this);
                ingredientViewDialog.show();
            }
        });

        Button cookButton = findViewById(R.id.viewRecipeAct_btn_cook);
        cookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cookIntent = new Intent(ViewRecipeActivity.this,CookingActivity.class);
                startActivity(cookIntent);
            }
        });


        final Button viewAllergensButton = findViewById(R.id.viewRecipeAct_btn_view_allergens);
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
                //Open addRecipeActivity in edit mode
                editRecipeIntent.putExtra("Flag",EDIT_RECIPE_FLAG);
                startActivityForResult(editRecipeIntent,EDIT_RECIPE);
            }
        });

        Button deleteRecipeButton = findViewById(R.id.viewRecipeAct_btn_delete);
        deleteRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewRecipeActivity.this,R.style.Theme_AppCompat_Dialog_Alert);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Do you want to delete '" + nameText.getText().toString()+"'?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        recipeController.deleteCurrentRecipe();
                        recipeController.setDeletedFlag(Boolean.TRUE);
                        dialog.dismiss();
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        //enlarge button on image click
        recipeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandedRecipeImage.setVisibility(View.VISIBLE);
                recipeImage.setVisibility(View.INVISIBLE);
                expandedRecipeImage.bringToFront();
                ((View)expandedRecipeImage.getParent()).invalidate();
                ((View)expandedRecipeImage.getParent()).requestLayout();

                viewIngredientsButton.setVisibility(View.INVISIBLE);
                viewAllergensButton.setVisibility(View.INVISIBLE);

            }
        });

        //shrink image on enlarged image click
        expandedRecipeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandedRecipeImage.setVisibility(View.INVISIBLE);
                recipeImage.setVisibility(View.VISIBLE);

                viewIngredientsButton.setVisibility(View.VISIBLE);
                viewAllergensButton.setVisibility(View.VISIBLE);
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
