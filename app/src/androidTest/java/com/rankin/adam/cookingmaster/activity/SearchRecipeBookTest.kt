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
class SearchRecipeBookTest {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity?>? = ActivityTestRule(
            MainActivity::class.java
    )
    //var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
        // workaround while waiting for https://github.com/android/android-test/issues/1412
    @Test
    fun searchRecipeBookTest() {
        val appCompatButton = onView(
allOf(withId(R.id.mainAct_btn_recipe_book), withText("RECIPE BOOK"),isDisplayed()))
        appCompatButton.perform(click())

        val appCompatButton2 = onView(
allOf(withId(R.id.btn_add_recipe), withText("Add"), isDisplayed()))
        appCompatButton2.perform(click())

        val appCompatTextView = onView(
allOf(withId(R.id.addRecipeAct_txt_allergen_list), withText("No Allergens"), isDisplayed()))
        appCompatTextView.perform(click())

        val appCompatCheckedTextView = onData(anything())
.inAdapterView(allOf(withId(com.google.android.material.R.id.select_dialog_listview),
childAtPosition(
withId(com.google.android.material.R.id.contentPanel),
0)))
.atPosition(0)
        appCompatCheckedTextView.perform(click())

        val appCompatButton3 = onView(
allOf(withId(android.R.id.button1), withText("OK")))
        appCompatButton3.perform(scrollTo(), click())

        val appCompatEditText = onView(
allOf(withId(R.id.addRecipeAct_txt_name), isDisplayed()))
        appCompatEditText.perform(replaceText("soy meal"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
allOf(withId(R.id.addRecipeAct_txt_time), isDisplayed()))
        appCompatEditText2.perform(replaceText("30"), closeSoftKeyboard())

        val appCompatButton4 = onView(
allOf(withId(R.id.addRecipeAct_btn_add_recipe), withText("Add"), isDisplayed()))
        appCompatButton4.perform(click())

        val appCompatButton5 = onView(
allOf(withId(R.id.btn_add_recipe), withText("Add"), isDisplayed()))
        appCompatButton5.perform(click())

        val appCompatEditText3 = onView(
allOf(withId(R.id.addRecipeAct_txt_name), isDisplayed()))
        appCompatEditText3.perform(replaceText("PB and J"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
allOf(withId(R.id.addRecipeAct_txt_time), isDisplayed()))
        appCompatEditText4.perform(replaceText("5"), closeSoftKeyboard())

        val appCompatTextView2 = onView(
allOf(withId(R.id.addRecipeAct_txt_allergen_list), withText("No Allergens"), isDisplayed()))
        appCompatTextView2.perform(click())

        val appCompatCheckedTextView2 = onData(anything())
.inAdapterView(allOf(withId(com.google.android.material.R.id.select_dialog_listview),
childAtPosition(
withId(com.google.android.material.R.id.contentPanel),
0)))
.atPosition(3)
        appCompatCheckedTextView2.perform(click())

        val appCompatButton6 = onView(
allOf(withId(android.R.id.button1), withText("OK")))
        appCompatButton6.perform(scrollTo(), click())

        val appCompatEditText5 = onView(
allOf(withId(R.id.addRecipeAct_txt_time), withText("5"), isDisplayed()))
        appCompatEditText5.perform(pressImeActionButton())

        val appCompatButton7 = onView(
allOf(withId(R.id.addRecipeAct_btn_add_recipe), withText("Add"), isDisplayed()))
        appCompatButton7.perform(click())

        val appCompatButton8 = onView(
allOf(withId(R.id.btn_add_recipe), withText("Add"), isDisplayed()))
        appCompatButton8.perform(click())

        val appCompatEditText6 = onView(
allOf(withId(R.id.addRecipeAct_txt_name), isDisplayed()))
        appCompatEditText6.perform(replaceText("chicken"), closeSoftKeyboard())

        val appCompatEditText7 = onView(
allOf(withId(R.id.addRecipeAct_txt_time), isDisplayed()))
        appCompatEditText7.perform(replaceText("60"), closeSoftKeyboard())

        val appCompatButton9 = onView(
allOf(withId(R.id.addRecipeAct_btn_set_ingredients), withText("Set"), isDisplayed()))
        appCompatButton9.perform(click())

        val appCompatEditText8 = onView(
allOf(withId(R.id.ingrDialog_txt_add_ingredient), isDisplayed()))
        appCompatEditText8.perform(replaceText("chicken"), closeSoftKeyboard())

        val appCompatEditText9 = onView(
allOf(withId(R.id.ingrDialog_edt_amount), isDisplayed()))
        appCompatEditText9.perform(replaceText("1"), closeSoftKeyboard())

        val appCompatButton10 = onView(
allOf(withId(R.id.ingrDialog_btn_add_ingredient), withText("Add"), isDisplayed()))
        appCompatButton10.perform(click())

        val appCompatEditText10 = onView(
allOf(withId(R.id.ingrDialog_txt_add_ingredient), isDisplayed()))
        appCompatEditText10.perform(replaceText("beef"), closeSoftKeyboard())

        val appCompatEditText11 = onView(
allOf(withId(R.id.ingrDialog_edt_amount), isDisplayed()))
        appCompatEditText11.perform(replaceText("1"), closeSoftKeyboard())

        val appCompatButton11 = onView(
allOf(withId(R.id.ingrDialog_btn_add_ingredient), withText("Add"), isDisplayed()))
        appCompatButton11.perform(click())

        val appCompatEditText12 = onView(
allOf(withId(R.id.ingrDialog_txt_add_ingredient), isDisplayed()))
        appCompatEditText12.perform(replaceText("lamb"), closeSoftKeyboard())

        val appCompatEditText13 = onView(
allOf(withId(R.id.ingrDialog_edt_amount), isDisplayed()))
        appCompatEditText13.perform(replaceText("1"), closeSoftKeyboard())

        val appCompatButton12 = onView(
allOf(withId(R.id.ingrDialog_btn_add_ingredient), withText("Add"), isDisplayed()))
        appCompatButton12.perform(click())

        val appCompatButton13 = onView(
allOf(withId(R.id.ingrDialog_btn_save), withText("Save"), isDisplayed()))
        appCompatButton13.perform(click())

        val appCompatButton14 = onView(
allOf(withId(R.id.addRecipeAct_btn_add_recipe), withText("Add"), isDisplayed()))
        appCompatButton14.perform(click())

        val appCompatButton15 = onView(
allOf(withId(R.id.recipeBookAct_btn_search), withText("Search"), isDisplayed()))
        appCompatButton15.perform(click())

        val appCompatEditText14 = onView(
allOf(withId(R.id.searchRecipesDialog_edt_max_time), isDisplayed()))
        appCompatEditText14.perform(replaceText("25"), closeSoftKeyboard())

        val appCompatButton16 = onView(
allOf(withId(R.id.searchRecipesDialog_btn_search), withText("Search"), isDisplayed()))
        appCompatButton16.perform(click())

        val textView = onView(
allOf(withId(R.id.recipe_row_name), withText("PB and J"),
withParent(withParent(withId(R.id.recycler_recipe_book))),
isDisplayed()))
        textView.check(matches(withText("PB and J")))

        val appCompatButton17 = onView(
allOf(withId(R.id.recipeBookAct_btn_search), withText("Search"), isDisplayed()))
        appCompatButton17.perform(click())

        val appCompatEditText15 = onView(
allOf(withId(R.id.searchRecipesDialog_edt_add_ingredient_1), isDisplayed()))
        appCompatEditText15.perform(replaceText("chicken"), closeSoftKeyboard())

        val appCompatEditText16 = onView(
allOf(withId(R.id.searchRecipesDialog_edt_add_ingredient_2), isDisplayed()))
        appCompatEditText16.perform(replaceText("beef"), closeSoftKeyboard())

        val appCompatEditText17 = onView(
allOf(withId(R.id.searchRecipesDialog_edt_add_ingredient_3), isDisplayed()))
        appCompatEditText17.perform(replaceText("lamb"), closeSoftKeyboard())

        val appCompatButton18 = onView(
allOf(withId(R.id.searchRecipesDialog_btn_search), withText("Search"), isDisplayed()))
        appCompatButton18.perform(click())

        val textView2 = onView(
allOf(withId(R.id.recipe_row_name), withText("chicken"),
withParent(withParent(withId(R.id.recycler_recipe_book))),
isDisplayed()))
        textView2.check(matches(withText("chicken")))

        val appCompatButton19 = onView(
allOf(withId(R.id.recipeBookAct_btn_search), withText("Search"), isDisplayed()))
        appCompatButton19.perform(click())

        val appCompatTextView3 = onView(
allOf(withId(R.id.searchRecipesDialog_txt_allergens), withText("None"), isDisplayed()))
        appCompatTextView3.perform(click())

        val appCompatCheckedTextView3 = onData(anything())
.inAdapterView(allOf(withId(com.google.android.material.R.id.select_dialog_listview),
childAtPosition(
withId(com.google.android.material.R.id.contentPanel),
0)))
.atPosition(0)
        appCompatCheckedTextView3.perform(click())

        val appCompatButton20 = onView(
allOf(withId(android.R.id.button1), withText("OK"),
childAtPosition(
childAtPosition(
withId(com.google.android.material.R.id.buttonPanel),
0),
3)))
        appCompatButton20.perform(scrollTo(), click())

        val appCompatButton21 = onView(
allOf(withId(R.id.searchRecipesDialog_btn_search), withText("Search"), isDisplayed()))
        appCompatButton21.perform(click())

        val textView3 = onView(
allOf(withId(R.id.recipe_row_name), withText("PB and J"),
withParent(withParent(withId(R.id.recycler_recipe_book))),
isDisplayed()))
        textView3.check(matches(withText("PB and J")))

        val recyclerView2 = onView(
allOf(withId(R.id.recycler_recipe_book),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
1)))
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val appCompatButton24 = onView(
allOf(withId(R.id.viewRecipeAct_btn_delete), withText("Delete"), isDisplayed()))
        appCompatButton24.perform(click())

        val appCompatButton25 = onView(
allOf(withId(android.R.id.button1), withText("Yes"),
childAtPosition(
childAtPosition(
withId(com.google.android.material.R.id.buttonPanel),
0),
3)))
        appCompatButton25.perform(scrollTo(), click())

        val recyclerView3 = onView(
allOf(withId(R.id.recycler_recipe_book),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
1)))
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val appCompatButton26 = onView(
allOf(withId(R.id.viewRecipeAct_btn_delete), withText("Delete"), isDisplayed()))
        appCompatButton26.perform(click())

        val appCompatButton27 = onView(
allOf(withId(android.R.id.button1), withText("Yes"),
childAtPosition(
childAtPosition(
withId(com.google.android.material.R.id.buttonPanel),
0),
3)))
        appCompatButton27.perform(scrollTo(), click())

        val recyclerView4 = onView(
allOf(withId(R.id.recycler_recipe_book),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
1)))
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val appCompatButton28 = onView(
allOf(withId(R.id.viewRecipeAct_btn_delete), withText("Delete"), isDisplayed()))
        appCompatButton28.perform(click())

        val appCompatButton29 = onView(
allOf(withId(android.R.id.button1), withText("Yes")))
        appCompatButton29.perform(scrollTo(), click())
        }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

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
