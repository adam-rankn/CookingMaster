package com.rankin.adam.cookingmaster;

import com.rankin.adam.cookingmaster.controller.ShoppingListController;
import com.rankin.adam.cookingmaster.model.Ingredient;
import com.rankin.adam.cookingmaster.model.ShoppingList;
import com.rankin.adam.cookingmaster.model.ShoppingListEntry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adam on 16-Jul-18.
 */

public class TestShoppingList {

    ShoppingList shoppingList = new ShoppingList();
    ShoppingListController shoppingListController = new ShoppingListController();

    Ingredient milk = new Ingredient("milk");
    ShoppingListEntry milkEntry = new ShoppingListEntry(milk,4,"gallons");

    Ingredient ingredient = new Ingredient("flour");
    ShoppingListEntry entry = new ShoppingListEntry(ingredient,1,"lb");

    public TestShoppingList() {}

    @Test
    public void testShoppingListAdd(){
        assertEquals(0,shoppingList.getShoppingList().size());
        shoppingList.addEntry(entry);
        assertEquals(1,shoppingList.getShoppingList().size());
        shoppingListController.clearShoppingList();

    }

    @Test
    public void testIngredientMerging(){
        shoppingListController.addIngredient(entry);
        Ingredient moreFlour = new Ingredient("flour");
        ShoppingListEntry entry2 = new ShoppingListEntry(moreFlour,1,"lb");
        shoppingListController.addIngredient(entry2);
        ShoppingListEntry combinedEntry = shoppingListController.getShoppingList().get(0);
        assertEquals((Integer)2,(Integer)combinedEntry.getAmount());
        shoppingListController.clearShoppingList();
    }

    @Test
    public void testFindIngredient(){
        shoppingListController.addIngredient(entry);
        ShoppingListEntry test = shoppingListController.findIngredient(ingredient);
        assertEquals("flour",test.getName());
        shoppingListController.clearShoppingList();
    }

    @Test
    public void testRemoveIngredient(){
        shoppingListController.addIngredient(entry);
        shoppingListController.addIngredient(milkEntry);
        shoppingListController.removeEntry(0);
        assertEquals("milk",shoppingListController.getShoppingList().get(0).getName());
        shoppingListController.clearShoppingList();
    }

    @Test
    public void testIncreaseIngredient(){
        shoppingList.clearShoppingList();
        shoppingList.addEntry(entry);
        shoppingList.increaseAmount(0,100);
        assertEquals((Integer)101,(Integer)shoppingList.getShoppingList().get(0).getAmount());

    }




}
