package com.rankin.adam.cookingmaster.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.adapter.IngredientViewLayoutAdapter;
import com.rankin.adam.cookingmaster.dialog.RecipeTimerPopup;
import com.rankin.adam.cookingmaster.model.Recipe;

import junit.framework.Assert;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

public class CookingRecipeFragment extends Fragment {

    private Button showIngredientsButton;
    private Button showInstructionsButton;
    private Button scaleButton;
    private Button timerButton;
    private Button pinRecipeButton;

    private TextView instructionsTextView;
    private RecyclerView ingredientsRecyclerView;
    private TextView recipeTitleTextView;

    private Boolean isPinned = Boolean.FALSE;

    private Recipe recipe;
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_cook,container,false);

        Bundle bundle = this.getArguments();
        int currentRecipe = bundle.getInt("recipe", -1);
        activity = getActivity();

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
            pinRecipeButton.setText(R.string.unpin);
        }

        //build instructions string with links and set to textView
        String instructions = recipe.getInstructions();
        SpannableStringBuilder builder = highlightTimers(instructions);
        instructionsTextView = view.findViewById(R.id.cookRecipeFrag_txt_instructions);
        instructionsTextView.setMovementMethod(LinkMovementMethod.getInstance());
        instructionsTextView.setText(builder);

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
                    pinRecipeButton.setText(R.string.pin);
                }

                else if (isPinned){
                    isPinned = Boolean.FALSE;
                    pinRecipeButton.setText(R.string.unpin);
                }
            }
        });

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Timer duration in minutes");

                // Set up the input
                final EditText timerEdit = new EditText(getContext());
                timerEdit.setHint("5");

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                timerEdit.setInputType(InputType.TYPE_CLASS_DATETIME);
                builder.setView(timerEdit);

                // Set up buttons
                builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Integer time = Integer.parseInt(timerEdit.getText().toString())* 60;

                        LayoutInflater layoutInflater =
                                (LayoutInflater)getContext()
                                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = layoutInflater.inflate(R.layout.popup_cook_timer, null);
                        final RecipeTimerPopup popupWindow = new RecipeTimerPopup(
                                popupView, ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT,time,showInstructionsButton,recipe.getName());
                    }
                });
                builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        scaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getText(R.string.scaleRecipeDialogTitle));

                // Set up the input
                final EditText factorEdit = new EditText(getContext());
                factorEdit.setHint("2");

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                factorEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(factorEdit);

                // Set up buttons
                builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (factorEdit.getText().toString().trim().length() == 0) {
                            factorEdit.setError(getText(R.string.scaleRecipeFactor));
                        }
                        Integer scaleFactor = Integer.parseInt(factorEdit.getText().toString());
                        ingredientViewAdapter.setScaleFactor(scaleFactor);
                    }
                });
                builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
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

    private SpannableStringBuilder highlightTimers(String text){

        //split on one or more whitespaces
        String[] splitInstructions = text.split("\\s+");

        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (String word:splitInstructions){
            //check if the current word is a valid time

            //match XX minutes
            if (word.matches("\\d+")){
                SpannableString ss = new SpannableString(word);
                Integer time = Integer.parseInt(word)*60;
                ss.setSpan(new timerClickableSpan(time),0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.append(ss);
            }
            //match MM:SS format of timer
            else if (word.matches("[\\d]\\S[:]\\S[\\d+]")){
                SpannableString ss = new SpannableString(word);

                //get the time in seconds
                Integer time = Integer.parseInt(word.substring(0 , word.indexOf(":"))) * 60;
                time += Integer.parseInt(word.substring(word.indexOf(":")+1,word.length()));

                ss.setSpan(new timerClickableSpan(time),0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.append(ss);
            }
            else {
                builder.append(word);
            }
            //add space
            builder.append(" ");

        }

        return builder;
    }

    public class timerClickableSpan extends ClickableSpan {

        Integer time;
        private long lastClickTime = 0;

        public timerClickableSpan(Integer time){
            this.time = time;
        }
        @Override
        public void onClick(View view) {
            // prevent double click opening multiple timers
            if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                return;
            }
            lastClickTime = SystemClock.elapsedRealtime();


            //open timer
            LayoutInflater layoutInflater =
                    (LayoutInflater)getContext()
                            .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.popup_cook_timer, null);
            final RecipeTimerPopup popupWindow = new RecipeTimerPopup(popupView, ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT,time, showInstructionsButton,recipe.getName());
        }
    }
}
