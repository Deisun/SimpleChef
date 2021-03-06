package com.example.simplechef.ui.home;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "ViewPagerAdapter";
    private String [] tabTitles = new String [] { "All Recipes", "Favorites", "My Recipes"};
    boolean isSecond = false;

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    private int selected = 0;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public AllRecipesFragment allRecipesFragment = AllRecipesFragment.newInstance();
    public FavoriteRecipesFragment favoriteRecipesFragment = FavoriteRecipesFragment.newInstance();
    public MyRecipesFragment myRecipesFragment = MyRecipesFragment.newInstance();

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                if (isSecond) {
                    selected = 0;
                }
                return allRecipesFragment;
            case 1:
                if (isSecond) {
                    selected = 1;
                }
                else {
                    isSecond = true;
                }
                return favoriteRecipesFragment;
            case 2:

                return myRecipesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
    public void search(String hold, int frag){

        switch(frag) {
            case 0:

/*
                 allRecipesFragment.search(hold);
*/
            case 1:

/*
                favoriteRecipesFragment.onSearch(hold);
*/
            case 2:

                 /*myRecipesFragment.onSearch(hold);*/

        }

    }

}
