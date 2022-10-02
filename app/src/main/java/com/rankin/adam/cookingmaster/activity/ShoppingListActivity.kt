package com.rankin.adam.cookingmaster.activity

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rankin.adam.cookingmaster.R
import com.rankin.adam.cookingmaster.adapter.ShoppingListLayoutAdapter
import com.rankin.adam.cookingmaster.controller.SaveLoadController
import com.rankin.adam.cookingmaster.model.ShoppingListEntry

class ShoppingListActivity : AppCompatActivity() {
    private var shoppingListAdapter: ShoppingListLayoutAdapter? = null
    var saveLoadController = SaveLoadController(this@ShoppingListActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)
        val shoppingList = ArrayList<ShoppingListEntry>()
        shoppingList.addAll(MainActivity.shoppingListController.shoppingList)
        val shoppingListRecyclerView = findViewById<RecyclerView>(R.id.shoppingListAct_recyclerview)
        val shoppingListLinearLayoutManager = LinearLayoutManager(this)
        shoppingListRecyclerView.layoutManager = shoppingListLinearLayoutManager
        shoppingListAdapter = ShoppingListLayoutAdapter(shoppingList, this)
        shoppingListRecyclerView.adapter = shoppingListAdapter
        val orderButton = findViewById<Button>(R.id.shoppingListAct_btn_buy)
        orderButton.setOnClickListener {
            //TODO online grocery ordering integration
        }
        val addButton = findViewById<Button>(R.id.shoppingListAct_btn_add)
        addButton.setOnClickListener {
            //TODO open dialog for adding more stuff
        }
        val clearButton = findViewById<Button>(R.id.shoppingListAct_btn_clear)
        clearButton.setOnClickListener { //TODO factor out confirmation dialogs
            AlertDialog.Builder(this@ShoppingListActivity, R.style.Theme_AppCompat_Dialog_Alert)
                .setTitle("Clear List")
                .setMessage("Delete entire list?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes) { dialog, whichButton ->
                    Toast.makeText(
                        this@ShoppingListActivity,
                        "Shopping List Cleared",
                        Toast.LENGTH_SHORT
                    ).show()
                    MainActivity.shoppingListController.clearShoppingList()
                    shoppingListAdapter!!.clearList()
                    saveLoadController.saveShoppingListToFile()
                }
                .setNegativeButton(android.R.string.no) { dialog, whichButton ->
                    Toast.makeText(
                        this@ShoppingListActivity,
                        "Shopping List Not Cleared",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .show()
        }
    }

    override fun onPause() {
        super.onPause()
        saveLoadController.saveShoppingListToFile()
    }
}