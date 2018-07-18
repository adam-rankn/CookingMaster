package com.rankin.adam.cookingmaster.Controllers;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rankin.adam.cookingmaster.Model.Ingredient;
import com.rankin.adam.cookingmaster.Model.Recipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_WORLD_READABLE;
import static android.provider.Telephony.Mms.Part.FILENAME;
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

}
