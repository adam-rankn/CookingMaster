package com.rankin.adam.cookingmaster.activity


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
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
class TimerTest {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity?>? = ActivityTestRule(
        MainActivity::class.java
    )
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun timerTest2ElectricBoogaloo() {
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
        appCompatEditText.perform(replaceText("timer test"), closeSoftKeyboard())

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
        appCompatEditText2.perform(replaceText("5"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.addRecipeAct_txt_time), withText("5"),
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

        val appCompatButton4 = onView(
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
        appCompatButton4.perform(click())

        val appCompatButton5 = onView(
            allOf(
                withId(R.id.cookRecipeFrag_btn_start_timer), withText("Timer"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout2),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            4
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatButton5.perform(click())

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
        editText.perform(replaceText("5"), closeSoftKeyboard())

        val appCompatButton6 = onView(
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
        appCompatButton6.perform(scrollTo(), click())


        val textView1 = onView(withText("timer test")).inRoot(isPopupWindow())

        textView1.check(ViewAssertions.matches(withText("timer test")))


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

    private fun isPopupWindow(): Matcher<Root> {
        return isPlatformPopup()
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
