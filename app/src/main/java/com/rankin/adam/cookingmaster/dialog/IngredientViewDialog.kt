package com.rankin.adam.cookingmaster.dialog;

import android.app.Dialog;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rankin.adam.cookingmaster.activity.ViewRecipeActivity;
import com.rankin.adam.cookingmaster.adapter.IngredientViewLayoutAdapter;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

/**
 * Created by Adam on 15-Jul-18.
 */

public class IngredientViewDialog extends Dialog {

    private IngredientViewDialog thisDialog;
    private ArrayList<RecipeIngredientEntry> ingredients;

    public IngredientViewDialog(ViewRecipeActivity context) {
        super(context);
        this.thisDialog = this;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_view_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

        ingredients = new ArrayList<>();
        ingredients.addAll(recipeController.getIngredients());
        initialize();
        //TODO add an add all ingr to shop list minus pantry items

    }

    private void initialize() {


        RecyclerView ingredientViewRecyclerView = findViewById(R.id.ingrViewDialog_recyclerView_ingredients);
        LinearLayoutManager ingredientViewLinearLayoutManager = new LinearLayoutManager(getContext());
        ingredientViewRecyclerView.setLayoutManager(ingredientViewLinearLayoutManager);
        IngredientViewLayoutAdapter ingredientViewAdapter = new IngredientViewLayoutAdapter(ingredients, getContext());
        ingredientViewRecyclerView.setAdapter(ingredientViewAdapter);


        Button doneButton = findViewById(R.id.ingrViewDialog_btn_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisDialog.dismiss();
            }
        });
    }
}
