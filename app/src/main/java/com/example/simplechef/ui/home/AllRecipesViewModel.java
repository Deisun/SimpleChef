package com.example.simplechef.ui.home;

import com.example.simplechef.Recipe;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllRecipesViewModel extends ViewModel {
    private MutableLiveData<List<Recipe>> recipes;
    public LiveData<List<Recipe>> getRecipes() {
        if (recipes == null) {
            recipes = new MutableLiveData<>();
        }
        return recipes;
    }

    private void loadRecipes() {

    }
}
