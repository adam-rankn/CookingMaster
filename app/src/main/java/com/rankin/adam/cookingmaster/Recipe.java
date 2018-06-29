package com.rankin.adam.cookingmaster;

import java.util.ArrayList;

/**
 * Created by Adam on 26-Jun-18.
 */

public class Recipe {

    private String Name;
    private String Time;
    private ArrayList<Ingredient> IngredientList;


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
}
