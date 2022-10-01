package com.rankin.adam.cookingmaster.model

import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry
import com.rankin.adam.cookingmaster.model.Ingredient
import com.rankin.adam.cookingmaster.model.ShoppingListEntry
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rankin.adam.cookingmaster.databinding.ActivitySpiceRackBinding

class SpiceRackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySpiceRackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        //CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        //toolBarLayout.setTitle(getTitle());

        //FloatingActionButton fab = binding.fab;
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});
    }
}