package com.rankin.adam.cookingmaster.controller;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rankin.adam.cookingmaster.model.Recipe;
import com.rankin.adam.cookingmaster.model.ShoppingListEntry;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeList;
import static com.rankin.adam.cookingmaster.activity.MainActivity.shoppingList;

/**
 * Created by Adam on 17-Jul-18.
 */

public class SaveLoadController {

    private Context context;

    public SaveLoadController(Context context) {
        this.context = context;
    }

    public void loadRecipesFromFile() {
        try {
            FileInputStream fis = context.openFileInput("Recipes");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Recipe>>() {}.getType();
            ArrayList<Recipe> recipeLoadList = gson.fromJson(in, listType);
            recipeList.setRecipeList(recipeLoadList);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block

        }
    }

    public void saveRecipesToFile() {
        try {
            FileOutputStream fos = context.openFileOutput("Recipes",
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(recipeList.getRecipeList(),writer);
            writer.flush();
            fos.close();
        }  catch (IOException e) {
            Log.e("SaveError","recipes failed to save");
            throw new RuntimeException();
        }
    }

    public void loadShoppingListFromFile() {
        try {
            FileInputStream fis = context.openFileInput("Shopping List");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<ShoppingListEntry>>() {}.getType();
            ArrayList<ShoppingListEntry> shoppingLoadList = gson.fromJson(in, listType);
            shoppingList.setShoppingList(shoppingLoadList);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block

        }
    }

    public void saveShoppingListToFile() {
        try {
            FileOutputStream fos = context.openFileOutput("Shopping List",
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(shoppingList.getShoppingList(),writer);
            writer.flush();
            fos.close();
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
