package com.rankin.adam.cookingmaster.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.rankin.adam.cookingmaster.activity.dialog.SearchRecipesDialog;
import com.rankin.adam.cookingmaster.adapter.RecipeBookLayoutAdapter;
import com.rankin.adam.cookingmaster.controller.SaveLoadController;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.model.Ingredient;
import com.rankin.adam.cookingmaster.model.Recipe;

import java.util.ArrayList;
import java.util.Collections;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;
import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeList;

public class RecipeBookActivity extends AppCompatActivity {

    private RecyclerView recipeBookRecyclerView;
    private LinearLayoutManager recipeBookLinearLayoutManager;
    public RecipeBookLayoutAdapter recipeBookAdapter;

    private int ADD_RECIPE_REQUEST = 0;

    private ArrayList<Recipe> adapterList = new ArrayList<>();

    private SaveLoadController saveLoadController = new SaveLoadController(RecipeBookActivity.this);

    private Boolean isFiltered = Boolean.FALSE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

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

        Button searchRecipesButton = findViewById(R.id.recipeBookAct_btn_search);
        searchRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchRecipesDialog searchDialog = new SearchRecipesDialog(RecipeBookActivity.this);
                searchDialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ADD_RECIPE_REQUEST){
            recreate();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        saveLoadController.saveRecipesToFile();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        invalidateOptionsMenu();
        if (recipeController.getDeletedFlag()){
            recreate();
            recipeController.setDeletedFlag(Boolean.FALSE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void filterRecipeList(Integer maxTime, ArrayList<Ingredient> ingredients, ArrayList<String> allergens){
        ArrayList<Recipe> recipes = recipeController.getRecipesList();
        ArrayList<Recipe> filteredList = new ArrayList<>();

        Boolean filterAllergens = Boolean.FALSE;
        Boolean filterIngredients = Boolean.FALSE;
        Boolean filterTime = Boolean.FALSE;

        Boolean addRecipe;

        if (!allergens.isEmpty()){
            filterAllergens = Boolean.TRUE;
        }
        if (!ingredients.isEmpty()){
            filterIngredients = Boolean.TRUE;
        }
        if (maxTime > 0){
            filterTime = Boolean.TRUE;
        }

        //build the filtered list
        for (Recipe recipe : recipes) {
            addRecipe = Boolean.TRUE;
            if (filterTime){
                if (recipe.getTime() > maxTime){
                    addRecipe = Boolean.FALSE;
                }
            }
            if (addRecipe && filterAllergens){
                if (!Collections.disjoint(allergens,recipe.getAllergens())){
                    addRecipe = Boolean.FALSE;
                }
            }
            if (addRecipe && filterIngredients){
                for (Ingredient ingr : ingredients){
                    if (!recipe.containsIngredient(ingr)){
                        addRecipe = Boolean.FALSE;
                    }
                }
            }

            if (addRecipe){
                filteredList.add(recipe);
            }
        }
        isFiltered = Boolean.TRUE;

        recipeBookAdapter.setRecipeList(filteredList);
        //refresh adapter
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                recipeBookAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (isFiltered){
            recipeBookAdapter.setRecipeList(adapterList);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    recipeBookAdapter.notifyDataSetChanged();
                }
            });
            isFiltered = Boolean.FALSE;
        }

        else{
            super.onBackPressed();
        }

    }
}
