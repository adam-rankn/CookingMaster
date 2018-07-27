package com.rankin.adam.cookingmaster.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rankin.adam.cookingmaster.Fragments.CookingRecipeFragment;
import com.rankin.adam.cookingmaster.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class CookingRecipesPagerAdapter extends FragmentStatePagerAdapter {

    private final List<CookingRecipeFragment> fragmentList = new ArrayList<>();
    private final List<Recipe> fragmentRecipeList = new ArrayList<>();


    public CookingRecipesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(CookingRecipeFragment fragment, Recipe recipe){
        fragmentList.add(fragment);
        fragmentRecipeList.add(recipe);
    }

    public CookingRecipeFragment getFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public Recipe getRecipe(int position) {
        return fragmentRecipeList.get(position);
    }

    public void removeItemAtPosition(int position){
        fragmentList.remove(position);
        fragmentRecipeList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object item) {
        CookingRecipeFragment fragment = (CookingRecipeFragment)item;
        int position = fragmentList.indexOf(fragment);
        if (position >= 0) {
            return position;
        } else {
            return POSITION_NONE;
        }
    }
}
