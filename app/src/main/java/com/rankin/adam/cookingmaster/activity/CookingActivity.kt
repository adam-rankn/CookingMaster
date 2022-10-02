package com.rankin.adam.cookingmaster.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.rankin.adam.cookingmaster.R
import com.rankin.adam.cookingmaster.adapter.CookingRecipesPagerAdapter
import com.rankin.adam.cookingmaster.fragments.CookingRecipeFragment

class CookingActivity : AppCompatActivity() {
    private var cookingRecipesPagerAdapter: CookingRecipesPagerAdapter? = null
    private var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cooking)

        //cookingRecipesPagerAdapter = new CookingRecipesPagerAdapter(getSupportFragmentManager());
        val viewPager = findViewById<ViewPager>(R.id.cookingAct_container)

        //setup pager
        setupViewPager(viewPager)
        val prevButton = findViewById<Button>(R.id.cookingAct_btn_prev)
        val nextButton = findViewById<Button>(R.id.cookingAct_btn_next)
        prevButton.setOnClickListener {
            removeCurrentRecipeIfUnpinned()

            //go to prev recipe
            val prevRecipe = viewPager.currentItem - 1
            val size = cookingRecipesPagerAdapter!!.count
            viewPager.currentItem = prevRecipe
        }
        nextButton.setOnClickListener {
            removeCurrentRecipeIfUnpinned()
            val nextRecipe = viewPager.currentItem + 1
            val size = cookingRecipesPagerAdapter!!.count

            //if (nextRecipe > size - 1){
            //    nextRecipe = 0;
            //}
            //go to nest recipe
            viewPager.currentItem = nextRecipe
        }
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        cookingRecipesPagerAdapter = CookingRecipesPagerAdapter(supportFragmentManager)
        val currentRecipe = MainActivity.recipeController.currentRecipe
        if (!MainActivity.recipeController.isRecipePinned(currentRecipe)) {
            MainActivity.recipeController.pinRecipe(currentRecipe)
        }
        val currentPos =
            MainActivity.recipeController.pinnedRecipes.indexOf(MainActivity.recipeController.currentRecipe)
        for (i in MainActivity.recipeController.pinnedRecipes.indices) {
            val recipe = MainActivity.recipeController.pinnedRecipes[i]
            val fragment = CookingRecipeFragment()
            val bundle = Bundle()
            bundle.putInt("recipe", i)
            fragment.arguments = bundle
            cookingRecipesPagerAdapter!!.addFragment(fragment, recipe)
        }
        viewPager!!.adapter = cookingRecipesPagerAdapter
        viewPager.currentItem = currentPos
    }

    fun removeCurrentRecipeIfUnpinned() {
        val index = viewPager!!.currentItem
        val adapter = viewPager!!.adapter as CookingRecipesPagerAdapter?
        val fragment = adapter!!.getFragment(index)
        if (!fragment.pinned) {
            adapter.removeItemAtPosition(index)
            adapter.notifyDataSetChanged()
            MainActivity.recipeController.unpinRecipe(MainActivity.recipeController.pinnedRecipes[index])
        }
    }

    override fun onBackPressed() {
        removeCurrentRecipeIfUnpinned()
        super.onBackPressed()
    }
}