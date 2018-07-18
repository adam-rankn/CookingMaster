package com.rankin.adam.cookingmaster.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rankin.adam.cookingmaster.activity.dialog.IngredientAddDialog;
import com.rankin.adam.cookingmaster.R;
import com.rankin.adam.cookingmaster.model.Recipe;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;
import static java.lang.Boolean.FALSE;

public class AddRecipeActivity extends AppCompatActivity {

    private List<String> allergenList = new ArrayList<>();
    private Recipe newRecipe;

    private int IMAGE_REQUEST_CODE = 0;
    private int IMAGE_RESULT = 1;
    private String imageDecode;
    private Bitmap recipeImage;
    private ImageView recipeThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        allergenList.add("Soy");
        allergenList.add("Wheat");
        allergenList.add("Dairy");
        allergenList.add("Peanut");
        allergenList.add("Tree nuts");
        allergenList.add("Fish");
        allergenList.add("Shellfish");
        allergenList.add("Peanut");
        allergenList.add("Eggs");

        newRecipe = new Recipe("test");
        recipeThumbnail = findViewById(R.id.addRecipeAct_btn_add_image);


        //mode = getIntent().getIntExtra("Mode",0);
        //if (mode == 1){
        //    Button saveRecipeButton = findViewById(R.id.addRecipeAct_btn_add_recipe);
        //    saveRecipeButton.setText("Save");
        //}

        Button addButton = findViewById(R.id.addRecipeAct_btn_add_recipe);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get recipe ingr from view
                EditText nameEdit = findViewById(R.id.addRecipeAct_txt_name);
                EditText timeEdit = findViewById(R.id.addRecipeAct_txt_time);
                EditText instructionsEdit = findViewById(R.id.addRecipeAct_txt_instructions);
                TextView allergensText = findViewById(R.id.addRecipeAct_txt_allergen_list);


                ArrayList<String> allergensList= new ArrayList(Arrays.asList(allergensText.toString().split(",")));


                if (nameEdit.getText().toString().trim().isEmpty()){
                    nameEdit.setError("Recipe name required");
                }

                else if (timeEdit.getText().toString().trim().isEmpty()){
                    timeEdit.setError("Recipe Time required");
                }


