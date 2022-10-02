package com.rankin.adam.cookingmaster.activity


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.rankin.adam.cookingmaster.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ScaleIngedientsTest {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity?>? = ActivityTestRule(
        MainActivity::class.java
    )
    //var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    //temp workaround until 5.0alpha02 bugfix
    @Test
    fun scaleIngedientsTest() {
        val appCompatButton = onView(
            allOf(
                withId(R.id.mainAct_btn_recipe_book), withText("RECIPE BOOK"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.btn_add_recipe), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.addRecipeAct_txt_name),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("cheese"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.addRecipeAct_txt_time),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("2"), closeSoftKeyboard())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.addRecipeAct_btn_set_ingredients), withText("Set"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    12
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.ingrDialog_txt_add_ingredient),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("cheese"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.ingrDialog_edt_amount),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText("2"), closeSoftKeyboard())

        val appCompatButton4 = onView(
            allOf(
                withId(R.id.ingrDialog_btn_add_ingredient), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatButton4.perform(click())

        val appCompatButton5 = onView(
            allOf(
                withId(R.id.ingrDialog_btn_save), withText("Save"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatButton5.perform(click())

        val appCompatButton6 = onView(
            allOf(
                withId(R.id.addRecipeAct_btn_add_recipe), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton6.perform(click())

        val recyclerView = onView(
            allOf(
                withId(R.id.recycler_recipe_book),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val appCompatButton7 = onView(
            allOf(
                withId(R.id.viewRecipeAct_btn_cook), withText("Cook"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    11
                ),
                isDisplayed()
            )
        )
        appCompatButton7.perform(click())

        val appCompatButton8 = onView(
            allOf(
                withId(R.id.cookRecipeFrag_btn_scale), withText("Scale"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout2),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            4
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton8.perform(click())

        val editText = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(com.google.android.material.R.id.custom),
                        childAtPosition(
                            withId(com.google.android.material.R.id.customPanel),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText.perform(replaceText("2"), closeSoftKeyboard())

        val appCompatButton9 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withId(com.google.android.material.R.id.buttonPanel),
                        0
                    ),
                    3
                )
            )
        )
        appCompatButton9.perform(scrollTo(), click())

        val textView = onView(
            allOf(
                withId(R.id.ingrViewRowLay_txt_amount), withText("4.0"),
                withParent(withParent(withId(R.id.cookRecipeFrag_recycler_ingredients))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("4.0")))

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(com.google.android.material.R.id.action_bar),
                        childAtPosition(
                            withId(com.google.android.material.R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val appCompatButton10 = onView(
            allOf(
                withId(R.id.viewRecipeAct_btn_delete), withText("Delete"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        appCompatButton10.perform(click())

        val appCompatButton11 = onView(
            allOf(
                withId(android.R.id.button1), withText("Yes"),
                childAtPosition(
                    childAtPosition(
                        withId(com.google.android.material.R.id.buttonPanel),
                        0
                    ),
                    3
                )
            )
        )
        appCompatButton11.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
