package com.rankin.adam.cookingmaster.Activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rankin.adam.cookingmaster.Adapters.IngredientLayoutAdapter;
import com.rankin.adam.cookingmaster.Model.Ingredient;
import com.rankin.adam.cookingmaster.R;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.recipeController;
import static com.rankin.adam.cookingmaster.Activities.MainActivity.recipeList;

/**
 * Created by Adam on 08-Jul-18.
 */

public class IngredientsDialog extends Dialog {
    private AddRecipeActivity activity;
    private IngredientsDialog thisDialog;

    private EditText ingredientText;

    private ArrayList<Ingredient> ingredients;
    private ArrayList<Ingredient> adapterList;


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
        ingredients.addAll(recipeController.getIngredients());


        initalize();


    }

    private void initalize() {

        ingredientText = findViewById(R.id.ingrDialog_txt_add_ingredient);

        RecyclerView ingredientRecyclerView = findViewById(R.id.ingrDialog_recyclerView);
        LinearLayoutManager ingredientLinearLayoutManager = new LinearLayoutManager(getContext());
        ingredientRecyclerView.setLayoutManager(ingredientLinearLayoutManager);
        IngredientLayoutAdapter ingredientAdapter = new IngredientLayoutAdapter(ingredients, getContext());
        ingredientRecyclerView.setAdapter(ingredientAdapter);


        Button addIngredientButton = findViewById(R.id.ingrDialog_btn_add_ingredient);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String ingrString= ingredientText.getText().toString();
                Ingredient ingredient = new Ingredient(ingrString);
                ingredients.add(ingredient);


                //refresh the recyclerview
                adapterList = new ArrayList<>();
                adapterList.clear();
                adapterList.addAll(recipeController.getIngredients());
                recipeController.addIngredient(ingredient);

                RecyclerView ingredientRecyclerView = findViewById(R.id.ingrDialog_recyclerView);
                LinearLayoutManager ingredientLinearLayoutManager = new LinearLayoutManager(getContext());
                ingredientRecyclerView.setLayoutManager(ingredientLinearLayoutManager);
                IngredientLayoutAdapter ingredientAdapter = new IngredientLayoutAdapter(ingredients, getContext());
                ingredientRecyclerView.setAdapter(ingredientAdapter);
                ingredientAdapter.notifyDataSetChanged();

                ingredientText.setText("");

            }
        });

        Button saveButton = findViewById(R.id.ingrDialog_btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeController.setIngredients(ingredients);
                thisDialog.dismiss();
            }
        });
    }

}
