package com.rankin.adam.cookingmaster.activity;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.rankin.adam.cookingmaster.adapter.RecipeBookLayoutAdapter;
import com.rankin.adam.cookingmaster.controller.SaveLoadController;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.model.Recipe;

import java.util.ArrayList;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeList;

public class RecipeBookActivity extends AppCompatActivity {

    private RecyclerView recipeBookRecyclerView;
    private LinearLayoutManager recipeBookLinearLayoutManager;
    public RecipeBookLayoutAdapter recipeBookAdapter;

    private int ADD_RECIPE_REQUEST = 0;

    private ArrayList<Recipe> adapterList = new ArrayList<>();

    private SaveLoadController saveLoadController = new SaveLoadController(RecipeBookActivity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        adapterList.addAll(recipeList.getRecipeList());

        recipeBookRecyclerView = findViewById(R.id.recycler_recipe_book);
        recipeBookLinearLayoutManager = new LinearLayoutManager(this);
        recipeBookRecyclerView.setLayoutManager(recipeBookLinearLayoutManager);
        recipeBookAdapter = new RecipeBookLayoutAdapter(adapterList, this);
        recipeBookRecyclerView.setAdapter(recipeBookAdapter);

        Button returnToMainButton = findViewById(R.id.btn_main);
        returnToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button addRecipeButton = findViewById(R.id.btn_add_recipe);
        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRecipeIntent = new Intent(RecipeBookActivity.this, AddRecipeActivity.class);
                startActivityForResult(addRecipeIntent,ADD_RECIPE_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ADD_RECIPE_REQUEST){
            if (resultCode == RESULT_OK){
                recreate();
            }
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        saveLoadController.saveRecipesToFile();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        invalidateOptionsMenu();
        recreate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
