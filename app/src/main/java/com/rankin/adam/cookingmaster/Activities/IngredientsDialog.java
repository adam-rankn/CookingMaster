package com.rankin.adam.cookingmaster.Activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rankin.adam.cookingmaster.Model.Ingredient;
import com.rankin.adam.cookingmaster.R;

import java.util.ArrayList;

/**
 * Created by Adam on 08-Jul-18.
 */

public class IngredientsDialog extends Dialog {
    private AddRecipeActivity activity;
    private IngredientsDialog thisDialog;

    private EditText ingredientText;

    private ArrayList<Ingredient> ingredients;


    public IngredientsDialog(AddRecipeActivity context) {
        super(context);
        this.activity = context;
        this.thisDialog = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_dialog);
        getWindow().setLayout(android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("pie"));


        initalize();


    }

    private void initalize() {

        ingredientText = findViewById(R.id.ingrDialog_txt_add_ingredient);

        RecyclerView ingredientRecyclerView = (RecyclerView) findViewById(R.id.ingrDialog_recyclerView);
        LinearLayoutManager ingredientLinearLayoutManager = new LinearLayoutManager(getContext());
        ingredientRecyclerView.setLayoutManager(ingredientLinearLayoutManager);
        IngredientLayoutAdapter ingredientAdapter = new IngredientLayoutAdapter(ingredients, getContext());
        ingredientRecyclerView.setAdapter(ingredientAdapter);


        Button cancelButton = findViewById(R.id.ingrDialog_btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisDialog.cancel();
            }
        });

        Button addIngredientButton = findViewById(R.id.ingrDialog_btn_add_ingredient);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String ingrString= ingredientText.getText().toString();
                Ingredient ingredient = new Ingredient(ingrString);
                ingredients.add(ingredient);

            }
        });

        Button saveButton = findViewById(R.id.ingrDialog_btn_cancel);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save the list
            }
        });
    }

}
