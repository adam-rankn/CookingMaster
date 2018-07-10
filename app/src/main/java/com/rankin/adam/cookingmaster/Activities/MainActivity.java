package com.rankin.adam.cookingmaster.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rankin.adam.cookingmaster.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cookButton = (Button) findViewById(R.id.btn_cook);
        cookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cookIntent = new Intent(MainActivity.this, CookingActivity.class);
                startActivity(cookIntent);
            }
        });

        Button recipesButton = (Button) findViewById(R.id.btn_recipe_book);
        recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recipeBookIntent = new Intent(MainActivity.this, RecipeBookActivity.class);
                startActivity(recipeBookIntent);
            }
        });

        Button spiceButton = (Button) findViewById(R.id.btn_spices);
        spiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent spiceIntent = new Intent(MainActivity.this, Activity.class);
                //startActivity(spiceIntent);

            }
        });
        Button shoppingButton = (Button) findViewById(R.id.btn_shopping);
        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent shoppingIntent = new Intent(MainActivity.this, Activity.class);
                //startActivity(shoppingIntent);
            }
        });

        Button optionsButton = (Button) findViewById(R.id.btn_options);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent optionsIntent = new Intent(MainActivity.this, Activity.class);
                //startActivity(optionsIntent);

            }
        });
    }


}
