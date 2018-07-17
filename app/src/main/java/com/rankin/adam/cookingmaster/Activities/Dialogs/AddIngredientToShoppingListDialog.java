package com.rankin.adam.cookingmaster.Activities.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.Model.Ingredient;
import com.rankin.adam.cookingmaster.Model.ShoppingListEntry;
import com.rankin.adam.cookingmaster.R;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.shoppingList;
import static com.rankin.adam.cookingmaster.Activities.MainActivity.shoppingListController;

/**
 * Created by Adam on 16-Jul-18.
 */

public class AddIngredientToShoppingListDialog extends Dialog implements AdapterView.OnItemSelectedListener {

    private Context viewRecipeContext;

    private TextView ingredientTextView;
    private EditText amountEditText;
    private Button addButton;

    private String unit;
    private Ingredient ingredient;
    private Integer amount;

    public AddIngredientToShoppingListDialog(@NonNull Context context) {
        super(context);
        viewRecipeContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ingredient_to_shopping_list_dialog);
        getWindow().setLayout(android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

        Spinner unitSpinner = (Spinner) findViewById(R.id.addIngrShopListDialog_spinner_units);
        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(viewRecipeContext,
                R.array.units_array, android.R.layout.simple_spinner_item);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(unitAdapter);
        unitSpinner.setOnItemSelectedListener(this);

        amountEditText = findViewById(R.id.addIngrShopListDialog_edit_amount);
        ingredientTextView = findViewById(R.id.addIngrShopListDialog_txt_ingredient_name);
        ingredientTextView.setText(shoppingListController.getIngredient().getName());

        addButton = findViewById(R.id.addIngrShopListDialog_btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (amountEditText.getText().toString().trim().length() == 0){
                    amountEditText.setError("please enter a quantity");
                }

                else{
                    String amountString = amountEditText.getText().toString();
                    ingredient = shoppingListController.getIngredient();
                    amount = Integer.parseInt(amountString);

                    ShoppingListEntry shoppingListEntry = new ShoppingListEntry(ingredient, amount, unit);
                    shoppingList.addEntry(shoppingListEntry);
                    dismiss();
                }

            }
        });


    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        unit = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
