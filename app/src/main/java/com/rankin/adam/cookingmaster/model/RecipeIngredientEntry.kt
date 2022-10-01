package com.rankin.adam.cookingmaster.model;

/**
 * Created by Adam on 18-Jul-18.
 */

public class RecipeIngredientEntry {

    private Ingredient ingredient;
    private Float amount;
    private String unit;


    public RecipeIngredientEntry(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public RecipeIngredientEntry(Ingredient ingredient, Float amount, String unit) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIngredientName(){
        return this.ingredient.getName();
    }
}
