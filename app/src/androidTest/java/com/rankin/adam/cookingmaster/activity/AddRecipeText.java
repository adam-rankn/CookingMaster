package com.rankin.adam.cookingmaster.activity;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.rankin.adam.cookingmaster.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddRecipeText {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addRecipeText() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_recipe_book), withText("RECIPE BOOK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_add_recipe), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.addRecipeAct_txt_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Chicken Gallina"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.addRecipeAct_txt_time),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("45"), closeSoftKeyboard());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.addRecipeAct_txt_allergen_list), withText("No Allergens"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatTextView.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(6);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.addRecipeAct_btn_set_ingredients), withText("SET"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                12),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.ingrDialog_txt_add_ingredient),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.ingrDialog_txt_add_ingredient),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("flour"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.ingrDialog_edt_amount),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.ingrDialog_btn_add_ingredient), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.ingrDialog_edt_amount), withText("2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText6.perform(pressImeActionButton());

        pressBack();

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.ingrDialog_btn_save), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.addRecipeAct_btn_add_recipe), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_recipe_book),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.viewRecipeAct_txt_name), withText("Chicken Gallina"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Chicken Gallina")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.viewRecipeAct_txt_time), withText("45"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("45")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.viewRecipeAct_txt_instructions), withText("Just cook it"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        textView3.check(matches(withText("Just cook it")));

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.viewRecipeAct_btn_view_ingredients), withText("Ingredients"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.ingrViewRowLay_ingredient_name), withText("flour"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ingrViewDialog_recyclerView_ingredients),
                                        0),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("flour")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.ingrViewRowLay_txt_amount), withText("2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ingrViewDialog_recyclerView_ingredients),
                                        0),
                                1),
                        isDisplayed()));
        textView5.check(matches(withText("2")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.ingrViewRowLay_txt_unit), withText("cups"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ingrViewDialog_recyclerView_ingredients),
                                        0),
                                2),
                        isDisplayed()));
        textView6.check(matches(withText("cups")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.ingrViewRowLay_txt_unit), withText("cups"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ingrViewDialog_recyclerView_ingredients),
                                        0),
                                2),
                        isDisplayed()));
        textView7.check(matches(withText("cups")));

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.ingrViewDialog_btn_done), withText("Done"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.viewRecipeAct_btn_view_allergens), withText("Allergens"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton10.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
