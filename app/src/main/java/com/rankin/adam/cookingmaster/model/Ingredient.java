package com.rankin.adam.cookingmaster.model;

import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return name.equals(that.name);
    }

}
