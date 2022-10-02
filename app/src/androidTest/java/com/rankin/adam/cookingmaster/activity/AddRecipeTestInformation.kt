package com.rankin.adam.cookingmaster.activity


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.rankin.adam.cookingmaster.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class AddRecipeTestInformation {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity?>? = ActivityTestRule(
        MainActivity::class.java
    )

    //var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun addRecipeTestInformation() {
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
        appCompatEditText.perform(replaceText("pizza"), closeSoftKeyboard())

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
        appCompatEditText2.perform(replaceText("35"), closeSoftKeyboard())

        val appCompatTextView = onView(
            allOf(
                withId(R.id.addRecipeAct_txt_allergen_list), withText("No Allergens"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val appCompatCheckedTextView = onData(anything())
            .inAdapterView(
                allOf(
                    withId(com.google.android.material.R.id.select_dialog_listview),
                    childAtPosition(
                        withId(com.google.android.material.R.id.contentPanel),
                        0
                    )
                )
            )
            .atPosition(2)
        appCompatCheckedTextView.perform(click())

        val appCompatButton3 = onView(
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
        appCompatButton3.perform(scrollTo(), click())

        val appCompatButton4 = onView(
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
        appCompatButton4.perform(click())

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
        appCompatEditText4.perform(replaceText("5"), closeSoftKeyboard())

        val appCompatButton5 = onView(
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
        appCompatButton5.perform(click())

        val appCompatEditText5 = onView(
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
        appCompatEditText5.perform(replaceText("sausage"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
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
        appCompatEditText6.perform(replaceText("3"), closeSoftKeyboard())

        val appCompatButton6 = onView(
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
        appCompatButton6.perform(click())

        val appCompatButton7 = onView(
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
        appCompatButton7.perform(click())

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.addRecipeAct_txt_instructions),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    9
                ),
                isDisplayed()
            )
        )
        appCompatEditText7.perform(replaceText("bake in oven"), closeSoftKeyboard())

        val appCompatButton8 = onView(
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
        appCompatButton8.perform(click())

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

        val textView = onView(
            allOf(
                withId(R.id.viewRecipeAct_txt_name), withText("pizza"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("pizza")))

        val textView2 = onView(
            allOf(
                withId(R.id.viewRecipeAct_txt_time), withText("35"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("35")))

        val textView3 = onView(
            allOf(
                withId(R.id.viewRecipeAct_edt_instructions), withText("bake in oven"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("bake in oven")))

        val appCompatButton9 = onView(
            allOf(
                withId(R.id.viewRecipeAct_btn_view_allergens), withText("Allergens"),
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
        appCompatButton9.perform(click())

        val textView4 = onView(
            allOf(
                withId(android.R.id.text1), withText("Dairy"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.select_dialog_listview),
                        withParent(withId(com.google.android.material.R.id.contentPanel))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Dairy")))

        val appCompatButton10 = onView(
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
        appCompatButton10.perform(scrollTo(), click())

        val appCompatButton11 = onView(
            allOf(
                withId(R.id.viewRecipeAct_btn_view_ingredients), withText("Ingredients"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatButton11.perform(click())

        val textView5 = onView(
            allOf(
                withId(R.id.ingrViewRowLay_ingredient_name), withText("cheese"),
                withParent(withParent(withId(R.id.ingrViewDialog_recyclerView_ingredients))),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("cheese")))

        val textView6 = onView(
            allOf(
                withId(R.id.ingrViewRowLay_txt_amount), withText("5.0"),
                withParent(withParent(withId(R.id.ingrViewDialog_recyclerView_ingredients))),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("5.0")))

        val appCompatButton12 = onView(
            allOf(
                withId(R.id.ingrViewDialog_btn_done), withText("Done"),
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
        appCompatButton12.perform(click())

        val appCompatButton13 = onView(
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
        appCompatButton13.perform(click())

        val appCompatButton14 = onView(
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
        appCompatButton14.perform(scrollTo(), click())
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
