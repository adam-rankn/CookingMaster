package com.rankin.adam.cookingmaster.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.rankin.adam.cookingmaster.activity.MainActivity.recipeController;
import static java.lang.Boolean.FALSE;

public class AddRecipeActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        recipeThumbnail = findViewById(R.id.addRecipeAct_btn_add_image);
        nameEdit = findViewById(R.id.addRecipeAct_txt_name);
        timeEdit = findViewById(R.id.addRecipeAct_txt_time);
        instructionsEdit = findViewById(R.id.addRecipeAct_txt_instructions);
        allergensText = findViewById(R.id.addRecipeAct_txt_allergen_list);

        Button addButton = findViewById(R.id.addRecipeAct_btn_add_recipe);

        allergenList.add("Soy");
        allergenList.add("Wheat");
        allergenList.add("Dairy");
        allergenList.add("Peanut");
        allergenList.add("Tree nuts");
        allergenList.add("Fish");
        allergenList.add("Shellfish");
        allergenList.add("Peanut");
        allergenList.add("Eggs");


        final int mode = getIntent().getIntExtra("Flag", 0);
        if (mode == 1){
            loadRecipeData();
            addButton.setText("Done");

        }

        else {
            //set temporary recipe so we can add photo etc
            newRecipe = new Recipe("test");
            recipeController.setCurrentRecipe(newRecipe);
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (nameEdit.getText().toString().trim().isEmpty()){
                    nameEdit.setError("Recipe name required");
                }

                else if (timeEdit.getText().toString().trim().isEmpty()){
                    timeEdit.setError("Recipe Time required");
                }


                else {

                    if (recipeController.getImageUri() == null){
                        Uri defaultUri = Uri.parse("android.resource://com.rankin.adam.cookingmaster/drawable/R.drawable/default_recipe_background");
                        String defaultUriString = defaultUri.toString();
                        recipeController.setImageUri(defaultUriString);
                    }
                    String name = nameEdit.getText().toString();
                    recipeController.setName(name);

                    String time = timeEdit.getText().toString();
                    recipeController.setTime(time);

                    String instructions = instructionsEdit.getText().toString();
                    recipeController.setInstructions(instructions);

                    ArrayList<String> allergens= new ArrayList<String>(Arrays.asList(allergensText.getText().toString().split(", ")));
                    recipeController.setAllergens(allergens);

                    if (mode == 0) {
                        recipeController.addRecipe(newRecipe);
                    }

                    else if (mode == 1){

                    }
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
                //recipeController.setCurrentRecipe(null);
                finish();
            }
        });

        Button ingredientsButton = findViewById(R.id.addRecipeAct_btn_set_ingredients);
        ingredientsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mode == 0) {
                    recipeController.setCurrentRecipe(newRecipe);
                }
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
                                if (stringBuilder.length() > 0) stringBuilder.append(", ");
                                stringBuilder.append(list.getItemAtPosition(i));
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

    private void loadRecipeData(){
        currentRecipe = recipeController.getCurrentRecipe();

        String recipeUriString = recipeController.getImageUri();
        Uri uri = Uri.parse(recipeUriString);
        recipeThumbnail.setImageURI(uri);
        nameEdit.setText(currentRecipe.getName());
        timeEdit.setText(currentRecipe.getTime());
        instructionsEdit.setText(currentRecipe.getInstructions());

        ArrayList<String> allergenList = recipeController.getAllergens();

         //build the comma seperated list
        StringBuilder stringBuilder = new StringBuilder();
        //for (int i = 0; i < allergenList.size(); i++) {
        //    if (stringBuilder.length() > 0) stringBuilder.append(',');
        //    String allergen = allergenList.get(i);
        //stringBuilder.append(allergen);
        //}

        for (String element : allergenList) {
            if (stringBuilder.length() > 0) stringBuilder.append(',');
            stringBuilder.append(element);
            }
        allergensText.setText(stringBuilder.toString());
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
                ActivityCompat.requestPermissions(AddRecipeActivity.this, new String[]{permission}, IMAGE_REQUEST_CODE);

            } else {
                ActivityCompat.requestPermissions(AddRecipeActivity.this, new String[]{permission}, IMAGE_REQUEST_CODE);

            }
        } else {
            // has permission, get the image
            recipeImage = decodeFile(imageDecode);
            recipeThumbnail.setImageBitmap(recipeImage);

            //TODO factor out

            //https://stackoverflow.com/questions/42460710/how-to-convert-a-bitmap-image-to-uri
            File file = createImageFile();
            if (file != null) {
                FileOutputStream fout;
                try {
                    fout = new FileOutputStream(file);
                    recipeImage.compress(Bitmap.CompressFormat.PNG, 70, fout);
                    fout.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Uri uri = Uri.fromFile(file);
                String uriString = uri.toString();
                recipeController.setImageUri(uriString);
            }
        }
    }

    /**
     * checks result of permissions request
     *
     * @param requestCode the indicator for gps or image permission
     * @param permissions the sentence of permission request
     * @param grantResults the result of permission request
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        //if length is zero, request was cancelled
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            recipeImage = decodeFile(imageDecode);
            recipeThumbnail.setImageBitmap(recipeImage);

            //TODO factor out

            //https://stackoverflow.com/questions/42460710/how-to-convert-a-bitmap-image-to-uri
            File file = createImageFile();
            if (file != null) {
                FileOutputStream fout;
                try {
                    fout = new FileOutputStream(file);
                    recipeImage.compress(Bitmap.CompressFormat.PNG, 70, fout);
                    fout.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Uri uri = Uri.fromFile(file);
                String uriString = uri.toString();
                recipeController.setImageUri(uriString);


            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            Toast.makeText(AddRecipeActivity.this, "Permission needed to access photo gallery.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * decode into Bitmap then compress to desired size
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

        final int REQUIRED_SIZE = 500;
        // Find the correct scale value - power of 2.
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

    /**
     * Save bitmap image to file
     * https://stackoverflow.com/questions/42460710/how-to-convert-a-bitmap-image-to-uri
     *
     * @return file
     */
    public File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "RECIPE_" + timeStamp + "_";
        File mFileTemp = null;
        String root = this.getDir("recipe_images", Context.MODE_PRIVATE).getAbsolutePath();
        File myDir = new File(root);
        if(!myDir.exists()){
            myDir.mkdirs();
        }
        try {
            mFileTemp=File.createTempFile(imageFileName,".jpg",myDir.getAbsoluteFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return mFileTemp;
    }
}




