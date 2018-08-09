package com.rankin.adam.cookingmaster.Fragments;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rankin.adam.cookingmaster.activity.AddRecipeActivity;
import com.rankin.adam.cookingmaster.dialog.SearchRecipesDialog;
import com.rankin.adam.cookingmaster.adapter.RecipeBookLayoutAdapter;
import com.rankin.adam.cookingmaster.controller.SaveLoadController;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.model.Ingredient;
import com.rankin.adam.cookingmaster.model.Recipe;

import java.util.ArrayList;
import java.util.Collections;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;
import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeList;

public class RecipeBookFragment extends Fragment {

    private RecyclerView recipeBookRecyclerView;
    private LinearLayoutManager recipeBookLinearLayoutManager;
    public RecipeBookLayoutAdapter recipeBookAdapter;

    private int ADD_RECIPE_REQUEST = 0;

    private ArrayList<Recipe> adapterList = new ArrayList<>();

    private SaveLoadController saveLoadController = new SaveLoadController(getActivity());

    private Boolean isFiltered = Boolean.FALSE;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterList.addAll(recipeList.getRecipeList());
        if (getArguments() != null) {
            //name = getArguments().getString(KEY_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_book, container, false);

        recipeBookRecyclerView = view.findViewById(R.id.recycler_recipe_book);
        recipeBookLinearLayoutManager = new LinearLayoutManager(getContext());
        recipeBookRecyclerView.setLayoutManager(recipeBookLinearLayoutManager);
        recipeBookAdapter = new RecipeBookLayoutAdapter(adapterList, getContext());
        recipeBookRecyclerView.setAdapter(recipeBookAdapter);

        Button returnToMainButton = view.findViewById(R.id.btn_main);
        returnToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
            }
        });

        Button addRecipeButton = view.findViewById(R.id.btn_add_recipe);
        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRecipeIntent = new Intent(getContext(), AddRecipeActivity.class);
                startActivityForResult(addRecipeIntent,ADD_RECIPE_REQUEST);
            }
        });

        Button searchRecipesButton = view.findViewById(R.id.recipeBookAct_btn_search);
        searchRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SearchRecipesDialog searchDialog = new SearchRecipesDialog(RecipeBookFragment.this));
                //searchDialog.show();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ADD_RECIPE_REQUEST){
            //recreate();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        saveLoadController.saveRecipesToFile();
    }

    @Override
    public void onResume() {
        super.onResume();
        //invalidateOptionsMenu();
        if (recipeController.getDeletedFlag()){
            //recreate();
            recipeController.setDeletedFlag(Boolean.FALSE);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
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

}
