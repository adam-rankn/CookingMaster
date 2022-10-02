package com.rankin.adam.cookingmaster.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.rankin.adam.cookingmaster.R
import com.rankin.adam.cookingmaster.controller.RecipeImportController
import com.rankin.adam.cookingmaster.dialog.AddRecipeFromURLDialogue
import com.rankin.adam.cookingmaster.dialog.IngredientAddDialog
import com.rankin.adam.cookingmaster.model.Recipe
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddRecipeActivity : AppCompatActivity() {
    private var allergenList: List<String> = ArrayList()
    private var newRecipe: Recipe? = null
    private val IMAGE_REQUEST_CODE = 0
    private val IMAGE_RESULT = 1
    private var imageDecode: String? = null
    private var recipeImage: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        val recipeThumbnail = findViewById<ImageButton>(R.id.addRecipeAct_btn_add_image)
        val nameEdit = findViewById<EditText>(R.id.addRecipeAct_txt_name)
        val timeEdit = findViewById<EditText>(R.id.addRecipeAct_txt_time)
        val instructionsEdit = findViewById<EditText>(R.id.addRecipeAct_txt_instructions)
        val allergensText = findViewById<TextView>(R.id.addRecipeAct_txt_allergen_list)
        val addButton = findViewById<Button>(R.id.addRecipeAct_btn_add_recipe)
        val allergenList = ArrayList(listOf(*resources.getStringArray(R.array.allergens)))
        val mode = intent.getIntExtra("Flag", 0)
        if (mode == 1) {
            loadRecipeData()
            addButton.setText(R.string.done)
        } else {
            //set temporary recipe so we can add photo etc
            newRecipe = Recipe("test")
            MainActivity.recipeController.currentRecipe = newRecipe
        }
        addButton.setOnClickListener {
            if (nameEdit.text.toString().trim { it <= ' ' }.isEmpty()) {
                nameEdit.error = "Recipe name required"
            } else if (timeEdit.text.toString().trim { it <= ' ' }.isEmpty()) {
                timeEdit.error = "Recipe Time required"
            } else if (timeEdit.text.toString().toInt() > 2880) {
                timeEdit.error = "Please enter a time shorter than 2 days"
            } else {
                if (MainActivity.recipeController.imageUri == null) {
                    val defaultUri =
                        Uri.parse("android.resource://com.rankin.adam.cookingmaster/drawable/R.drawable/default_recipe_background")
                    val defaultUriString = defaultUri.toString()
                    MainActivity.recipeController.imageUri = defaultUriString
                }
                val name = nameEdit.text.toString()
                MainActivity.recipeController.name = name
                val time = timeEdit.text.toString().toInt()
                MainActivity.recipeController.time = time
                val instructions = instructionsEdit.text.toString()
                MainActivity.recipeController.instructions = instructions
                val allergens = ArrayList(
                    listOf(
                        *allergensText.text.toString().split(", ").toTypedArray()
                    )
                )
                MainActivity.recipeController.allergens = allergens
                if (mode == 0) {
                    MainActivity.recipeController.addRecipe(newRecipe)
                }
                finish()
            }
        }
        val thumbnailButton = findViewById<ImageButton>(R.id.addRecipeAct_btn_add_image)
        thumbnailButton.setOnClickListener {
            val addImageIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(addImageIntent, IMAGE_RESULT)
        }
        val cancelButton = findViewById<Button>(R.id.addRecipeAct_btn_cancel)
        cancelButton.setOnClickListener { //recipeController.setCurrentRecipe(null);
            finish()
        }
        val addRecipeFromURLButton = findViewById<Button>(R.id.btn_add_from_url)
        addRecipeFromURLButton.setOnClickListener {
            val builder = AlertDialog.Builder(this@AddRecipeActivity)
            builder.setTitle("URL to import from")

            // Set up the input
            val addURLEdit = EditText(this@AddRecipeActivity)
            addURLEdit.hint = "currently works for allrecipes.com"

            // Specify the type of input expected
            addURLEdit.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(addURLEdit)

            // Set up buttons
            builder.setPositiveButton(R.string.OK) { dialog, which ->
                val recipeImportController = RecipeImportController(addURLEdit.text.toString())
                recipeImportController.setRecipeName(addURLEdit.text.toString())
                val time = recipeImportController.time
                timeEdit.setText(time.toString())
                nameEdit.setText(recipeImportController.recipe.name)
                val instructions = recipeImportController.instructions
                instructionsEdit.setText(instructions)
                MainActivity.recipeController.ingredients = recipeImportController.ingredients
                MainActivity.recipeController.instructions = instructions
                newRecipe!!.instructions = instructions
                val layoutInflater = applicationContext
                    .getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val popupView = layoutInflater.inflate(R.layout.dialog_add_from_url, null)
                val popupWindow = AddRecipeFromURLDialogue(this@AddRecipeActivity)
            }
            builder.setNegativeButton(R.string.Cancel) { dialog, which -> dialog.cancel() }
            builder.show()
        }
        val ingredientsButton = findViewById<Button>(R.id.addRecipeAct_btn_set_ingredients)
        ingredientsButton.setOnClickListener {
            if (mode == 0) {
                MainActivity.recipeController.currentRecipe = newRecipe
            }
            val ingredientAddDialog = IngredientAddDialog(this@AddRecipeActivity)
            ingredientAddDialog.show()
        }
        val openDialog = findViewById<View>(allergensText.id)
        openDialog.setOnClickListener {
            val count = allergenList.size
            val dialogList = allergenList.toTypedArray<CharSequence>()
            val builderDialog = AlertDialog.Builder(this@AddRecipeActivity)
            builderDialog.setTitle("Select Allergens")
            val is_checked = BooleanArray(count)
            builderDialog.setMultiChoiceItems(
                dialogList, is_checked
            ) { dialog, whichButton, isChecked -> }
            builderDialog.setPositiveButton("OK") { dialog, which ->
                val list = (dialog as AlertDialog).listView
                // build the comma separated list
                val stringBuilder = StringBuilder()
                for (i in 0 until list.count) {
                    val checked = list.isItemChecked(i)
                    if (checked) {
                        if (stringBuilder.isNotEmpty()) stringBuilder.append(", ")
                        stringBuilder.append(list.getItemAtPosition(i))
                    }
                }
                if (stringBuilder.toString().trim { it <= ' ' } == "") {
                    (findViewById<View>(R.id.addRecipeAct_txt_allergen_list) as TextView).setText(R.string.no_allergens)
                    stringBuilder.setLength(0)
                } else {
                    (findViewById<View>(R.id.addRecipeAct_txt_allergen_list) as TextView).text =
                        stringBuilder
                }
            }
            builderDialog.setNegativeButton("Cancel") { dialog, which ->
                (findViewById<View>(R.id.addRecipeAct_txt_allergen_list) as TextView).setText(
                    R.string.no_allergens
                )
            }
            val alert = builderDialog.create()
            alert.show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadRecipeData() {
        val currentRecipe = MainActivity.recipeController.currentRecipe
        try {
            val recipeThumbnail = findViewById<ImageButton>(R.id.addRecipeAct_btn_add_image)
            val recipeUriString = currentRecipe.imageUri
            val uri = Uri.parse(recipeUriString)
            recipeThumbnail!!.setImageURI(uri)
        } catch (e: NullPointerException) {
            Log.d("imageError", "recipe image not found")
        }
        val nameEdit = findViewById<EditText>(R.id.addRecipeAct_txt_name)
        nameEdit.setText(currentRecipe.name)
        val timeEdit = findViewById<EditText>(R.id.addRecipeAct_txt_time)
        timeEdit.setText(currentRecipe.time.toString())
        val instructionsEdit = findViewById<EditText>(R.id.addRecipeAct_txt_instructions)
        instructionsEdit.setText(currentRecipe.instructions)
        val allergenList = MainActivity.recipeController.allergens
        val stringBuilder = StringBuilder()
        for (element in allergenList) {
            if (stringBuilder.isNotEmpty()) stringBuilder.append(',')
            stringBuilder.append(element)
        }
        val allergensText = findViewById<TextView>(R.id.addRecipeAct_txt_allergen_list)
        allergensText!!.text = stringBuilder.toString()
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == IMAGE_RESULT && resultCode == RESULT_OK && null != data) {
                val URI = data.data
                val file = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = contentResolver.query(URI!!, file, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(file[0])
                imageDecode = cursor.getString(columnIndex)
                cursor.close()
                // check permission to get image
                checkPermission(IMAGE_REQUEST_CODE)
            }
        } catch (e: Exception) {
            // unable to get the path
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                .show()
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
    private fun checkPermission(requestType: Int) {
        // access  to external storage to get image
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        // no permission, ask permission
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                ActivityCompat.requestPermissions(
                    this@AddRecipeActivity,
                    arrayOf(permission),
                    IMAGE_REQUEST_CODE
                )
            } else {
                ActivityCompat.requestPermissions(
                    this@AddRecipeActivity,
                    arrayOf(permission),
                    IMAGE_REQUEST_CODE
                )
            }
        } else {
            // has permission, get the image
            recipeImage = decodeFile(imageDecode)
            val recipeThumbnail = findViewById<ImageButton>(R.id.addRecipeAct_btn_add_image)
            recipeThumbnail!!.setImageBitmap(recipeImage)

            //TODO factor out

            //https://stackoverflow.com/questions/42460710/how-to-convert-a-bitmap-image-to-uri
            val file = createImageFile()
            if (file != null) {
                val fout: FileOutputStream
                try {
                    fout = FileOutputStream(file)
                    recipeImage!!.compress(Bitmap.CompressFormat.PNG, 70, fout)
                    fout.flush()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val uri = Uri.fromFile(file)
                val uriString = uri.toString()
                MainActivity.recipeController.imageUri = uriString
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
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        //if length is zero, request was cancelled
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            recipeImage = decodeFile(imageDecode)
            val recipeThumbnail = findViewById<ImageButton>(R.id.addRecipeAct_btn_add_image)
            recipeThumbnail!!.setImageBitmap(recipeImage)

            //TODO factor out

            //https://stackoverflow.com/questions/42460710/how-to-convert-a-bitmap-image-to-uri
            val file = createImageFile()
            if (file != null) {
                val fout: FileOutputStream
                try {
                    fout = FileOutputStream(file)
                    recipeImage!!.compress(Bitmap.CompressFormat.PNG, 70, fout)
                    fout.flush()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val uri = Uri.fromFile(file)
                val uriString = uri.toString()
                MainActivity.recipeController.imageUri = uriString
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            Toast.makeText(
                this@AddRecipeActivity,
                "Permission needed to access photo gallery.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * decode into Bitmap then compress to desired size
     *
     * @param filePath the file path of image in this phone
     */
    fun decodeFile(filePath: String?): Bitmap {
        //set max image file size
        val maxSize = 65536
        val compressedImage: Bitmap

        // get the original image size
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, option)
        val requiredSize = 500
        // Find the correct scale value - power of 2.
        var widthOrigin = option.outWidth
        var heightOrigin = option.outHeight
        var scale = 1
        while (true) {
            if (widthOrigin < requiredSize && heightOrigin < requiredSize) break
            widthOrigin /= 2
            heightOrigin /= 2
            scale *= 2
        }

        // get image with desired size
        val optionSet = BitmapFactory.Options()
        optionSet.inSampleSize = scale
        compressedImage = BitmapFactory.decodeFile(filePath, optionSet)

        // compress the image to desired file size
        var compressQuality = 100
        var streamLength = maxSize
        while (streamLength >= maxSize) {
            compressQuality -= 1
            val bmpStream = ByteArrayOutputStream()
            compressedImage.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
        }
        return compressedImage
    }

    /**
     * Save bitmap image to file
     * https://stackoverflow.com/questions/42460710/how-to-convert-a-bitmap-image-to-uri
     *
     * @return file
     */
    fun createImageFile(): File? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val imageFileName = "RECIPE_" + timeStamp + "_"
        var mFileTemp: File? = null
        val root = getDir("recipe_images", MODE_PRIVATE).absolutePath
        val myDir = File(root)
        if (!myDir.exists()) {
            myDir.mkdirs()
        }
        try {
            mFileTemp = File.createTempFile(imageFileName, ".jpg", myDir.absoluteFile)
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return mFileTemp
    }
}