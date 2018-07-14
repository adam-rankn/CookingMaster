package com.rankin.adam.cookingmaster.Model;

import com.rankin.adam.cookingmaster.Model.Ingredient;

import java.util.ArrayList;

/**
 * Created by Adam on 26-Jun-18.
 */

public class Recipe {

    private String Name;
    private String Time;
    private ArrayList<Ingredient> IngredientList;
    private String Instructions;
    private ArrayList<String> allergens;


    public Recipe(String name) {
        Name = name;
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
        return IngredientList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        IngredientList = ingredientList;
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
}
