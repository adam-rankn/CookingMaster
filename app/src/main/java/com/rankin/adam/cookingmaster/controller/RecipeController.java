package com.rankin.adam.cookingmaster.controller;

import android.graphics.Bitmap;
import android.net.Uri;

import com.rankin.adam.cookingmaster.model.Ingredient;
import com.rankin.adam.cookingmaster.model.Recipe;
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry;

import java.net.URI;
import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeList;

/**
 * Created by Adam on 14-Jul-18.
 */

public class RecipeController {

    private Recipe recipe;
    private int position;

    public RecipeController(){
    }

    public void addRecipe(Recipe recipe){
        recipeList.addRecipe(recipe);
    }

    public Recipe getCurrentRecipe(){
        return recipe;
    }

    public void setCurrentRecipe(int position){
        this.position = position;
        recipe = recipeList.getRecipe(position);
    }

    public void editCurrentRecipe(){
        recipeList.editRecipe(position,recipe);
    }

    public void deleteCurrentRecipe(){
        recipeList.deleteRecipe(position);
    }

    public void setCurrentRecipe(Recipe recipe){
        this.recipe=recipe;
    }

    public String getName(){
        return recipe.getName();
    }

    public void setName(String name){
        recipe.setName(name);
    }

    public String getTime(){
        return recipe.getTime();
    }

    public void setTime(String time){
        recipe.setTime(time);
    }

    public String getInstructions(){
        return recipe.getInstructions();
    }

    public void setInstructions(String instructions){
        recipe.setInstructions(instructions);
    }

    public String getImageUri(){
        return recipe.getImageUri();
    }

    public void setImageUri(String imageUri){
        recipe.setImageUri(imageUri);
    }

    public void setIngredients(ArrayList<RecipeIngredientEntry> recipeIngredientEntries){
        recipe.setIngredientList(recipeIngredientEntries);
    }

    public ArrayList<RecipeIngredientEntry> getIngredients(){
        return recipe.getIngredientList();
    }

    public void setAllergens(ArrayList<String> recipeAllergens){
        recipe.setAllergens(recipeAllergens);
    }

    public ArrayList<String> getAllergens(){
        return recipe.getAllergens();
    }


    public void addIngredient(RecipeIngredientEntry recipeIngredientEntry){
        recipe.addIngredient(recipeIngredientEntry);
    }

    public void removeIngredient(int position){
        recipe.removeIngredient(position);
    }

    public void clearRecipes(){
        //recipeList.deleteAll();
    }

}
