package com.rankin.adam.cookingmaster.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.rankin.adam.cookingmaster.Ingredient;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.Recipe;

import java.util.ArrayList;

public class RecipeBookActivity extends AppCompatActivity {

    private RecyclerView recipeBookRecyclerView;
    private LinearLayoutManager recipeBookLinearLayoutManager;
    private RecipeBookLayoutAdapter recipeBookAdapter;

    private ArrayList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);

        ArrayList<Recipe> recipeList = new ArrayList<>();

        //dummy recipes
        Recipe chicken = new Recipe("Chicken Gallina");
        chicken.setTime("30 min");
        Recipe pork = new Recipe("Pork Suilla");
        pork.setTime("45 min");
        Recipe beef = new Recipe("Beef Vacca");
        beef.setTime("60 min");
        recipeList.add(chicken);
        recipeList.add(pork);
        recipeList.add(beef);

        recipeBookRecyclerView = (RecyclerView) findViewById(R.id.recycler_recipe_book);
        recipeBookLinearLayoutManager = new LinearLayoutManager(this);
        recipeBookRecyclerView.setLayoutManager(recipeBookLinearLayoutManager);
        recipeBookAdapter = new RecipeBookLayoutAdapter(recipeList, this);
        recipeBookRecyclerView.setAdapter(recipeBookAdapter);

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
