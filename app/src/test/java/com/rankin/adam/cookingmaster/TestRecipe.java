package com.rankin.adam.cookingmaster;

import com.rankin.adam.cookingmaster.Model.Ingredient;
import com.rankin.adam.cookingmaster.Model.Recipe;

import org.junit.Test;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Adam on 16-Jul-18.
 */

public class TestRecipe {

    private Recipe recipe = new Recipe("pie");


    public TestRecipe(){
        recipe.addIngredient(new Ingredient("flour"));
        recipe.setTime("15");
    }

    @Test
    public void addIngredient(){
        assertEquals(1,recipe.getIngredientList().size());
        recipe.addIngredient(new Ingredient("oil"));
        assertEquals(2,recipe.getIngredientList().size());
    }

    @Test public void deleteIngredient(){
        assertEquals(1,recipe.getIngredientList().size());
        recipe.removeIngredient(0);
        assertEquals(0,recipe.getIngredientList().size());
    }

}