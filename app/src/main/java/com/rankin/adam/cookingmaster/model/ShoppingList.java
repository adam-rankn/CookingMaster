package com.rankin.adam.cookingmaster.model;

import java.util.ArrayList;

/**
 * Created by Adam on 15-Jul-18.
 */

public class ShoppingList {

    private ArrayList<ShoppingListEntry> shoppingList;

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

    public ShoppingListEntry findIngredient(Ingredient ingredient){
        //TODO optimize
        for (ShoppingListEntry element : shoppingList) {
            if (element.getIngredient().getName().equals(ingredient.getName())){
                return element;
            }
        }
        return null;

    }

    public void increaseAmount(int position,int amount){
        shoppingList.get(position).addAmount(amount);

    }
}
