package com.rankin.adam.cookingmaster.activity.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    private ArrayList<Ingredient> ingredientList;

    private TextView allergensText;

    private LinearLayout linearLayout;
    private EditText ingredientEdit;

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
        getWindow().setLayout(android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);

        //setup for auto generated TexViews
/*        linearLayout = findViewById(R.id.searchRecipeAct_linearLayout);
        ingredientEdit = (EditText) findViewById(R.id.searcRecipesDialog_edt_add_ingredient);
        TextView textView = new TextView(context);
        textView.setText("text");
        textView.setTextSize(24);
        textView.setTextColor(0);*/

        ingredientList = new ArrayList<>();
        ingredientEdit1 = findViewById(R.id.searcRecipesDialog_edt_add_ingredient_1);
        ingredientEdit2 = findViewById(R.id.searcRecipesDialog_edt_add_ingredient_2);
        ingredientEdit3 = findViewById(R.id.searcRecipesDialog_edt_add_ingredient_3);


        allergenList = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.allergens)));
        allergensText = findViewById(R.id.searchRecipesDialog_txt_allergens);
        final EditText timeEdit = findViewById(R.id.searchRecipesDialog_edt_max_time);

/*        Button addIngredientButton = findViewById(R.id.searchRecipesDialog_btn_add);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.addView(createNewTextView(ingredientEdit.getText().toString()));

            }
        });*/

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
                            ((TextView) findViewById(R.id.searchRecipesDialog_txt_allergens)).setText("No Allergens");
                            stringBuilder.setLength(0);
                        } else {
                            ((TextView) findViewById(R.id.searchRecipesDialog_txt_allergens)).setText(stringBuilder);
                        }
                    }
                });
                builderDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((TextView) findViewById(R.id.searchRecipesDialog_txt_allergens)).setText("No Allergens");
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

/*    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(context);
        textView.setLayoutParams(lparams);
        textView.setTextSize(18);
        textView.setText(text);
        return textView;
    }*/



}
