package com.rankin.adam.cookingmaster.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.model.ShoppingListEntry;
import com.rankin.adam.cookingmaster.R;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.shoppingListController;


/**
 * Created by Adam on 16-Jul-18.
 */

public class ShoppingListLayoutAdapter extends RecyclerView.Adapter<ShoppingListLayoutAdapter.ViewHolder> {

    private ArrayList<ShoppingListEntry> shoppingList;

    public ShoppingListLayoutAdapter(ArrayList<ShoppingListEntry> shoppingList, Context context) {
        super();
        this.shoppingList = new ArrayList<>();
        this.shoppingList.addAll(shoppingList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ingredientName;
        private TextView ingredientAmount;
        private TextView ingredientUnit;
        private Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.shoppingListRowLay_txt_ingredient);
            ingredientAmount = itemView.findViewById(R.id.shoppingListRowLay_txt_amount);
            ingredientUnit = itemView.findViewById(R.id.shoppingListRowLay_txt_unit);

            deleteButton = itemView.findViewById(R.id.shoppingListRowLay_btn_delete);

        }

        @Override
        public void onClick(View view) {

        }
    }

    @NonNull
    @Override
    public ShoppingListLayoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .shopping_list_row_layout, parent, false);
        return new ShoppingListLayoutAdapter.ViewHolder(inflatedView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ShoppingListLayoutAdapter.ViewHolder holder, final int position) {
        final ShoppingListEntry shoppingListEntry = shoppingList.get(position);

        holder.ingredientName.setText(shoppingListEntry.getName());
        holder.ingredientAmount.setText(shoppingListEntry.getAmount().toString());
        holder.ingredientUnit.setText(shoppingListEntry.getUnit());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(holder.getLayoutPosition());
                shoppingListController.removeEntry(holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public void clearList(){
        shoppingList.clear();
        notifyDataSetChanged();
    }

    public void deleteItem(int position){
        shoppingList.remove(position);
        notifyItemRemoved(position);
    }
}
