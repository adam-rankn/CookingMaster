package com.rankin.adam.cookingmaster.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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
import com.rankin.adam.cookingmaster.model.Recipe;

import junit.framework.Assert;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
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
            pinRecipeButton.setImageResource(R.drawable.pinpressed);
            pinRecipeButton.setBackgroundColor(0x00000);
        }

        //build instructions string with links and set to textView
        String instructions = recipe.getInstructions();
        SpannableStringBuilder builder = highlightTimers(instructions);
        instructionsTextView = view.findViewById(R.id.cookRecipeFrag_txt_instructions);
        instructionsTextView.setMovementMethod(LinkMovementMethod.getInstance());
        //instructionsTextView.setText(recipe.getInstructions());
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


        Integer currentTimer = 1;
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (String word:splitInstructions){
            //check if the current word is a valid time
            if (word.matches("\\d+")){
                SpannableString ss = new SpannableString(word);
                ss.setSpan(new timerClickableSpan(currentTimer,word),0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.append(ss);
            }
            else {
                builder.append(word);
            }
            //add space
            builder.append(" ");
            currentTimer +=1;
        }

        return builder;
    }

    public class timerClickableSpan extends ClickableSpan {

        int pos;
        String time;
        Integer timerMinutes;
        Integer timerSeconds;
        String seconds;
        String minutes;

        public timerClickableSpan(int position,String time){
            this.pos = position;
            this.time = time;

        }

        @Override
        public void onClick(View view) {
            //open timer
            LayoutInflater layoutInflater =
                    (LayoutInflater)getContext()
                            .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.popup_cook_timer, null);
            final PopupWindow popupWindow = new PopupWindow(
                    popupView, ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

            Button btnDismiss = popupView.findViewById(R.id.cookTimerPopup_btn_close);
            TextView timerTitleTextView = popupView.findViewById(R.id.cookTimerPopup_txt_title);
            final TextView timerTextView = popupView.findViewById(R.id.cookTimerPopu_txt_timer);

            //TODO extract all this into a custom popup class
            timerTitleTextView.setText(recipe.getName());
            timerTextView.setText(time);
            timerMinutes = Integer.parseInt(time);
            timerSeconds = timerMinutes * 60;

            new CountDownTimer(timerSeconds*1000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timerTextView.setText((millisUntilFinished / 1000)/60 +  ":" + (millisUntilFinished / 1000)%60);
                    //here you can have your logic to set text to edittext
                }

                public void onFinish() {
                    timerTextView.setText("00:00");
                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(500); //You can manage the blinking time with this parameter
                    anim.setStartOffset(0);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    timerTextView.startAnimation(anim);
                }

            }.start();

            btnDismiss.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }});

            popupWindow.showAsDropDown(showInstructionsButton, 50, -30);


            popupView.setOnTouchListener(new View.OnTouchListener() {
                int orgX, orgY;
                int offsetX, offsetY;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            orgX = (int) event.getX();
                            orgY = (int) event.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            offsetX = (int)event.getRawX() - orgX;
                            offsetY = (int)event.getRawY() - orgY;
                            popupWindow.update(offsetX, offsetY, -1, -1, true);
                            break;
                    }
                    return true;
                }});
        }

    }

}
