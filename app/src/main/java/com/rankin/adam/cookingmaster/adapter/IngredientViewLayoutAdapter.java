package com.rankin.adam.cookingmaster.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.dialog.AddIngredientToShoppingListDialog;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry;
import com.rankin.adam.cookingmaster.model.ShoppingListEntry;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.shoppingListController;

/**
 * Created by Adam on 15-Jul-18.
 */

public class IngredientViewLayoutAdapter extends RecyclerView.Adapter<IngredientViewLayoutAdapter.ViewHolder> {

    private ArrayList<RecipeIngredientEntry> ingredientList;
    private Context viewRecipeContext;
    private Integer scaleFactor = 1;

    public IngredientViewLayoutAdapter(ArrayList<RecipeIngredientEntry> ingredientList, Context context) {
        this.ingredientList = new ArrayList<>();
        this.ingredientList.addAll(ingredientList);
        this.viewRecipeContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ingredientName;
        private Button addToShoppingListButton;
        private TextView ingredientAmount;
        private TextView ingredientUnit;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            addToShoppingListButton = itemView.findViewById(R.id.ingrViewRowLay_btn_add_to_shop);
            ingredientName = itemView.findViewById(R.id.ingrViewRowLay_ingredient_name);
            ingredientAmount = itemView.findViewById(R.id.ingrViewRowLay_txt_amount);
            ingredientUnit = itemView.findViewById(R.id.ingrViewRowLay_txt_unit);
        }
        @Override
        public void onClick(View view) {
        }
    }

    @NonNull
    @Override
    public IngredientViewLayoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .ingredient_view_row_layout, parent, false);
        return new IngredientViewLayoutAdapter.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(IngredientViewLayoutAdapter.ViewHolder holder, final int position) {
        final RecipeIngredientEntry recipeIngredientEntry = ingredientList.get(position);
        String ingredientName = recipeIngredientEntry.getIngredientName();
        holder.ingredientName.setText(ingredientName);

        if (recipeIngredientEntry.getAmount() != null){
            Float amount = recipeIngredientEntry.getAmount() * scaleFactor;
            String strAmount = amount.toString();
            String unit = recipeIngredientEntry.getUnit();
            holder.ingredientAmount.setText(strAmount);
            holder.ingredientUnit.setText(unit);
        }

        holder.addToShoppingListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingListEntry shoppingListEntry = new ShoppingListEntry(recipeIngredientEntry.getIngredient());
                shoppingListController.setShoppingListEntry(shoppingListEntry);
                AddIngredientToShoppingListDialog dialog = new AddIngredientToShoppingListDialog(viewRecipeContext);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void setScaleFactor(Integer factor){
        this.scaleFactor = factor;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

}
