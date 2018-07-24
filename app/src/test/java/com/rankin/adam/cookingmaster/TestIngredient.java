package com.rankin.adam.cookingmaster;

import com.rankin.adam.cookingmaster.model.Ingredient;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Adam on 16-Jul-18.
 */

public class TestIngredient {

    Ingredient ingredient = new Ingredient("milk");

    public TestIngredient(){
    }

    @Test
    public void testIngredientEquals(){
        Ingredient ingredient2 = new Ingredient("milk");
        assertEquals(ingredient,ingredient2);

        Ingredient ingredient3 = new Ingredient("malk");
        assertNotEquals(ingredient,ingredient3);

        Ingredient ingredient4 = new Ingredient("cheese");
        ingredient4.setName("milk");
        assertEquals(ingredient,ingredient4);
    }
}
