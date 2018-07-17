package com.rankin.adam.cookingmaster.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.rankin.adam.cookingmaster.Activities.MainActivity;
import com.rankin.adam.cookingmaster.Adapters.ShoppingListLayoutAdapter;
import com.rankin.adam.cookingmaster.Model.Ingredient;
import com.rankin.adam.cookingmaster.Model.ShoppingListEntry;
import com.rankin.adam.cookingmaster.R;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.Activities.MainActivity.shoppingListController;

public class ShoppingListActivity extends AppCompatActivity {

    private RecyclerView shoppingListRecyclerView;
    private LinearLayoutManager shoppingListLinearLayoutManager;
    private ShoppingListLayoutAdapter shoppingListAdapter;

    private ArrayList<ShoppingListEntry> shoppingList;

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

        Button mainButton = (Button) findViewById(R.id.shoppingListAct_btn_main);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button addButton = (Button) findViewById(R.id.shoppingListAct_btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open dialog for adding more stuff
            }
        });

        Button clearButton = (Button) findViewById(R.id.shoppingListAct_btn_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO delete shopping list, use confrirmation dialog
            }
        });
    }
}
