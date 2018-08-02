package com.rankin.adam.cookingmaster.model;

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

    public void deleteRecipe(Recipe recipe){
        recipeList.remove(recipe);
    }

    public Recipe getRecipe(int position){
        return recipeList.get(position);
    }

    public int getSize(){
        return recipeList.size();
    }

    public ArrayList<Recipe> getRecipeList(){
        return recipeList;
    }

    public void setRecipeList(ArrayList<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
