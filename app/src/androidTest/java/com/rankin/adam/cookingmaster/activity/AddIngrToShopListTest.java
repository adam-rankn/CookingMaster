package com.rankin.adam.cookingmaster.activity;


import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.internal.util.Checks;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.filters.LargeTest;
import android.view.View;
import android.widget.TextView;

import com.rankin.adam.cookingmaster.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.rankin.adam.cookingmaster.activity.CustomMatchers.findInRecipeBook;
import static com.rankin.adam.cookingmaster.activity.CustomMatchers.findInShoppingList;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddIngrToShopListTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addIngrToShopListTest() {
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 1000000);
        String randomString = Integer.toString(randomNum);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.mainAct_btn_recipe_book), withText("RECIPE BOOK"),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btn_add_recipe), withText("ADD"),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.addRecipeAct_txt_name),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("TEST" + randomString), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.addRecipeAct_txt_time),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("11"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.addRecipeAct_btn_set_ingredients), withText("SET"),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.ingrDialog_txt_add_ingredient),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.ingrDialog_txt_add_ingredient),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("TEST" + randomString), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.ingrDialog_edt_amount),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("77"), closeSoftKeyboard());


        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.ingrDialog_btn_add_ingredient), withText("ADD"),
                        isDisplayed()));
        appCompatButton5.perform(click());

        onView(withId(R.id.ingrDialog_btn_save)).perform(closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.ingrDialog_btn_save), isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.addRecipeAct_btn_add_recipe), withText("ADD"),
                        isDisplayed()));
        appCompatButton7.perform(click());

        onView(withId(R.id.recycler_recipe_book))
                .perform(RecyclerViewActions.actionOnHolderItem(findInRecipeBook("TEST" + randomString), click()));

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.viewRecipeAct_btn_view_ingredients),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.ingrViewRowLay_btn_add_to_shop), withText("list"),
                        isDisplayed()));
        appCompatButton9.perform(click());


        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.addIngrShopListDialog_btn_add), withText("ADD"),
                        isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.ingrViewDialog_btn_done), withText("Done"),
                        isDisplayed()));
        appCompatButton11.perform(click());

        pressBack();

        pressBack();

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.mainAct_btn_shopping), withText("Shopping List")) );
        appCompatButton12.perform(click());

        onView(withId(R.id.shoppingListAct_recyclerview))
                .perform(RecyclerViewActions.actionOnHolderItem(findInShoppingList("TEST" + randomString), click()));


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.shoppingListAct_btn_clear), withText("Clear List"),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(android.R.id.button1), withText("OK")
                        ));
        appCompatButton15.perform(scrollTo(), click());

        pressBack();

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.mainAct_btn_recipe_book), withText("RECIPE BOOK"),
                        isDisplayed()));
        appCompatButton13.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_recipe_book)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.viewRecipeAct_btn_delete), withText("DELETE"),

                        isDisplayed()));
        appCompatButton14.perform(click());

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(android.R.id.button1), withText("Yes")));
        appCompatButton16.perform(scrollTo(), click());

    }

    public static Matcher<RecyclerView.ViewHolder> withItemSubject(final String subject) {
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
}
