package com.rankin.adam.cookingmaster.Activities.Dialogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rankin.adam.cookingmaster.Adapters.ShoppingListLayoutAdapter;
import com.rankin.adam.cookingmaster.Model.Ingredient;
import com.rankin.adam.cookingmaster.R;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.shoppingListController;

public class ShoppingListActivity extends AppCompatActivity {

    private RecyclerView shoppingListRecyclerView;
    private LinearLayoutManager shoppingListLinearLayoutManager;
    private ShoppingListLayoutAdapter shoppingListAdapter;

    private ArrayList<Ingredient> shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        shoppingList = new ArrayList<>();
        shoppingList.addAll(shoppingListController.getShoppingList());

        shoppingListRecyclerView = findViewById(R.id.shoppingListAct_recyclerview);
        shoppingListLinearLayoutManager = new LinearLayoutManager(this);
        shoppingListRecyclerView.setLayoutManager(shoppingListLinearLayoutManager);
        shoppingListAdapter = new ShoppingListLayoutAdapter(shoppingList, this);
        shoppingListRecyclerView.setAdapter(shoppingListAdapter);
    }
}
