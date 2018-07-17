package com.rankin.adam.cookingmaster.Model;

import java.util.ArrayList;

/**
 * Created by Adam on 15-Jul-18.
 */

public class ShoppingList {

    private ArrayList<ShoppingListEntry> shoppingList;


    //TODO edit method to use amount and unit as well

    public ShoppingList() {
        this.shoppingList = new ArrayList<>();
    }

    public ArrayList<ShoppingListEntry> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ArrayList<ShoppingListEntry> ingredientsList) {
        this.shoppingList = ingredientsList;
    }

    public void addEntry(ShoppingListEntry shoppingListEntry){
        this.shoppingList.add(shoppingListEntry);
    }

    public void removeEntry(int position){
        this.shoppingList.remove(position);
    }

    public void clearShoppingList(){
        this.shoppingList.clear();
    }
}
