package com.rankin.adam.cookingmaster.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.Activities.ViewRecipeActivity;
import com.rankin.adam.cookingmaster.Model.Ingredient;
import com.rankin.adam.cookingmaster.Model.Recipe;
import com.rankin.adam.cookingmaster.R;

import java.util.ArrayList;
import java.util.List;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.recipeController;

/**
 * Created by Adam on 15-Jul-18.
 */

public class IngredientViewLayoutAdapter extends RecyclerView.Adapter<IngredientViewLayoutAdapter.ViewHolder> {

    private ArrayList<Ingredient> ingredientList;


    public IngredientViewLayoutAdapter(ArrayList<Ingredient> ingredientList, Context context) {
        this.ingredientList = new ArrayList<>();
        this.ingredientList.addAll(ingredientList);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ingredientName;
        private Button addToShoppingListButton;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            addToShoppingListButton = itemView.findViewById(R.id.ingrViewRowLay_btn_add_to_shop);
            ingredientName = itemView.findViewById(R.id.ingrViewRowLay_ingredient_name);

        }
        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public IngredientViewLayoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .ingredient_view_row_layout, parent, false);
        return new IngredientViewLayoutAdapter.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(IngredientViewLayoutAdapter.ViewHolder holder, final int position) {
        final Ingredient ingredient = ingredientList.get(position);

        String ingredientName = ingredient.getName();
        holder.ingredientName.setText(ingredientName);

        holder.addToShoppingListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add this ingredient to shopping cart
            }
        });
    }




    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

}