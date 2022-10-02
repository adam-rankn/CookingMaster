package com.rankin.adam.cookingmaster.activity;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rankin.adam.cookingmaster.fragments.CookingRecipeFragment;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.adapter.CookingRecipesPagerAdapter;
import com.rankin.adam.cookingmaster.model.Recipe;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

public class CookingActivity extends AppCompatActivity {

    private CookingRecipesPagerAdapter cookingRecipesPagerAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);

        //cookingRecipesPagerAdapter = new CookingRecipesPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.cookingAct_container);

        //setup pager
        setupViewPager(viewPager);

        Button prevButton = findViewById(R.id.cookingAct_btn_prev);
        Button nextButton = findViewById(R.id.cookingAct_btn_next);

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeCurrentRecipeIfUnpinned();

                //go to prev recipe
                Integer prevRecipe = viewPager.getCurrentItem() - 1;
                int size = cookingRecipesPagerAdapter.getCount();
                viewPager.setCurrentItem(prevRecipe);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeCurrentRecipeIfUnpinned();

                Integer nextRecipe = viewPager.getCurrentItem() + 1;
                int size = cookingRecipesPagerAdapter.getCount();

                //if (nextRecipe > size - 1){
                //    nextRecipe = 0;
                //}
                //go to nest recipe
                viewPager.setCurrentItem(nextRecipe);

            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        cookingRecipesPagerAdapter = new CookingRecipesPagerAdapter(getSupportFragmentManager());

        Recipe currentRecipe = recipeController.getCurrentRecipe();
        if (!recipeController.isRecipePinned(currentRecipe)) {
            recipeController.pinRecipe(currentRecipe);
        }

        int currentPos = recipeController.getPinnedRecipes().indexOf(recipeController.getCurrentRecipe());

        for(int i = 0; i < recipeController.getPinnedRecipes().size(); i++) {
            Recipe recipe = recipeController.getPinnedRecipes().get(i);
            CookingRecipeFragment fragment = new CookingRecipeFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("recipe", i);
            fragment.setArguments(bundle);
            cookingRecipesPagerAdapter.addFragment(fragment,recipe);
        }

        viewPager.setAdapter(cookingRecipesPagerAdapter);
        viewPager.setCurrentItem(currentPos);
    }

    public void removeCurrentRecipeIfUnpinned(){
        int index = viewPager.getCurrentItem();
        final CookingRecipesPagerAdapter adapter = ((CookingRecipesPagerAdapter)viewPager.getAdapter());
        CookingRecipeFragment fragment = adapter.getFragment(index);

        if (!fragment.getPinned()){
            adapter.removeItemAtPosition(index);
            adapter.notifyDataSetChanged();
            recipeController.unpinRecipe(recipeController.getPinnedRecipes().get(index));

        }
    }

    @Override
    public void onBackPressed() {
        removeCurrentRecipeIfUnpinned();
        super.onBackPressed();
    }
}
