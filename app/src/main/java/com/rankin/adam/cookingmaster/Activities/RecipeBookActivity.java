package com.rankin.adam.cookingmaster.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rankin.adam.cookingmaster.R;

public class RecipeBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);

        Button returnToMainButton = (Button) findViewById(R.id.btn_main);
        returnToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button addRecipeButton = (Button) findViewById(R.id.btn_add_recipe);
        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRecipeIntent = new Intent(RecipeBookActivity.this, AddRecipeActivity.class);
                startActivity(addRecipeIntent);
            }
        });
    }


}
