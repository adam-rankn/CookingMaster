package com.rankin.adam.cookingmaster.activity;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.internal.util.Checks;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomMatches {

    public CustomMatches() {
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
}
