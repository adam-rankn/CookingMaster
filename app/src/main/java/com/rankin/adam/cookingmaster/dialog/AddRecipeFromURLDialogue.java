package com.rankin.adam.cookingmaster.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.rankin.adam.cookingmaster.R;

public class AddRecipeFromURLDialogue extends Dialog implements AdapterView.OnItemSelectedListener {

    public AddRecipeFromURLDialogue(@NonNull Context context) {
        super(context);

    }
    protected void OnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button saveButton = findViewById(R.id.btn_add_from_url);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
