package com.rankin.adam.cookingmaster.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.activity.ViewRecipeActivity;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.model.Recipe;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;


public class RecipeBookLayoutAdapter extends RecyclerView.Adapter<RecipeBookLayoutAdapter.ViewHolder>{

    private ArrayList<Recipe> recipeList;
    private Context recipeBookContext;

    public RecipeBookLayoutAdapter(ArrayList<Recipe> recipeList, Context context) {
        this.recipeList = new ArrayList<>();
        this.recipeList.addAll(recipeList);
        this.recipeBookContext = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView recipeName;
        private ImageView recipeImage;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            recipeName = itemView.findViewById(R.id.recipe_row_name);
            recipeImage = itemView.findViewById(R.id.recipe_book_row_image);
        }

        @Override
        public void onClick(View view) {
            Recipe recipe = recipeList.get(getAdapterPosition());
            recipeController.setCurrentRecipe(recipe);
            Intent viewRecipeIntent = new Intent(recipeBookContext, ViewRecipeActivity.class);
            recipeBookContext.startActivity(viewRecipeIntent);
        }
    }

    @Override
    public RecipeBookLayoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .recipe_book_row_layout, parent, false);
        return new RecipeBookLayoutAdapter.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecipeBookLayoutAdapter.ViewHolder holder, final int position) {
        final Recipe recipe = recipeList.get(position);

        String name = recipe.getName();
        holder.recipeName.setText(name);

        try {
            String recipeUriString = recipeController.getRecipesList().get(position).getImageUri();
            Uri uri = Uri.parse(recipeUriString);
            holder.recipeImage.setImageURI(uri);
        }

        catch (NullPointerException exception){
            //no image to display
        }
    }


    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void addRecipe(Recipe recipe){
        recipeList.add(recipe);
    }

    public void removeIngredient(int position) {
        recipeList.remove(position);
    }

    public void setRecipeList(ArrayList<Recipe> recipeList){
        this.recipeList = recipeList;
    }
}
