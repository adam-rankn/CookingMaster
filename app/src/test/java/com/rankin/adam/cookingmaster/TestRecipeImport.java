package com.rankin.adam.cookingmaster;

import com.rankin.adam.cookingmaster.controller.RecipeImportController;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestRecipeImport {

    RecipeImportController recipeImportController = new RecipeImportController("https://www.allrecipes.com/recipe/8453629/caprese-pasta-with-thai-basil/");


    public TestRecipeImport(){

    }

    @Test
    public void importTime(){

        assertEquals(recipeImportController.getTime(),30);
    }

    @Test
    public void importName(){

        assertEquals(recipeImportController.getRecipe().getName(),"caprese pasta with thai basil");
    }

    @Test
    public void importInstructions(){

        assertEquals(recipeImportController.getInstructions(),"Bring a large pot of lightly salted water to a boil. Cook spaghetti in the boiling water, stirring occasionally, until tender yet firm to the bite, about 12 minutes.\n" +
                "Combine cherry tomatoes, mozzarella balls, olive oil, balsamic vinegar, garlic, and Thai basil in a large mixing bowl; mix well. Season with salt and pepper to taste.\n" +
                "Drain pasta and top with cherry tomato mixture.\n");
    }
}
