package com.rankin.adam.cookingmaster.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
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

import java.util.concurrent.ThreadLocalRandom;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.rankin.adam.cookingmaster.activity.CustomMatchers.findInCookingIngredientsList;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeScaleTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recipeScaleTest() {
        Integer randomNum = ThreadLocalRandom.current().nextInt(100000, 1000000);
        String randomString = randomNum.toString();

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.mainAct_btn_recipe_book), withText("RECIPE BOOK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_add_recipe), withText("Add"),
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
        appCompatEditText.perform(replaceText("TEST" + randomString), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.addRecipeAct_txt_time),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("30"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.addRecipeAct_btn_set_ingredients), withText("Set"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                12),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.ingrDialog_txt_add_ingredient),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("TEST" + randomString), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.ingrDialog_edt_amount),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.ingrDialog_btn_add_ingredient), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        onView(withId(R.id.ingrDialog_btn_save)).perform(closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.ingrDialog_btn_save), withText("Save"),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.addRecipeAct_btn_add_recipe), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_recipe_book),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.viewRecipeAct_btn_cook), withText("Cook"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.cookRecipeFrag_btn_scale), withText("Scale"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction editText = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.custom),
                                childAtPosition(
                                        withId(R.id.customPanel),
                                        0)),
                        0),
                        isDisplayed()));
        editText.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton9.perform(scrollTo(), click());

        onView(withId(R.id.cookRecipeFrag_recycler_ingredients))
                .perform(RecyclerViewActions.actionOnHolderItem(findInCookingIngredientsList("9"), click()));

        pressBack();

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.viewRecipeAct_btn_delete), withText("DELETE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton11.perform(scrollTo(), click());

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
