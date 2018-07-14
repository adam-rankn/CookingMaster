package com.rankin.adam.cookingmaster.Activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.Model.Ingredient;
import com.rankin.adam.cookingmaster.R;

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
        private TextView ingredientName;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            ingredientName = itemView.findViewById(R.id.ingrRow_name);
        }

        @Override
        public void onClick(View view) {


        }
    }

    @Override
    public IngredientLayoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .ingredient_row_layout, parent, false);
        return new IngredientLayoutAdapter.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(IngredientLayoutAdapter.ViewHolder holder, final int position) {
        final Ingredient ingredient = ingredientList.get(position);
        String name = ingredient.getName();
        holder.ingredientName.setText(name);
    }


    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public ArrayList<Ingredient> getIngredientList(){
        return ingredientList;
    }

    public void addIngredient(Ingredient ingredient){
        ingredientList.add(ingredient);
    }
}


