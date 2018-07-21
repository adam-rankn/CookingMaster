package com.rankin.adam.cookingmaster.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rankin.adam.cookingmaster.activity.AddRecipeActivity;
import com.rankin.adam.cookingmaster.adapter.IngredientLayoutAdapter;
import com.rankin.adam.cookingmaster.model.Ingredient;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

/**
 * Created by Adam on 08-Jul-18.
 */

public class IngredientAddDialog extends Dialog {
    private IngredientAddDialog thisDialog;
    private EditText ingredientEdit;
    private ArrayList<RecipeIngredientEntry> ingredients;
    private EditText amountEdit;
    private Spinner unitSpinner;

    private String unit = "lb";
    private Context context;


    public IngredientAddDialog(AddRecipeActivity context) {
        super(context);
        this.thisDialog = this;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ingredients_dialog);
        getWindow().setLayout(android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

        ingredients = new ArrayList<>();
        ingredients.addAll(recipeController.getIngredients());
        initialize();

    }

    private void initialize() {

        ingredientEdit = findViewById(R.id.ingrDialog_txt_add_ingredient);
        amountEdit = findViewById(R.id.ingrDialog_edt_amount);

        final Spinner unitSpinner = findViewById(R.id.ingrDialog_spn_unit);
        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.recipe_units_array, android.R.layout.simple_spinner_item);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(unitAdapter);
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                unit = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                String ingrString = ingredientEdit.getText().toString();
                String amountString = amountEdit.getText().toString();

                Ingredient ingredient = new Ingredient(ingrString);
                RecipeIngredientEntry entry = new RecipeIngredientEntry(ingredient);

                if (amountString.trim().length() != 0){
                    entry.setAmount(Integer.parseInt(amountString));
                    entry.setUnit(unitSpinner.getSelectedItem().toString());
                }

                if (ingrString.trim().length() == 0){
                    ingredientEdit.setError("please enter an ingredient");
                }

                else {
                    ingredients.add(entry);
                    ingredientAdapter.addIngredient(entry);

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            ingredientAdapter.notifyDataSetChanged();
                        }
                    });

                    recipeController.addIngredient(entry);
                    ingredientEdit.setText("");
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
