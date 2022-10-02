package com.rankin.adam.cookingmaster.dialog

import android.app.Dialog
import android.content.Context
import android.widget.AdapterView
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.rankin.adam.cookingmaster.R

class AddRecipeFromURLDialogue(context: Context) : Dialog(context),
    AdapterView.OnItemSelectedListener {
    protected fun OnCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val saveButton = findViewById<Button>(R.id.btn_add_from_url)
        saveButton.setOnClickListener { }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {}
    override fun onNothingSelected(parent: AdapterView<*>?) {}
}