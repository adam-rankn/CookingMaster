package com.rankin.adam.cookingmaster.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Adam on 14-Jul-18.
 */

public class RecipeList {

    private ArrayList<Recipe> recipeList;

    public RecipeList() {
        this.recipeList = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe){
        recipeList.add(recipe);
    }

    public void editRecipe(int position, Recipe recipe){
        recipeList.set(position, recipe);
    }

    public void deleteRecipe(int position){
        recipeList.remove(position);
    }

    public Recipe getRecipe(int position){
        Recipe recipe = recipeList.get(position);
        return recipe;
    }

    public int getSize(){
        return recipeList.size();
    }

    public ArrayList<Recipe> getRecipeList(){
        return recipeList;
    }
}