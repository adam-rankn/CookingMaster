package com.rankin.adam.cookingmaster.controller;

import com.rankin.adam.cookingmaster.model.Ingredient;
import com.rankin.adam.cookingmaster.model.Recipe;
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeImportController {

    private Recipe recipe;
    private URL recipeURL;

    public RecipeImportController(String stringURL) {
        try {
            recipeURL = new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        recipe = new Recipe("imported recipe");
        recipe.setInstructions("");
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                 try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(recipeURL.openStream()));
                    String input;
                    StringBuilder stringBuffer = new StringBuilder();
                    while ((input = in.readLine()) != null) {
                        stringBuffer.append(input);
                    }
                    in.close();
                    String htmlData = stringBuffer.toString();
                    List<String> dataList;
                    dataList = Arrays.asList(htmlData.split("[<>]"));
                    setIngredients(dataList);
                    setRecipeName(recipeURL.toString());


                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        try {
            thread.join(0);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public void setRecipeName(String url){
        String [] tokens = url.split("/");
        String name = tokens[tokens.length-1];
        name = name.replace("-"," ");
        recipe.setName(name);
    }

    public void setRecipeInstructions(String instructions){
        recipe.setInstructions(instructions);
    }

    public void addInstructions(String newlines){
        String old = recipe.getInstructions();
        old += newlines;
        recipe.setInstructions(old);


    }

    public String getInstructions(){
        return recipe.getInstructions();
    }

    public Recipe getRecipe(){
        return recipe;
    }

    public void setTime(int time){
        recipe.setTime(time);
    }

    public int getTime(){
        return recipe.getTime();
    }

    public void setIngredients(List<String> data){
        int i;
        for (i = 0; i < data.size(); i++) {
            String token = (String) data.get(i);

            if (token.contains("data-ingredient=")) {
                List<String> list = Arrays.asList(token.split("\""));

                String ingredientName = "imported recipe";
                String unit = "lbs";
                String amount = "0";
                for (int j = 0; j < list.size() - 1; j++) {
                    if (list.get(j).equals(" data-ingredient=")) {
                        ingredientName = list.get(j + 1);

                    } else if (list.get(j).equals(" data-unit=")) {
                        unit = list.get(j + 1);
                    } else if (list.get(j).equals(" data-init-quantity=")) {
                        amount = list.get(j + 1);
                    }

                }
                Ingredient ingredient = new Ingredient(ingredientName);
                Float f = (float) 3.0;
                try {
                    f = Float.parseFloat(amount);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                RecipeIngredientEntry recipeIngredientEntry = new RecipeIngredientEntry(ingredient, f, unit);
                recipe.addIngredient(recipeIngredientEntry);
            }
            else if (token.contains("section-body elementFont__body--paragraphWithin elementFont__body--linkWithin")) {
                String instructions = data.get(i+5).toString();
                instructions += "\n";
                addInstructions(instructions);
            }

            else if (token.contains("total:")) {
                String timeString = data.get(i+4);
                int time = parseTime(timeString);
                setTime(time);
            }


        }
    }

    public ArrayList<RecipeIngredientEntry> getIngredients(){
        return recipe.getIngredientList();
    }

    public int parseTime(String timeString){
        int time = 0;
        List<String> tokens = Arrays.asList(timeString.split(" "));
        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (token.contains("hr") | token.contains("hour")){
                time += (Integer.parseInt(tokens.get(i-1))) * 60;
            }
            if (token.contains("min")){
                time += Integer.parseInt(tokens.get(i-1));
            }
        }
        return time;
    }


}
