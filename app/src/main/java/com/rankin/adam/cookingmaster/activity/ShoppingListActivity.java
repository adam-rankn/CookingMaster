package com.rankin.adam.cookingmaster.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rankin.adam.cookingmaster.adapter.ShoppingListLayoutAdapter;
import com.rankin.adam.cookingmaster.controller.SaveLoadController;
import com.rankin.adam.cookingmaster.model.ShoppingListEntry;
import com.rankin.adam.cookingmaster.R;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.shoppingListController;

public class ShoppingListActivity extends AppCompatActivity {

    private RecyclerView shoppingListRecyclerView;
    private LinearLayoutManager shoppingListLinearLayoutManager;
    private ShoppingListLayoutAdapter shoppingListAdapter;

    private ArrayList<ShoppingListEntry> shoppingList;
    SaveLoadController saveLoadController = new SaveLoadController(ShoppingListActivity.this);

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

        Button orderButton = findViewById(R.id.shoppingListAct_btn_buy);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO onlinee grocery ordering integration
            }
        });

        Button addButton = findViewById(R.id.shoppingListAct_btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open dialog for adding more stuff
            }
        });

        Button clearButton = findViewById(R.id.shoppingListAct_btn_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ShoppingListActivity.this,R.style.Theme_AppCompat_Dialog_Alert)
                        .setTitle("Clear List")
                        .setMessage("Delete entire list?")
                        .setIcon(android.R.drawable.ic_dialog_alert)

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(ShoppingListActivity.this, "Shopping List Cleared", Toast.LENGTH_SHORT).show();
                                shoppingListController.clearShoppingList();
                                shoppingListAdapter.clearList();

                            }})
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(ShoppingListActivity.this, "Shopping List Not Cleared", Toast.LENGTH_SHORT).show();
                            }}).show();
            }
        });
    }

    protected void onPause(){
        super.onPause();
        saveLoadController.saveShoppingListToFile();

    }
}
