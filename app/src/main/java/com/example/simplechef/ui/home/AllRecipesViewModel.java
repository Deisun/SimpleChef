package com.example.simplechef.ui.home;

import com.example.simplechef.RecipeClass;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllRecipesViewModel extends ViewModel {
    private MutableLiveData<List<RecipeClass>> recipes;
    public LiveData<List<RecipeClass>> getRecipes() {
        if (recipes == null) {
            recipes = new MutableLiveData<>();
        }
        return recipes;
    }

    private void loadRecipes() {

    }
}