                else {
                    String name = nameEdit.getText().toString();
                    newRecipe.setName(name);

                    String time = timeEdit.getText().toString();
                    newRecipe.setTime(time);

                    String instructions = instructionsEdit.getText().toString();
                    newRecipe.setInstructions(instructions);

                    newRecipe.setAllergens(allergensList);

                    recipeController.addRecipe(newRecipe);
                    finish();
                }
            }
        });

        ImageButton thumbnailButton = findViewById(R.id.addRecipeAct_btn_add_image);
        thumbnailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addImgaeIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(addImgaeIntent, IMAGE_RESULT);

            }
        });

        Button cancelButton = findViewById(R.id.addRecipeAct_btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button ingredientsButton = findViewById(R.id.addRecipeAct_btn_set_ingredients);
        ingredientsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                recipeController.setCurrentRecipe(newRecipe);
                IngredientAddDialog ingredientAddDialog = new IngredientAddDialog(AddRecipeActivity.this);
                ingredientAddDialog.show();
            }
        });


        View openDialog = (View) findViewById(R.id.addRecipeAct_txt_allergen_list);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = allergenList.size();
                final CharSequence[] dialogList =  allergenList.toArray(new CharSequence[count]);
                final AlertDialog.Builder builderDialog = new AlertDialog.Builder(AddRecipeActivity.this);
                builderDialog.setTitle("Select Item");
                boolean[] is_checked = new boolean[count];
                builderDialog.setMultiChoiceItems(dialogList, is_checked,
                        new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton, boolean isChecked) {
                    }
                });
                builderDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ListView list = ((AlertDialog) dialog).getListView();
                    // build the comma seperated list
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < list.getCount(); i++) {
                        boolean checked = list.isItemChecked(i);
                        if (checked) {
                            if (stringBuilder.length() > 0) stringBuilder.append(',');
                            stringBuilder.append(list.getItemAtPosition(i).toString());
                        }
                    }
                    if (stringBuilder.toString().trim().equals("")) {
                        ((TextView) findViewById(R.id.addRecipeAct_txt_allergen_list)).setText("No Allergens");
                        stringBuilder.setLength(0);
                    } else {
                        ((TextView) findViewById(R.id.addRecipeAct_txt_allergen_list)).setText(stringBuilder);
                    }
                }
                });
                builderDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((TextView) findViewById(R.id.addRecipeAct_txt_allergen_list)).setText("No Allergens");
                    }
                });
                AlertDialog alert = builderDialog.create();
                alert.show();

            }
        });

    }

    /**
     * call this to open photo gallery outside the app
     * then once the user select the image, get the path of the image
     * to use the path, call permission to access external storage
     *
     * @param requestCode The indicator for select image operation
     * @param resultCode Whether the activity returned properly
     * @param data the context of AddEventActivity activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == IMAGE_RESULT && resultCode == RESULT_OK && null != data) {
                Uri URI = data.getData();
                String[] FILE = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(URI, FILE, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(FILE[0]);
                imageDecode = cursor.getString(columnIndex);
                cursor.close();
                // check permission to get image
                checkPermission(IMAGE_REQUEST_CODE);

            }
        } catch (Exception e) {
            // unable to get the path
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                    .show();
        }

    }

    /**
     * Ask user for permission at runtime
     * one is when user need to get the image then
     * check for read external storage permission
     * one is when user need to get the current location using gps
     * check for access location permission
     *
     * @param requestType the indicator for gps or image permission
     */
    private void checkPermission(int requestType) {
        // access to external storage to get image
        final String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        // no permission, ask permission
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                ActivityCompat.requestPermissions(AddRecipeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

            } else {
                ActivityCompat.requestPermissions(AddRecipeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

            }
        } else {
            // has permission, get the image
            recipeImage = decodeFile(imageDecode);
            recipeThumbnail.setImageBitmap(recipeImage);
            //TODO store imgae here
        }


    }

    /**
     * call from checkPermission if no permission is granted
     * then ask the user to give permissions
     *
     * @param requestCode the indicator for gps or image permission
     * @param permissions the sentence of permission request
     * @param grantResults the result of permission request
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            recipeImage = decodeFile(imageDecode);
            recipeThumbnail.setImageBitmap(recipeImage);
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            Toast.makeText(AddRecipeActivity.this, "Permission needed to access photo gallery.", Toast.LENGTH_SHORT).show();
        }



    }

    /**
     * pass the file path to decode it into bitmap
     * then resize and compress it to desired file size
     * then set it to image view to show it
     *
     * @param filePath the file path of image in this phone
     */
    public Bitmap decodeFile(String filePath) {
        //set max image file size
        int maxSize = 65536;

        Bitmap compressedImage;

        // get the original image size
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, option);

        // The new size we want to scale to 500
        final int REQUIRED_SIZE = 500;
        // Find the correct scale value. It should be the power of 2.
        int width_origin = option.outWidth, height_origin = option.outHeight;
        int scale = 1;
        while (true) {
            if (width_origin < REQUIRED_SIZE && height_origin < REQUIRED_SIZE)
                break;
            width_origin /= 2;
            height_origin /= 2;
            scale *= 2;
        }

        // get image with desired size
        BitmapFactory.Options optionSet = new BitmapFactory.Options();
        optionSet.inSampleSize = scale;
        compressedImage = BitmapFactory.decodeFile(filePath, optionSet);

        // compress the image to desired file size
        int compressQuality = 100;
        int streamLength = maxSize;

        while (streamLength >= maxSize) {
            compressQuality -= 1;
            ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
            compressedImage.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream);

            byte[] bmpPicByteArray = bmpStream.toByteArray();
            streamLength = bmpPicByteArray.length;
        }

        return compressedImage;
    }
}



