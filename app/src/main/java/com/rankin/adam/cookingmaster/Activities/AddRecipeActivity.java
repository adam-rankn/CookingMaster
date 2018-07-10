package com.rankin.adam.cookingmaster.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.Ingredient;
import com.rankin.adam.cookingmaster.R;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {

    List<String> allergenList = new ArrayList<>();
    private int RECIPE_ADD_FLAG = 0;
    private int RECIPE_EDIT_FLAG = 1;
    private int mode;

    private RecyclerView ingredientRecyclerView;
    private LinearLayoutManager ingredientLinearLayoutManager;
    private IngredientLayoutAdapter ingredientAdapter;

    private ArrayList<Ingredient> ingredientList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        allergenList.add("Soy");
        allergenList.add("Wheat");
        allergenList.add("Dairy");
        allergenList.add("Peanut");
        allergenList.add("Tree nuts");
        allergenList.add("Fish");
        allergenList.add("Shellfish");
        allergenList.add("Peanut");
        allergenList.add("Eggs");

        ingredientRecyclerView = (RecyclerView) findViewById(R.id.ingrDialog_recyclerView);
        ingredientLinearLayoutManager = new LinearLayoutManager(this);
        ingredientRecyclerView.setLayoutManager(ingredientLinearLayoutManager);
        ingredientAdapter = new IngredientLayoutAdapter(ingredientList, this);
        ingredientRecyclerView.setAdapter(ingredientAdapter);

        //mode = getIntent().getIntExtra("Mode",0);
        //if (mode == 1){
        //    Button saveRecipeButton = findViewById(R.id.addRecipeAct_btn_add_recipe);
        //    saveRecipeButton.setText("Save");
        //}

        Button ingredientsButton = (Button) findViewById(R.id.addRecipeAct_btn_set_ingredients);
        ingredientsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IngredientsDialog ingredientsDialog = new IngredientsDialog(AddRecipeActivity.this);
                ingredientsDialog.show();
            }
        });


        View openDialog = (View) findViewById(R.id.txt_allergen_list);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = allergenList.size();
                final CharSequence[] dialogList =  allergenList.toArray(new CharSequence[count]);
                final AlertDialog.Builder builderDialog = new AlertDialog.Builder(AddRecipeActivity.this);
                builderDialog.setTitle("Select Item");
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
                    // build the comma seperated list
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < list.getCount(); i++) {
                        boolean checked = list.isItemChecked(i);
                        if (checked) {
                            if (stringBuilder.length() > 0) stringBuilder.append(',');
                            stringBuilder.append(list.getItemAtPosition(i).toString());
                        }
                    }
                    if (stringBuilder.toString().trim().equals("")) {
                        ((TextView) findViewById(R.id.txt_allergen_list)).setText("No Allergens");
                        stringBuilder.setLength(0);
                    } else {
                        ((TextView) findViewById(R.id.txt_allergen_list)).setText(stringBuilder);
                    }
                }
                });
                builderDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((TextView) findViewById(R.id.text)).setText("No Allergens");
                    }
                });
                AlertDialog alert = builderDialog.create();
                alert.show();

            }
        });
    }
}




