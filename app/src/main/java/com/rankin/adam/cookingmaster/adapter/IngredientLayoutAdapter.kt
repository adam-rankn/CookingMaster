package com.rankin.adam.cookingmaster.adapter

import android.content.Context
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.rankin.adam.cookingmaster.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.rankin.adam.cookingmaster.activity.MainActivity
import java.util.ArrayList

/**
 * Created by Adam on 10-Jul-18.
 */
class IngredientLayoutAdapter(
    ingredientList: ArrayList<RecipeIngredientEntry>?,
    context: Context?
) : RecyclerView.Adapter<IngredientLayoutAdapter.ViewHolder>() {
    private val ingredientList: ArrayList<RecipeIngredientEntry> = ArrayList()

    init {
        this.ingredientList.addAll(ingredientList!!)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ingredientName: TextView
        val deleteButton: Button
        val amount: TextView
        val unit: TextView

        init {
            itemView.setOnClickListener(this)
            ingredientName = itemView.findViewById(R.id.ingrRow_name)
            amount = itemView.findViewById(R.id.ingrRow_txt_amount)
            unit = itemView.findViewById(R.id.ingrRow_txt_unit)
            deleteButton = itemView.findViewById(R.id.ingrDialog_btn_delete_ingredient)
        }

        override fun onClick(view: View) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_row_layout, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeIngredientEntry = ingredientList[position]
        val name = recipeIngredientEntry.ingredientName
        holder.ingredientName.text = name
        if (recipeIngredientEntry.amount != null) {
            val amount = recipeIngredientEntry.amount.toString()
            holder.amount.text = amount
            val unit = recipeIngredientEntry.unit
            holder.unit.text = unit
        }
        holder.deleteButton.setOnClickListener {
            MainActivity.recipeController.removeIngredient(position)
            removeIngredient(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    fun addIngredient(ingredient: RecipeIngredientEntry) {
        ingredientList.add(ingredient)
    }

    fun removeIngredient(position: Int) {
        ingredientList.removeAt(position)
    }
}