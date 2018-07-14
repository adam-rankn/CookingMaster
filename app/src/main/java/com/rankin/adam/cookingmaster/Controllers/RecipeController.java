package com.rankin.adam.cookingmaster.Controllers;

import com.rankin.adam.cookingmaster.Model.Recipe;

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

    public void viewRecipe(int position){
        this.position = position;
        recipe = recipeList.getRecipe(position);
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

    public void viewHabit(int position){
        recipe = recipeList.getRecipe(position);
        this.position = position;
    }

    public void clearRecipes(){
        //recipeList.deleteAll();
    }

    public void saveRecipes(){
        //save recipe list
        //save recipe list

    }
}
