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
class TestRecipePinning {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity?>? = ActivityTestRule(
        MainActivity::class.java
    )
    //var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    // temp workaround while waiting for android test bugfix
    @Test
    fun testRecipePinning() {
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
        appCompatEditText.perform(replaceText("recipe 1"), closeSoftKeyboard())

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
        appCompatEditText2.perform(replaceText("1"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.addRecipeAct_txt_time), withText("1"),
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
        appCompatEditText3.perform(pressImeActionButton())

        val appCompatButton3 = onView(
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
        appCompatButton3.perform(click())

        val appCompatButton4 = onView(
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
        appCompatButton4.perform(click())

        val appCompatEditText4 = onView(
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
        appCompatEditText4.perform(replaceText("recipe 2"), closeSoftKeyboard())

        val appCompatEditText5 = onView(
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
        appCompatEditText5.perform(replaceText("2"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.addRecipeAct_txt_time), withText("2"),
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
        appCompatEditText6.perform(pressImeActionButton())

        val appCompatButton5 = onView(
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
        appCompatButton5.perform(click())

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

        val appCompatButton6 = onView(
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
        appCompatButton6.perform(click())

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

        val appCompatImageButton2 = onView(
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
        appCompatImageButton2.perform(click())

        val recyclerView2 = onView(
            allOf(
                withId(R.id.recycler_recipe_book),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

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
                withId(R.id.cookingAct_btn_prev), withText("Previous Recipe"),
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
        appCompatButton8.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.cookRecipeFrag_txt_title), withText("recipe 1"),
                withParent(withParent(withId(R.id.cookingAct_container))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("recipe 1")))

        val appCompatButton9 = onView(
            allOf(
                withId(R.id.cookingAct_btn_next), withText("Next Recipe"),
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
        appCompatButton9.perform(click())

        val textView2 = onView(
            allOf(
                withId(R.id.cookRecipeFrag_txt_title), withText("recipe 2"),
                withParent(withParent(withId(R.id.cookingAct_container))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("recipe 2")))

        val appCompatImageButton3 = onView(
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
        appCompatImageButton3.perform(click())

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

        val recyclerView3 = onView(
            allOf(
                withId(R.id.recycler_recipe_book),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val appCompatButton12 = onView(
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
        appCompatButton12.perform(click())

        val appCompatButton13 = onView(
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
        appCompatButton13.perform(scrollTo(), click())
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
