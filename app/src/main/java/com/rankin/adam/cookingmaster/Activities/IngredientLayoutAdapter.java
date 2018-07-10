package com.rankin.adam.cookingmaster.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.Ingredient;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.Recipe;

import java.util.ArrayList;

/**
 * Created by Adam on 10-Jul-18.
 */

public class IngredientLayoutAdapter extends RecyclerView.Adapter<IngredientLayoutAdapter.ViewHolder> {

    private ArrayList<Ingredient> ingredientList;


    public IngredientLayoutAdapter(ArrayList<Ingredient> ingredientList, Context context) {
        this.ingredientList = new ArrayList<>();
        this.ingredientList.addAll(ingredientList);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView recipeName;
        private ImageView recipeImage;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            recipeName = itemView.findViewById(R.id.recipe_row_name);
            //recipeImage = itemView.findViewById(R.id.recipe_row_image);
        }

        @Override
        public void onClick(View view) {


        }
    }

    @Override
    public IngredientLayoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .recipe_book_row_layout, parent, false);
        return new IngredientLayoutAdapter.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(IngredientLayoutAdapter.ViewHolder holder, final int position) {
        final Ingredient ingredient =ingredientList.get(position);
        //String name = Ingredient.getName();
        //holder.recipeName.setText(name);
    }


    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}


