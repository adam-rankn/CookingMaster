package com.rankin.adam.cookingmaster.Activities.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rankin.adam.cookingmaster.Activities.AddRecipeActivity;
import com.rankin.adam.cookingmaster.Adapters.IngredientLayoutAdapter;
import com.rankin.adam.cookingmaster.Model.Ingredient;
import com.rankin.adam.cookingmaster.R;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.recipeController;

/**
 * Created by Adam on 08-Jul-18.
 */

public class IngredientAddDialog extends Dialog {
    private IngredientAddDialog thisDialog;
    private EditText ingredientText;
    private ArrayList<Ingredient> ingredients;



    public IngredientAddDialog(AddRecipeActivity context) {
        super(context);
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
        initialize();

    }

    private void initialize() {

        ingredientText = findViewById(R.id.ingrDialog_txt_add_ingredient);

        RecyclerView ingredientRecyclerView = findViewById(R.id.ingrDialog_recyclerView);
        LinearLayoutManager ingredientLinearLayoutManager = new LinearLayoutManager(getContext());
        ingredientRecyclerView.setLayoutManager(ingredientLinearLayoutManager);
        final IngredientLayoutAdapter ingredientAdapter = new IngredientLayoutAdapter(ingredients, getContext());
        ingredientRecyclerView.setAdapter(ingredientAdapter);

        // TODO add speech recognitionn for ingredients
        Button addIngredientButton = findViewById(R.id.ingrDialog_btn_add_ingredient);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String ingrString= ingredientText.getText().toString();

                if (ingrString.trim().length() == 0){
                    ingredientText.setError("please enter an ingredient");
                }

                else {
                    Ingredient ingredient = new Ingredient(ingrString);
                    ingredients.add(ingredient);

                    ingredientAdapter.addIngredient(ingredient);

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            ingredientAdapter.notifyDataSetChanged();
                        }
                    });

                    recipeController.addIngredient(ingredient);
                    ingredientText.setText("");
                }

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
