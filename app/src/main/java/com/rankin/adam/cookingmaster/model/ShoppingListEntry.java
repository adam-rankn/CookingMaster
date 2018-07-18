package com.rankin.adam.cookingmaster.model;

/**
 * Created by Adam on 16-Jul-18.
 */

public class ShoppingListEntry {

    private Ingredient ingredient;
    private Integer amount;
    private String unit;

    public ShoppingListEntry(Ingredient ingredient, Integer amount, String unit) {
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName(){
        return this.ingredient.getName();
    }

}
