package com.rankin.adam.cookingmaster.activity;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.rankin.adam.cookingmaster.R;

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
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 1000000);
        String randomString = Integer.toString(randomNum);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.mainAct_btn_recipe_book), withText("RECIPE BOOK"),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(allOf(withId(R.id.btn_add_recipe), withText("ADD"),
                isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText = onView(allOf(withId(R.id.addRecipeAct_txt_name),
                isDisplayed()));
        appCompatEditText.perform(replaceText("TEST" + randomString), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.addRecipeAct_txt_time),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("47"), closeSoftKeyboard());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.addRecipeAct_txt_allergen_list), withText("No Allergens"),
                        isDisplayed()));
        appCompatTextView.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview))).atPosition(3);
        appCompatCheckedTextView.perform(click());


        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.addRecipeAct_btn_set_ingredients), withText("SET"),
                        isDisplayed()));
        appCompatButton4.perform(click());



        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.ingrDialog_txt_add_ingredient),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("TEST" + randomString), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.ingrDialog_edt_amount),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("2"));


        ViewInteraction appCompatButton5 = onView(allOf( withText("ADD"),
                isDisplayed()));
        appCompatButton5.perform(click());

        onView(withId(R.id.ingrDialog_btn_save)).perform(closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(withText("Save"));
        appCompatButton6.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.addRecipeAct_txt_instructions),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("Just cook it good"));

        ViewInteraction appCompatEditText7 = onView(
                withId(R.id.addRecipeAct_txt_instructions));
        appCompatEditText7.perform(closeSoftKeyboard());


        ViewInteraction appCompatButton7 = onView(  allOf(withId(R.id.addRecipeAct_btn_add_recipe), withText("ADD"),
                isDisplayed()));
        appCompatButton7.perform(click());

        onView(withId(R.id.recycler_recipe_book))
                .perform(RecyclerViewActions.actionOnHolderItem(findInRecipeBook("TEST" + randomString), click()));

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.viewRecipeAct_btn_view_ingredients), withText("Ingredients"),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction textView = onView(allOf(withId(R.id.ingrViewRowLay_ingredient_name), withText("TEST" + randomString) ,
                isDisplayed()));
        textView.check(matches(withText("TEST" + randomString)));

        ViewInteraction textView2 = onView(allOf(withId(R.id.ingrViewRowLay_txt_amount),
                isDisplayed()));
        textView2.check(matches(withText("2.0")));

        ViewInteraction textView3 = onView(allOf(withId(R.id.ingrViewRowLay_txt_unit),
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
                allOf(withId(R.id.viewRecipeAct_btn_delete), withText("DELETE"),
                        isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(android.R.id.button1), withText("Yes")));
        appCompatButton13.perform(scrollTo(), click());

        }


    }

