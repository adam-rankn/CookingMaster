package com.rankin.adam.cookingmaster.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

/**
 * Created by Adam on 10-Jul-18.
 */

public class IngredientLayoutAdapter extends RecyclerView.Adapter<IngredientLayoutAdapter.ViewHolder> {

    private ArrayList<RecipeIngredientEntry> ingredientList;

    public IngredientLayoutAdapter(ArrayList<RecipeIngredientEntry> ingredientList, Context context) {
        this.ingredientList = new ArrayList<>();
        this.ingredientList.addAll(ingredientList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ingredientName;
        private Button deleteButton;
        private TextView amount;
        private TextView unit;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            ingredientName = itemView.findViewById(R.id.ingrRow_name);
            amount = itemView.findViewById(R.id.ingrRow_txt_amount);
            unit = itemView.findViewById(R.id.ingrRow_txt_unit);

            deleteButton = itemView.findViewById(R.id.ingrDialog_btn_delete_ingredient);
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
        final RecipeIngredientEntry recipeIngredientEntry = ingredientList.get(position);
        String name = recipeIngredientEntry.getIngredientName();
        holder.ingredientName.setText(name);

        if (recipeIngredientEntry.getAmount() != null) {
            String amount = recipeIngredientEntry.getAmount().toString();
            holder.amount.setText(amount);

            String unit = recipeIngredientEntry.getUnit();
            holder.unit.setText(unit);
        }

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeController.removeIngredient(position);
                removeIngredient(position);
                notifyItemRemoved(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void addIngredient(RecipeIngredientEntry ingredient){
        ingredientList.add(ingredient);
    }

    public void removeIngredient(int position) {
        ingredientList.remove(position);
    }
}


