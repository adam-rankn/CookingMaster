package com.rankin.adam.cookingmaster;

import com.rankin.adam.cookingmaster.controller.SaveLoadController;
import com.rankin.adam.cookingmaster.model.Ingredient;
import com.rankin.adam.cookingmaster.model.Recipe;
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

/**
 * Created by Adam on 16-Jul-18.
 */

public class TestRecipe {

    private Recipe recipe = new Recipe("pie");
    RecipeIngredientEntry ingredientEntry = new RecipeIngredientEntry(new Ingredient("flour"), (float)1.0,"cup");

    public TestRecipe(){
        recipe.addIngredient(ingredientEntry);
        recipe.setTime(15);
    }

    @Test
    public void addIngredient(){
        assertEquals(1,recipe.getIngredientList().size());
        recipe.addIngredient(ingredientEntry);
        assertEquals(2,recipe.getIngredientList().size());
    }

    @Test public void deleteIngredient(){
        assertEquals(1,recipe.getIngredientList().size());
        recipe.removeIngredient(0);
        assertEquals(0,recipe.getIngredientList().size());
    }

    @Test public void containIngredient(){
        Ingredient ingredient = new Ingredient("flour");
        assertTrue(recipe.containsIngredient(ingredient));
    }

    @Test
    public void getSetTime(){
        assertEquals(15, (int)recipe.getTime());
        recipe.setTime(35);
        assertEquals(35, (int)recipe.getTime());
        recipe.setTime(0);
        assertEquals(0, (int)recipe.getTime());
    }

}