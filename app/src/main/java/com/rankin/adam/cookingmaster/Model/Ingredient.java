package com.rankin.adam.cookingmaster.Model;

import java.util.ArrayList;

/**
 * Created by Adam on 26-Jun-18.
 */

public class Ingredient {

    private String name;
    private ArrayList<String> allergens;
    private Integer amount;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


}
