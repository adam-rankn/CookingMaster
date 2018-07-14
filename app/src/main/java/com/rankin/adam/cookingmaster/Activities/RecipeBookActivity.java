package com.rankin.adam.cookingmaster.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.rankin.adam.cookingmaster.Model.RecipeList;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.Model.Recipe;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.recipeController;
import static com.rankin.adam.cookingmaster.Activities.MainActivity.recipeList;

public class RecipeBookActivity extends AppCompatActivity {

    private RecyclerView recipeBookRecyclerView;
    private LinearLayoutManager recipeBookLinearLayoutManager;
    private RecipeBookLayoutAdapter recipeBookAdapter;

    private int ADD_RECIPE_REQUEST = 0;

    private ArrayList<Recipe> adapterList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);

        //dummy recipes
        Recipe chicken = new Recipe("Chicken Gallina");
        chicken.setTime("30 min");
        Recipe pork = new Recipe("Pork Suilla");
        pork.setTime("45 min");
        Recipe beef = new Recipe("Beef Vacca");
        beef.setTime("60 min");

        adapterList.add(chicken);
        adapterList.add(pork);
        adapterList.add(beef);

        adapterList.addAll(recipeList.getRecipeList());

        recipeBookRecyclerView = findViewById(R.id.recycler_recipe_book);
        recipeBookLinearLayoutManager = new LinearLayoutManager(this);
        recipeBookRecyclerView.setLayoutManager(recipeBookLinearLayoutManager);
        recipeBookAdapter = new RecipeBookLayoutAdapter(adapterList, this);
        recipeBookRecyclerView.setAdapter(recipeBookAdapter);

        Button returnToMainButton = findViewById(R.id.btn_main);
        returnToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button addRecipeButton = findViewById(R.id.btn_add_recipe);
        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRecipeIntent = new Intent(RecipeBookActivity.this, AddRecipeActivity.class);
                startActivityForResult(addRecipeIntent,ADD_RECIPE_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ADD_RECIPE_REQUEST){
            if (resultCode == RESULT_OK){
                //recipeList.sort;
                adapterList.clear();
                adapterList.addAll(recipeList.getRecipeList());
                recipeBookAdapter.notifyDataSetChanged();


            }
        }

    }

    @Override
    public void onPause(){
        super.onPause();
        recipeController.saveRecipes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterList.clear();
        adapterList.addAll(recipeList.getRecipeList());
        recipeBookAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        invalidateOptionsMenu();

        recipeBookRecyclerView = (RecyclerView) findViewById(R.id.recycler_recipe_book);
        recipeBookLinearLayoutManager = new LinearLayoutManager(this);
        recipeBookRecyclerView.setLayoutManager(recipeBookLinearLayoutManager);
        adapterList.clear();
        adapterList.addAll(recipeList.getRecipeList());
        recipeBookAdapter = new RecipeBookLayoutAdapter(adapterList, this);
        recipeBookRecyclerView.setAdapter(recipeBookAdapter);
        recipeBookAdapter.notifyDataSetChanged();
    }


}
