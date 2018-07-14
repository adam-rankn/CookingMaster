package com.rankin.adam.cookingmaster.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.Controllers.RecipeController;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.Model.Recipe;

import java.util.ArrayList;
import java.util.List;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.recipeController;

public class AddRecipeActivity extends AppCompatActivity {

    private List<String> allergenList = new ArrayList<>();
    private int RECIPE_ADD_FLAG = 0;
    private int RECIPE_EDIT_FLAG = 1;
    private int mode;

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


        //mode = getIntent().getIntExtra("Mode",0);
        //if (mode == 1){
        //    Button saveRecipeButton = findViewById(R.id.addRecipeAct_btn_add_recipe);
        //    saveRecipeButton.setText("Save");
        //}

        Button addButton = findViewById(R.id.addRecipeAct_btn_add_recipe);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get recipe ingo from view
                EditText nameEdit = findViewById(R.id.addRecipeAct_txt_name);
                EditText timeEdit = findViewById(R.id.addRecipeAct_txt_time);
                EditText instructionsEdit = findViewById(R.id.addRecipeAct_txt_instructions);

                if (nameEdit.getText().toString().trim().isEmpty()){
                    nameEdit.setError("Recipe name required");
                }


                else {
                    String name = nameEdit.getText().toString();
                    Recipe newRecipe = new Recipe(name);

                    String time = timeEdit.getText().toString();
                    newRecipe.setTime(time);

                    String instructions = instructionsEdit.getText().toString();
                    newRecipe.setInstructions(instructions);

                    //TODO add the ingredients and allergens list

                    recipeController.addRecipe(newRecipe);
                    recipeController.saveRecipes();
                    finish();
                }
            }
        });

        Button cancelButton = findViewById(R.id.addRecipeAct_btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button ingredientsButton = findViewById(R.id.addRecipeAct_btn_set_ingredients);
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

        AlertDialog.Builder ingredientDialog = new AlertDialog.Builder(this);
        View convertView = LayoutInflater.from(this).inflate(R.layout.ingredients_dialog, null);
        ingredientDialog.setView(convertView);
        ingredientDialog.setTitle("Add Ingredients");
        Dialog dialog = ingredientDialog.create();



    }

}




