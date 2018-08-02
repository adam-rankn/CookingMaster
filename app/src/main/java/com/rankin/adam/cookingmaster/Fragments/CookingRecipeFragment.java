package com.rankin.adam.cookingmaster.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.adapter.IngredientViewLayoutAdapter;
import com.rankin.adam.cookingmaster.model.Recipe;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

public class CookingRecipeFragment extends Fragment {

    private Button showIngredientsButton;
    private Button showInstructionsButton;
    private Button scaleButton;
    private Button timerButton;
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
        scaleButton = view.findViewById(R.id.cookRecipeFrag_btn_scale);
        timerButton = view.findViewById(R.id.cookRecipeFrag_btn_start_timer);


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
        final IngredientViewLayoutAdapter ingredientViewAdapter = new IngredientViewLayoutAdapter(recipe.getIngredientList(), getContext());
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

        scaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ingredientViewAdapter.setScaleFactor();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Scale Recipe");

                // Set up the input
                final EditText factorEdit = new EditText(getContext());
                factorEdit.setHint("2");

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                factorEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(factorEdit);

                // Set up buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (factorEdit.getText().toString().trim().length() == 0) {
                            factorEdit.setError("please enter a valid factor");
                        }
                        Integer scaleFactor = Integer.parseInt(factorEdit.getText().toString());
                        ingredientViewAdapter.setScaleFactor(scaleFactor);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        return view;
    }

    public Boolean getPinned() {
        return isPinned;
    }

    private void highlightTimers(){
        String instructionText = instructionsTextView.getText().toString();

        //split on one or more whitspaces
        String[] splitInstructions = instructionText.split("\\s+");

        Integer currentTimer = 1;
        for (String word:splitInstructions){
            if (word.matches("\\d+")){
                SpannableString ss = new SpannableString(word);
                ss.setSpan(new myClickableSpan(currentTimer),1, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            }
        }

    }

    public class myClickableSpan extends ClickableSpan {

        int pos;
        public myClickableSpan(int position){
            this.pos=position;
        }

        @Override
        public void onClick(View widget) {
            //open timer
            Toast.makeText(getContext(), "Position "  + pos + " clicked!", Toast.LENGTH_LONG).show();
        }

    }

}
