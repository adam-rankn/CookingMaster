package com.rankin.adam.cookingmaster.Activities;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.Recipe;

import java.util.ArrayList;






public class RecipeBookLayoutAdapter extends RecyclerView.Adapter<RecipeBookLayoutAdapter.ViewHolder>{

    private ArrayList<Recipe> recipeList;


    public RecipeBookLayoutAdapter(ArrayList<Recipe> recipeList, Context context) {
        this.recipeList = new ArrayList<>();
        this.recipeList.addAll(recipeList);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView recipeName;
        private TextView recipeTime;
        private ImageView recipeImage;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipe_row_name);
            recipeTime = itemView.findViewById(R.id.recipe_row_time);

        }
        @Override
        public void onClick(View view) {

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
        final Recipe recipe =recipeList.get(position);
        String name = recipe.getName();
        String time = recipe.getTime();

        holder.recipeName.setText(name);
        holder.recipeTime.setText(time);

    }


    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
