package com.rankin.adam.cookingmaster.model;

import android.graphics.Bitmap;
import android.net.Uri;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by Adam on 26-Jun-18.
 */

public class Recipe {

    private String Name;
    private Integer Time;
    private ArrayList<RecipeIngredientEntry> ingredientList;
    private String Instructions;
    private ArrayList<String> allergens;
    private String imageUri;


    public Recipe(String name) {
        Name = name;
        ingredientList = new ArrayList<>();
        allergens = new ArrayList<>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getTime() {
        return Time;
    }

    public void setTime(Integer time) {
        Time = time;
    }

    public ArrayList<RecipeIngredientEntry> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<RecipeIngredientEntry> recipeIngredientEntries) {
        ingredientList = ingredientList;
    }

    public void addIngredient(RecipeIngredientEntry recipeIngredientEntry){
        ingredientList.add(recipeIngredientEntry);
    }

    public void removeIngredient(int position){
        ingredientList.remove(position);
    }

    public String getInstructions() {
        return Instructions;
    }

    public void setInstructions(String instructions) {
        Instructions = instructions;
    }

    public ArrayList<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(ArrayList<String> allergens) {
        this.allergens = allergens;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Boolean containsIngredient(Ingredient ingredient){
        for (RecipeIngredientEntry entry : ingredientList) {
            if (ingredient.getName().equals(entry.getIngredient().getName())){
                return Boolean.TRUE;
            }
        }
        return
                Boolean.FALSE;

    }
}
