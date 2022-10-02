package com.rankin.adam.cookingmaster.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rankin.adam.cookingmaster.fragments.CookingRecipeFragment
import com.rankin.adam.cookingmaster.model.Recipe

class CookingRecipesPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(
    fm!!
) {
    private val fragmentList: MutableList<CookingRecipeFragment> = ArrayList()
    private val fragmentRecipeList: MutableList<Recipe> = ArrayList()
    fun addFragment(fragment: CookingRecipeFragment, recipe: Recipe) {
        fragmentList.add(fragment)
        fragmentRecipeList.add(recipe)
    }

    fun getFragment(position: Int): CookingRecipeFragment {
        return fragmentList[position]
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun getRecipe(position: Int): Recipe {
        return fragmentRecipeList[position]
    }

    fun removeItemAtPosition(position: Int) {
        fragmentList.removeAt(position)
        fragmentRecipeList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemPosition(item: Any): Int {
        val fragment = item as CookingRecipeFragment
        val position = fragmentList.indexOf(fragment)
        return if (position >= 0) {
            position
        } else {
            POSITION_NONE
        }
    }
}