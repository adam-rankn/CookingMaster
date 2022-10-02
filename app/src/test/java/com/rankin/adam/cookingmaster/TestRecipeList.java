package com.rankin.adam.cookingmaster;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.rankin.adam.cookingmaster.model.Recipe;
import com.rankin.adam.cookingmaster.model.RecipeList;

public class TestRecipeList {

    RecipeList recipeList = new RecipeList();
    Recipe recipe1 = new Recipe("pie");
    Recipe recipe2 = new Recipe("cake");
    Recipe recipe3 = new Recipe("piecake");

    @Test
    public void addGetRecipe(){
        recipeList.addRecipe(recipe1);
        assertEquals(recipeList.getRecipe(0),recipe1);
    }

    @Test
    public void deleteRecipe(){
        recipeList.addRecipe(recipe2);
        recipeList.addRecipe(recipe1);
        recipeList.deleteRecipe(recipe1);
        recipeList.deleteRecipe(recipe2);

        assertEquals(0,recipeList.getSize());
    }

    @Test
    public void getSize(){
        assertEquals(0,recipeList.getSize());
        recipeList.addRecipe(recipe1);
        assertEquals(1,recipeList.getSize());
        recipeList.addRecipe(recipe2);
        assertEquals(2,recipeList.getSize());
    }

    @Test
    public void editRecipe(){
        recipeList.addRecipe(recipe1);
        recipeList.editRecipe(0,recipe2);
        assertEquals(recipeList.getRecipe(0),recipe2);
    }


}
