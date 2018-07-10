package com.rankin.adam.cookingmaster;

import java.util.ArrayList;

/**
 * Created by Adam on 26-Jun-18.
 */

public class Ingredient {

    private String name;
    private ArrayList<String> allergens;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
