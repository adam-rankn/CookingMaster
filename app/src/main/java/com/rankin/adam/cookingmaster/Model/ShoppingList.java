package com.rankin.adam.cookingmaster.Model;

import java.util.ArrayList;

/**
 * Created by Adam on 15-Jul-18.
 */

public class ShoppingList {

    private ArrayList<Ingredient> shoppingList;

    public ShoppingList() {
        this.shoppingList = new ArrayList<>();

    }

    public ArrayList<Ingredient> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ArrayList<Ingredient> ingredientsList) {
        this.shoppingList = ingredientsList;
    }

    public void addIngredient(Ingredient ingredient){
        this.shoppingList.add(ingredient);
    }

    public void removeIngredident(int position){
        this.shoppingList.remove(position);
    }

    public void clearShoppingList(){
        this.shoppingList.clear();
    }
}
