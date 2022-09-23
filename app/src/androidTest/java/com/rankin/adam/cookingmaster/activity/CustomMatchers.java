package com.rankin.adam.cookingmaster.activity;

import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.internal.util.Checks;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomMatchers {

    public CustomMatchers() {
    }

    public static Matcher<RecyclerView.ViewHolder> findInRecipeBook(final String subject) {
        Checks.checkNotNull(subject);
        return new BoundedMatcher<RecyclerView.ViewHolder, RecyclerView.ViewHolder>(
                RecyclerView.ViewHolder.class) {

            @Override
            protected boolean matchesSafely(RecyclerView.ViewHolder viewHolder) {
                TextView subjectTextView = viewHolder.itemView.findViewById(R.id.recipe_row_name);

                return ((subject.equals(subjectTextView.getText().toString())
                        && (subjectTextView.getVisibility() == View.VISIBLE)));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item with subject: " + subject);
            }
        };
    }

    public static Matcher<RecyclerView.ViewHolder> findInShoppingList(final String subject) {
        Checks.checkNotNull(subject);
        return new BoundedMatcher<RecyclerView.ViewHolder, RecyclerView.ViewHolder>(
                RecyclerView.ViewHolder.class) {

            @Override
            protected boolean matchesSafely(RecyclerView.ViewHolder viewHolder) {
                TextView subjectTextView = viewHolder.itemView.findViewById(R.id.shoppingListRowLay_txt_ingredient);

                return ((subject.equals(subjectTextView.getText().toString())
                        && (subjectTextView.getVisibility() == View.VISIBLE)));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item with subject: " + subject);
            }
        };
    }

    public static Matcher<RecyclerView.ViewHolder> findInCookingIngredientsList(final String subject) {
        Checks.checkNotNull(subject);
        return new BoundedMatcher<RecyclerView.ViewHolder, RecyclerView.ViewHolder>(
                RecyclerView.ViewHolder.class) {

            @Override
            protected boolean matchesSafely(RecyclerView.ViewHolder viewHolder) {
                TextView subjectTextView = viewHolder.itemView.findViewById(R.id.ingrViewRowLay_txt_amount);

                return ((subject.equals(subjectTextView.getText().toString())
                        && (subjectTextView.getVisibility() == View.VISIBLE)));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item with subject: " + subject);
            }
        };
    }
}
