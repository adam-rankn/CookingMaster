package com.rankin.adam.cookingmaster.adapter

import com.rankin.adam.cookingmaster.model.ShoppingListEntry
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.rankin.adam.cookingmaster.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Button
import com.rankin.adam.cookingmaster.activity.MainActivity
import java.util.ArrayList

/**
 * Created by Adam on 16-Jul-18.
 */
class ShoppingListLayoutAdapter(shoppingList: ArrayList<ShoppingListEntry>?, context: Context?) :
    RecyclerView.Adapter<ShoppingListLayoutAdapter.ViewHolder>() {
    private val shoppingList: ArrayList<ShoppingListEntry> = ArrayList()

    init {
        this.shoppingList.addAll(shoppingList!!)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ingredientName: TextView
        val deleteButton: Button
        val ingredientNote: TextView

        init {
            ingredientName = itemView.findViewById(R.id.shoppingListRowLay_txt_ingredient)
            ingredientNote = itemView.findViewById(R.id.shoppingListRowLay_txt_note)
            //ingredientAmount = itemView.findViewById(R.id.shoppingListRowLay_txt_amount)
            //ingredientUnit = itemView.findViewById(R.id.shoppingListRowLay_txt_unit)
            deleteButton = itemView.findViewById(R.id.shoppingListRowLay_btn_delete)
        }

        override fun onClick(view: View) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_list_row_layout, parent, false)
        return ViewHolder(inflatedView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoppingListEntry = shoppingList[position]
        holder.ingredientName.text = shoppingListEntry.name
        holder.ingredientNote.text = shoppingListEntry.note
        //holder.ingredientAmount.text = shoppingListEntry.amount.toString()
        //holder.ingredientUnit.text = shoppingListEntry.unit
        holder.deleteButton.setOnClickListener {
            deleteItem(holder.layoutPosition)
            MainActivity.shoppingListController.removeEntry(holder.layoutPosition)
        }
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearList() {
        shoppingList.clear()
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        shoppingList.removeAt(position)
        notifyItemRemoved(position)
    }
}