package com.rankin.adam.cookingmaster.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.adapter.IngredientViewLayoutAdapter;
import com.rankin.adam.cookingmaster.model.Recipe;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

public class CookingRecipeFragment extends Fragment {

    private Button showIngredientsButton;
    private Button showInstructionsButton;
    private ImageButton pinRecipeButton;

    private TextView instructionsTextView;
    private RecyclerView ingredientsRecyclerView;
    private TextView recipeTitleTextView;

    private Boolean isPinned = Boolean.FALSE;

    private Recipe recipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_cook,container,false);


        Bundle bundle = this.getArguments();
        int currentRecipe = bundle.getInt("recipe", -1);

        showIngredientsButton = view.findViewById(R.id.cookRecipeFrag_btn_show_ingredients);
        showInstructionsButton = view.findViewById(R.id.cookRecipeFrag_btn_show_instructions);
        pinRecipeButton = view.findViewById(R.id.cookRecipeFrag_btn_pin_recipe);


        try {
            if (currentRecipe != -1) {
                recipe = recipeController.getPinnedRecipes().get(currentRecipe);
            } else {
                recipe = recipeController.getCurrentRecipe();
            }
        }
        catch (IndexOutOfBoundsException e){
            recipe = recipeController.getPinnedRecipes().get(recipeController.getPinnedSize()-1);
        }

        if (recipeController.isRecipePinned(recipe)){
            isPinned = Boolean.TRUE;
            pinRecipeButton.setImageResource(R.drawable.pinpressed);
            pinRecipeButton.setBackgroundColor(0x00000);
        }

        instructionsTextView = view.findViewById(R.id.cookRecipeFrag_txt_instructions);
        instructionsTextView.setText(recipe.getInstructions());
        instructionsTextView.setVisibility(View.GONE);

        recipeTitleTextView = view.findViewById(R.id.cookRecipeFrag_txt_title);
        recipeTitleTextView.setText(recipe.getName());

        ingredientsRecyclerView = view.findViewById(R.id.cookRecipeFrag_recycler_ingredients);
        LinearLayoutManager ingredientViewLinearLayoutManager = new LinearLayoutManager(getContext());
        ingredientsRecyclerView.setLayoutManager(ingredientViewLinearLayoutManager);
        IngredientViewLayoutAdapter ingredientViewAdapter = new IngredientViewLayoutAdapter(recipe.getIngredientList(), getContext());
        ingredientsRecyclerView.setAdapter(ingredientViewAdapter);

        showInstructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instructionsTextView.setVisibility(View.VISIBLE);
                ingredientsRecyclerView.setVisibility(View.GONE);
            }
        });
        showIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredientsRecyclerView.setVisibility(View.VISIBLE);
                instructionsTextView.setVisibility(View.GONE);
            }
        });
        pinRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPinned){
                    isPinned = Boolean.TRUE;
                    if (!recipeController.isRecipePinned(recipe)) {
                        recipeController.pinRecipe(recipe);
                    }
                    pinRecipeButton.setImageResource(R.drawable.pinpressed);
                    pinRecipeButton.setBackgroundColor(0x00000);
                }

                else if (isPinned){
                    isPinned = Boolean.FALSE;
                    pinRecipeButton.setImageResource(R.drawable.pin);
                    pinRecipeButton.setBackgroundResource(android.R.drawable.btn_default);
                }

            }
        });

        return view;
    }

/*    @Override
    public void onStop(){
        if (!isPinned){
            recipeController.unpinRecipe(recipe);
            //getActivity().getSupportFragmentManager().popBackStack();
            ((CookingActivity)getActivity()).size = recipeController.getPinnedSize();
        }
        super.onStop();

    }*/

    public Boolean getPinned() {
        return isPinned;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            Log.d("tag", recipe.getName() + " is NOT on screen");
        }
        else
        {
            Log.d("tag", recipe.getName() + " is on screen");
        }
    }
}
