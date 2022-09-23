package com.rankin.adam.cookingmaster.activity;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.rankin.adam.cookingmaster.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

import static com.rankin.adam.cookingmaster.activity.CustomMatchers.findInRecipeBook;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddRecipeTestIngredients {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addRecipeTestIngredients() {
        Integer randomNum = ThreadLocalRandom.current().nextInt(100000, 1000000);
        String randomString = randomNum.toString();

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.mainAct_btn_recipe_book), withText("RECIPE BOOK"),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(allOf(withId(R.id.btn_add_recipe), withText("ADD"),
                childAtPosition(childAtPosition(withId(android.R.id.content), 0), 4),
                isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.addRecipeAct_txt_name),
                childAtPosition(childAtPosition(withId(android.R.id.content), 0),5),
                isDisplayed()));
        appCompatEditText.perform(replaceText("TEST" + randomString), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.addRecipeAct_txt_time),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("47"), closeSoftKeyboard());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.addRecipeAct_txt_allergen_list), withText("No Allergens"),
                        childAtPosition(childAtPosition(
                                withId(android.R.id.content), 0),
                                7),
                        isDisplayed()));
        appCompatTextView.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview), childAtPosition(
                        withId(R.id.contentPanel), 0))).atPosition(3);
        appCompatCheckedTextView.perform(click());


        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton3.perform(scrollTo(), click());

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
        appCompatEditText5.perform(replaceText("2"));


        ViewInteraction appCompatButton5 = onView(allOf(withId(R.id.ingrDialog_btn_add_ingredient), withText("ADD"),
                isDisplayed()));
        appCompatButton5.perform(closeSoftKeyboard(),click());

        onView(withId(R.id.ingrDialog_btn_save)).perform(closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(withText("Save"));
        appCompatButton6.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.addRecipeAct_txt_instructions),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content), 0), 9),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("Just cook it good"));

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.addRecipeAct_txt_instructions), withText("Just cook it good"),
                        isDisplayed()));
        appCompatEditText7.perform(closeSoftKeyboard());


        ViewInteraction appCompatButton7 = onView(  allOf(withId(R.id.addRecipeAct_btn_add_recipe), withText("ADD"),
                childAtPosition(
                        childAtPosition(withId(android.R.id.content), 0), 0),
                isDisplayed()));
        appCompatButton7.perform(click());

        onView(withId(R.id.recycler_recipe_book))
                .perform(RecyclerViewActions.actionOnHolderItem(findInRecipeBook("TEST" + randomString), click()));

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.viewRecipeAct_btn_view_ingredients), withText("Ingredients"),
                        childAtPosition(
                                childAtPosition(withId(android.R.id.content), 0), 4),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction textView = onView(allOf(withId(R.id.ingrViewRowLay_ingredient_name), withText("TEST" + randomString),
                childAtPosition(
                        childAtPosition(withId(R.id.ingrViewDialog_recyclerView_ingredients),
                                0),
                        0),
                isDisplayed()));
        textView.check(matches(withText("TEST" + randomString)));

        ViewInteraction textView2 = onView(allOf(withId(R.id.ingrViewRowLay_txt_amount),
                withText("2"),
                isDisplayed()));
        textView2.check(matches(withText("2")));

        ViewInteraction textView3 = onView(allOf(withId(R.id.ingrViewRowLay_txt_unit),
                withText("cups"),
                isDisplayed()));
        textView3.check(matches(withText("cups")));

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.ingrViewDialog_btn_done), withText("Done"),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.viewRecipeAct_btn_view_allergens), withText("Allergens"),
                        isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(android.R.id.text1), withText("Peanuts"),
                        isDisplayed()));
        textView4.check(matches(withText("Peanuts")));

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.viewRecipeAct_btn_delete), withText("DELETE"), childAtPosition(
                        childAtPosition(withId(android.R.id.content), 0), 10),
                        isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton13.perform(scrollTo(), click());

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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
