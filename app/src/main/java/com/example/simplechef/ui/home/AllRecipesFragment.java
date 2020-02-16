package com.example.simplechef.ui.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplechef.data.AllRecipesViewModel;

public class AllRecipesFragment extends Fragment {
    private static final String TAG = "AllRecipesFragment";
    private AllRecipesViewModel allRecipesViewModel;

    public static AllRecipesFragment newInstance() {
        AllRecipesFragment fragment = new AllRecipesFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allRecipesViewModel = new ViewModelProvider(this).get(AllRecipesViewModel.class);
        allRecipesViewModel.getRecipes().observe(this, recipes -> {
            // update UI
        });

    }
}
