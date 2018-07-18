package com.rankin.adam.cookingmaster.controller;

import com.rankin.adam.cookingmaster.model.Ingredient;
import com.rankin.adam.cookingmaster.model.ShoppingListEntry;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.shoppingList;

/**
 * Created by Adam on 15-Jul-18.
 */

public class ShoppingListController {

    private Ingredient ingredient;
    private int position;

    public ShoppingListController() {
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void addEntry(Ingredient ingredient, Integer amount, String unit){
        ShoppingListEntry shoppingListEntry = new ShoppingListEntry(ingredient,amount,unit);
        shoppingList.addEntry(shoppingListEntry);

    }

    public void removeEntry(int position){
        shoppingList.removeEntry(position);
    }

    public ArrayList<ShoppingListEntry> getShoppingList(){
        return shoppingList.getShoppingList();
    }

    public void setShoppingList(ArrayList<ShoppingListEntry> list){
        shoppingList.setShoppingList(list);
    }

    public void clearShoppingList(){
        shoppingList.clearShoppingList();
    }
}
