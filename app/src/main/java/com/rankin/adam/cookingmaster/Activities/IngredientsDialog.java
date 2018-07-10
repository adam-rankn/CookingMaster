package com.rankin.adam.cookingmaster.Activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rankin.adam.cookingmaster.Ingredient;
import com.rankin.adam.cookingmaster.R;

import java.util.ArrayList;

/**
 * Created by Adam on 08-Jul-18.
 */

public class IngredientsDialog extends Dialog {
    private AddRecipeActivity activity;
    private IngredientsDialog thisDialog;

    private Integer searchBy = 0;
    private String textString;
    private EditText ingredientText;


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
        initalize();
    }

    private void initalize() {

        ingredientText = findViewById(R.id.ingrDialog_txt_add_ingredient);


        Button cancel = findViewById(R.id.ingrDialog_btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisDialog.cancel();
            }
        });

        Button addIngredientButton = findViewById(R.id.ingrDialog_btn_add_ingredient);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String ingredient= ingredientText.getText().toString();
                Ingredient ingredient = new Ingredient();

            }
        });
    }
}
