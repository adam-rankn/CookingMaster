package com.rankin.adam.cookingmaster.controller;

import com.rankin.adam.cookingmaster.model.Ingredient;
import com.rankin.adam.cookingmaster.model.ShoppingList;
import com.rankin.adam.cookingmaster.model.ShoppingListEntry;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.shoppingList;
import static com.rankin.adam.cookingmaster.activity.MainActivity.shoppingListController;

/**
 * Created by Adam on 15-Jul-18.
 */

public class ShoppingListController {

    private ShoppingListEntry shoppingListEntry;
    private int position;

    public ShoppingListController() {
    }

    public ShoppingListEntry getShoppingListEntry() {
        return shoppingListEntry;
    }

    public void setShoppingListEntry(ShoppingListEntry shoppingListEntry) {
        this.shoppingListEntry = shoppingListEntry;
    }

    public void addIngredient(ShoppingListEntry newEntry){


        //if there is an entry with the same ingredient type and unit, the add to to it
        if (findIngredient(newEntry.getIngredient()) != null){
            ShoppingListEntry existingEntry = findIngredient(newEntry.getIngredient());
            if (existingEntry.getUnit().equals(newEntry.getUnit())){
                int pos = shoppingList.getShoppingList().indexOf(existingEntry);
                shoppingList.increaseAmount(pos,newEntry.getAmount());
            }
            else{
                shoppingList.addEntry(newEntry);
            }
        }
        //otherwise, make a new entry.
        else {
            shoppingList.addEntry(newEntry);
        }

    }

    public Ingredient getCurrentIngredient(){
        return this.shoppingListEntry.getIngredient();
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

    public ShoppingListEntry findIngredient(Ingredient ingredient){
        if (shoppingList.findIngredient(ingredient) != null){
            return shoppingList.findIngredient(ingredient);
        }
        return null;
    }

}
