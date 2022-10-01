package com.rankin.adam.cookingmaster.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.activity.RecipeBookActivity;
import com.rankin.adam.cookingmaster.model.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchRecipesDialog extends Dialog {

    private RecipeBookActivity context;
    private Dialog thisDialog;

    private ArrayList<String> allergenList;

    private TextView allergensText;
    private EditText ingredientEdit1;
    private EditText ingredientEdit2;
    private EditText ingredientEdit3;

    public SearchRecipesDialog(RecipeBookActivity context) {
        super(context);
        this.context = context;
        thisDialog = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_search_recipes);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

        ingredientEdit1 = findViewById(R.id.searchRecipesDialog_edt_add_ingredient_1);
        ingredientEdit2 = findViewById(R.id.searchRecipesDialog_edt_add_ingredient_2);
        ingredientEdit3 = findViewById(R.id.searchRecipesDialog_edt_add_ingredient_3);

        allergenList = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.allergens)));
        allergensText = findViewById(R.id.searchRecipesDialog_txt_allergens);
        final EditText timeEdit = findViewById(R.id.searchRecipesDialog_edt_max_time);

        // get list of allergens
        allergensText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = allergenList.size();
                final CharSequence[] dialogList =  allergenList.toArray(new CharSequence[count]);
                final AlertDialog.Builder builderDialog = new AlertDialog.Builder(context);
                builderDialog.setTitle("Select Allergens");
                boolean[] is_checked = new boolean[count];
                builderDialog.setMultiChoiceItems(dialogList, is_checked,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton, boolean isChecked) {
                            }
                        });
                builderDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListView list = ((AlertDialog) dialog).getListView();
                        // build the comma separated string
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < list.getCount(); i++) {
                            boolean checked = list.isItemChecked(i);
                            if (checked) {
                                if (stringBuilder.length() > 0) stringBuilder.append(", ");
                                stringBuilder.append(list.getItemAtPosition(i));
                            }
                        }
                        if (stringBuilder.toString().trim().equals("")) {
                            ((TextView) findViewById(R.id.searchRecipesDialog_txt_allergens)).setText(R.string.no_allergens);
                            stringBuilder.setLength(0);
                        } else {
                            ((TextView) findViewById(R.id.searchRecipesDialog_txt_allergens)).setText(stringBuilder);
                        }
                    }
                });
                builderDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((TextView) findViewById(R.id.searchRecipesDialog_txt_allergens)).setText(R.string.no_allergens);
                    }
                });
                AlertDialog alert = builderDialog.create();
                alert.show();

            }
        });

        Button searchButton = findViewById(R.id.searchRecipesDialog_btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                ArrayList<String> allergens= new ArrayList<>(Arrays.asList(allergensText.getText().toString().split(", ")));
                if (ingredientEdit1.getText().toString().length() > 0){
                    ingredients.add(new Ingredient(ingredientEdit1.getText().toString()));
                }
                if (ingredientEdit2.getText().toString().length() > 0){
                    ingredients.add(new Ingredient(ingredientEdit2.getText().toString()));
                }
                if (ingredientEdit3.getText().toString().length() > 0){
                    ingredients.add(new Ingredient(ingredientEdit3.getText().toString()));
                }

                Integer time;
                if (timeEdit.getText().toString().trim().length() == 0){
                    time = 999999999;
                }
                else {
                    time = Integer.parseInt(timeEdit.getText().toString());
                }
                context.filterRecipeList(time,ingredients,allergens);
                thisDialog.dismiss();
            }
        });
    }
}
