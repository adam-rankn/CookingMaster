package com.rankin.adam.cookingmaster.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Adam on 26-Jun-18.
 */

public class Recipe {

    private String Name;
    private String Time;
    private ArrayList<Ingredient> ingredientList;
    private String Instructions;
    private ArrayList<String> allergens;
    private Bitmap thumbnail;


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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        ingredientList = ingredientList;
    }

    public void addIngredient(Ingredient ingredient){
        ingredientList.add(ingredient);
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

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }
}
