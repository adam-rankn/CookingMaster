package com.rankin.adam.cookingmaster.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.Model.Recipe;
import com.rankin.adam.cookingmaster.R;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.recipeController;

public class ViewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        TextView nameText = findViewById(R.id.viewRecipeAct_txt_name);
        TextView timeText = findViewById(R.id.viewRecipeAct_txt_time);
        TextView instructionsText = findViewById(R.id.viewRecipeAct_txt_instructions);

        String name = recipeController.getName();
        String time = recipeController.getTime();
        String instructions = recipeController.getInstructions();

        nameText.setText(name);
        timeText.setText(time);
        instructionsText.setText(instructions);
    }
}
