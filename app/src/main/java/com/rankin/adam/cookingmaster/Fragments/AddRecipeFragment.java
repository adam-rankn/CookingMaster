package com.rankin.adam.cookingmaster.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.activity.AddRecipeActivity;
import com.rankin.adam.cookingmaster.dialog.IngredientAddDialog;
import com.rankin.adam.cookingmaster.model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;

public class AddRecipeFragment extends Fragment {

    private List<String> allergenList = new ArrayList<>();
    private Recipe newRecipe;
    private Recipe currentRecipe;

    private int IMAGE_REQUEST_CODE = 0;
    private int IMAGE_RESULT = 1;
    private String imageDecode;
    private Bitmap recipeImage;

    private ImageView recipeThumbnail;
    private EditText nameEdit;
    private EditText timeEdit;
    private EditText instructionsEdit;
    private TextView allergensText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        nameEdit = view.findViewById(R.id.addRecipeAct_txt_name);
        timeEdit = view.findViewById(R.id.addRecipeAct_txt_time);
        instructionsEdit = view.findViewById(R.id.addRecipeAct_txt_instructions);
        allergensText = view.findViewById(R.id.addRecipeAct_txt_allergen_list);

        Button addButton = view.findViewById(R.id.addRecipeAct_btn_add_recipe);

        ImageButton thumbnailButton = view.findViewById(R.id.addRecipeAct_btn_add_image);
        thumbnailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addImgaeIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(addImgaeIntent, IMAGE_RESULT);
            }
        });

        Button cancelButton = view.findViewById(R.id.addRecipeAct_btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recipeController.setCurrentRecipe(null);
                //TODO close
            }
        });

        Button ingredientsButton = view.findViewById(R.id.addRecipeAct_btn_set_ingredients);
        ingredientsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //if (mode == 0) {
                //    recipeController.setCurrentRecipe(newRecipe);
                //}
                //IngredientAddDialog ingredientAddDialog = new IngredientAddDialog();
                //ingredientAddDialog.show();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nameEdit.getText().toString().trim().isEmpty()){
                    nameEdit.setError("Recipe name required");
                }

                else if (timeEdit.getText().toString().trim().isEmpty()){
                    timeEdit.setError("Recipe Time required");
                }

                else if (Integer.parseInt(timeEdit.getText().toString())> 2880){
                    timeEdit.setError("Please enter a time shorter than 2 days");
                }

                else {

                    if (recipeController.getImageUri() == null){
                        Uri defaultUri = Uri.parse("android.resource://com.rankin.adam.cookingmaster/drawable/R.drawable/default_recipe_background");
                        String defaultUriString = defaultUri.toString();
                        recipeController.setImageUri(defaultUriString);
                    }
                    String name = nameEdit.getText().toString();
                    recipeController.setName(name);

                    Integer time = Integer.parseInt(timeEdit.getText().toString());
                    recipeController.setTime(time);

                    String instructions = instructionsEdit.getText().toString();
                    recipeController.setInstructions(instructions);

                    ArrayList<String> allergens= new ArrayList<>(Arrays.asList(allergensText.getText().toString().split(", ")));
                    recipeController.setAllergens(allergens);

                    //if (mode == 0) {
                    //    recipeController.addRecipe(newRecipe);
                    //}


                }
            }
        });



        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        allergenList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.allergens)));


    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
