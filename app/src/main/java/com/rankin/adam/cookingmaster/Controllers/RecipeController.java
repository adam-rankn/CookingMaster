package com.rankin.adam.cookingmaster.Controllers;

import android.graphics.Bitmap;

import com.rankin.adam.cookingmaster.Model.Ingredient;
import com.rankin.adam.cookingmaster.Model.Recipe;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.recipeList;

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

    public Bitmap getThumbnail(){
        return recipe.getThumbnail();
    }

    public void setThumbnail(Bitmap thumbnail){
        recipe.setThumbnail(thumbnail);
    }

    public void setIngredients(ArrayList<Ingredient> ingredients){
        recipe.setIngredientList(ingredients);
    }

    public ArrayList<Ingredient> getIngredients(){
        return recipe.getIngredientList();
    }

    public void addIngredient(Ingredient ingredient){
        recipe.addIngredient(ingredient);
    }

    public void removeIngredient(int position){
        recipe.removeIngredient(position);
    }

    public void clearRecipes(){
        //recipeList.deleteAll();
    }

    public void saveRecipes(){
        //save recipe list
        //TODO add saving
    }

}
