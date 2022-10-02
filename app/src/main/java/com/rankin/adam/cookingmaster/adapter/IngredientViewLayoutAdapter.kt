package com.rankin.adam.cookingmaster.adapter

import android.content.Context
import android.os.Handler
import com.rankin.adam.cookingmaster.model.RecipeIngredientEntry
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.rankin.adam.cookingmaster.R
import android.view.ViewGroup
import android.view.LayoutInflater
import com.rankin.adam.cookingmaster.model.ShoppingListEntry
import com.rankin.adam.cookingmaster.activity.MainActivity
import com.rankin.adam.cookingmaster.dialog.AddIngredientToShoppingListDialog
import android.os.Looper
import android.view.View
import android.widget.Button
import java.util.ArrayList

/**
 * Created by Adam on 15-Jul-18.
 */
class IngredientViewLayoutAdapter(
    ingredientList: ArrayList<RecipeIngredientEntry>?,
    context: Context
) : RecyclerView.Adapter<IngredientViewLayoutAdapter.ViewHolder>() {
    private val ingredientList: ArrayList<RecipeIngredientEntry> = ArrayList()
    private val viewRecipeContext: Context
    private var scaleFactor = 1

    init {
        this.ingredientList.addAll(ingredientList!!)
        viewRecipeContext = context
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ingredientName: TextView
        val addToShoppingListButton: Button
        val ingredientAmount: TextView
        val ingredientUnit: TextView

        init {
            itemView.setOnClickListener(this)
            addToShoppingListButton = itemView.findViewById(R.id.ingrViewRowLay_btn_add_to_shop)
            ingredientName = itemView.findViewById(R.id.ingrViewRowLay_ingredient_name)
            ingredientAmount = itemView.findViewById(R.id.ingrViewRowLay_txt_amount)
            ingredientUnit = itemView.findViewById(R.id.ingrViewRowLay_txt_unit)
        }

        override fun onClick(view: View) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_view_row_layout, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeIngredientEntry = ingredientList[position]
        val ingredientName = recipeIngredientEntry.ingredientName
        holder.ingredientName.text = ingredientName
        if (recipeIngredientEntry.amount != null) {
            val amount = recipeIngredientEntry.amount!! * scaleFactor
            val strAmount = amount.toString()
            val unit = recipeIngredientEntry.unit
            holder.ingredientAmount.text = strAmount
            holder.ingredientUnit.text = unit
        }
        holder.addToShoppingListButton.setOnClickListener {
            val shoppingListEntry = ShoppingListEntry(recipeIngredientEntry.ingredient)
            MainActivity.shoppingListController.shoppingListEntry = shoppingListEntry
            val dialog = AddIngredientToShoppingListDialog(viewRecipeContext)
            dialog.show()
        }
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    fun setScaleFactor(factor: Int) {
        scaleFactor = factor
        Handler(Looper.getMainLooper()).post { notifyDataSetChanged() }
    }
}