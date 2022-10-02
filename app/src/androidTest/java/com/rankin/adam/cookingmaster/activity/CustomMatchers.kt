package com.rankin.adam.cookingmaster.activity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import android.widget.TextView
import androidx.test.internal.util.Checks
import com.rankin.adam.cookingmaster.R
import org.hamcrest.Description
import org.hamcrest.Matcher

object CustomMatchers {
    @JvmStatic
    fun findInRecipeBook(subject: String): BoundedMatcher<RecyclerView.ViewHolder?, RecyclerView.ViewHolder> {
        Checks.checkNotNull(subject)
        return object : BoundedMatcher<RecyclerView.ViewHolder?, RecyclerView.ViewHolder>(
            RecyclerView.ViewHolder::class.java
        ) {
            override fun matchesSafely(viewHolder: RecyclerView.ViewHolder): Boolean {
                val subjectTextView =
                    viewHolder.itemView.findViewById<TextView>(R.id.recipe_row_name)
                return subject == subjectTextView.text.toString() && subjectTextView.visibility == View.VISIBLE
            }

            override fun describeTo(description: Description) {
                description.appendText("item with subject: $subject")
            }
        }
    }

    @JvmStatic
    fun findInShoppingList(subject: String): BoundedMatcher<RecyclerView.ViewHolder?, RecyclerView.ViewHolder> {
        Checks.checkNotNull(subject)
        return object : BoundedMatcher<RecyclerView.ViewHolder?, RecyclerView.ViewHolder>(
            RecyclerView.ViewHolder::class.java
        ) {
            override fun matchesSafely(viewHolder: RecyclerView.ViewHolder): Boolean {
                val subjectTextView =
                    viewHolder.itemView.findViewById<TextView>(R.id.shoppingListRowLay_txt_ingredient)
                return subject == subjectTextView.text.toString() && subjectTextView.visibility == View.VISIBLE
            }

            override fun describeTo(description: Description) {
                description.appendText("item with subject: $subject")
            }
        }
    }

    @JvmStatic
    fun findInCookingIngredientsList(subject: String): BoundedMatcher<RecyclerView.ViewHolder?, RecyclerView.ViewHolder> {
        Checks.checkNotNull(subject)
        return object : BoundedMatcher<RecyclerView.ViewHolder?, RecyclerView.ViewHolder>(
            RecyclerView.ViewHolder::class.java
        ) {
            override fun matchesSafely(viewHolder: RecyclerView.ViewHolder): Boolean {
                val subjectTextView =
                    viewHolder.itemView.findViewById<TextView>(R.id.ingrViewRowLay_txt_amount)
                return subject == subjectTextView.text.toString() && subjectTextView.visibility == View.VISIBLE
            }

            override fun describeTo(description: Description) {
                description.appendText("item with subject: $subject")
            }
        }
    }
}