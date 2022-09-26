package com.rankin.adam.cookingmaster.dialog;


import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;
import static com.rankin.adam.cookingmaster.activity.MainActivity.shoppingListController;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.controller.RecipeImportController;
import com.rankin.adam.cookingmaster.model.Recipe;
import com.rankin.adam.cookingmaster.model.ShoppingListEntry;

import java.net.MalformedURLException;
import java.net.URL;

public class AddRecipeFromURLDialogue extends Dialog implements AdapterView.OnItemSelectedListener {

    private EditText addURLEditText;

    public AddRecipeFromURLDialogue(@NonNull Context context) {
        super(context);



    }
    protected void OnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addURLEditText = findViewById(R.id.addURLEditText);

        Button saveButton = findViewById(R.id.btn_add_from_url);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String recipeURL = addURLEditText.getText().toString();
                    RecipeImportController recipeImporter = new RecipeImportController(recipeURL);
                    Recipe importedRecipe = recipeImporter.getRecipe();
                    recipeController.addRecipe(importedRecipe);

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
