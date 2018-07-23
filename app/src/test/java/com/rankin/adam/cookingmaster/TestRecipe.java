package com.rankin.adam.cookingmaster;

import com.rankin.adam.cookingmaster.model.Ingredient;
import com.rankin.adam.cookingmaster.model.Recipe;
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adam on 16-Jul-18.
 */

public class TestRecipe {

    private Recipe recipe = new Recipe("pie");
    RecipeIngredientEntry ingredientEntry = new RecipeIngredientEntry(new Ingredient("flour"),1,"cup");


    public TestRecipe(){
        recipe.addIngredient(ingredientEntry);
        recipe.setTime("15");
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

}
