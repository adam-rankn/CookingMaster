package com.rankin.adam.cookingmaster.Controllers;

import com.rankin.adam.cookingmaster.Model.Ingredient;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.shoppingList;

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

    public void addIngredient(Ingredient ingredient){
        shoppingList.addIngredient(ingredient);
    }

    public void removeIngredient(int position){
        shoppingList.removeIngredient(position);
    }

    public ArrayList<Ingredient> getShoppingList(){
        return shoppingList.getShoppingList();
    }

    public void setShoppingList(ArrayList<Ingredient> list){
        shoppingList.setShoppingList(list);
    }

}
