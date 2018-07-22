package com.rankin.adam.cookingmaster;

import com.rankin.adam.cookingmaster.model.Ingredient;
import com.rankin.adam.cookingmaster.model.ShoppingList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adam on 16-Jul-18.
 */

public class TestShoppingList {

    ShoppingList shoppingList = new ShoppingList();
    Ingredient ingredient = new Ingredient("flour");

    public TestShoppingList() {}

    @Test
    public void testShoppingListArray(){
        assertEquals(0,shoppingList.getShoppingList().size());
        shoppingList.addIngredient(ingredient);
        assertEquals(1,shoppingList.getShoppingList().size());
    }


}
