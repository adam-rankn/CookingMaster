package com.rankin.adam.cookingmaster.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.rankin.adam.cookingmaster.Fragments.CookingRecipeFragment;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.adapter.CookingRecipesPagerAdapter;
import com.rankin.adam.cookingmaster.model.Recipe;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

public class CookingActivity extends AppCompatActivity {

    private CookingRecipesPagerAdapter cookingRecipesPagerAdapter;
    private ViewPager viewPager;

    private Button nextButton;
    private Button prevButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);

        //cookingRecipesPagerAdapter = new CookingRecipesPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.cookingAct_container);

        //setup pager
        setupViewPager(viewPager);

        prevButton = findViewById(R.id.cookingAct_btn_prev);
        nextButton = findViewById(R.id.cookingAct_btn_next);

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeCurrentRecipeIfUnpinned();

                //go to prev recipe
                Integer prevRecipe = viewPager.getCurrentItem() - 1;
                int size = cookingRecipesPagerAdapter.getCount();

                if (prevRecipe < 0){
                    prevRecipe = size - 1;
                }
                //go to nest recipe
                viewPager.setCurrentItem(prevRecipe);

/*                try {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                }
                catch (Exception e){
                    //viewPager.setCurrentItem(cookingRecipesPagerAdapter.getCount()-1);
                }*/
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeCurrentRecipeIfUnpinned();

                Integer nextRecipe = viewPager.getCurrentItem() + 1;
                int size = cookingRecipesPagerAdapter.getCount();

                if (nextRecipe > size - 1){
                    nextRecipe = 0;
                }
                //go to nest recipe
                viewPager.setCurrentItem(nextRecipe);

/*                try {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
                catch (Exception e){
                    //viewPager.setCurrentItem(0);
                }*/
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
            //adapter.destroyItem(viewPager,index,adapter.getItem(index));
            adapter.removeItemAtPosition(index);
            adapter.notifyDataSetChanged();
            recipeController.unpinRecipe(recipeController.getPinnedRecipes().get(index));
/*            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });*/
        }
    }

    @Override
    public void onBackPressed() {
        removeCurrentRecipeIfUnpinned();
        super.onBackPressed();
    }
}
