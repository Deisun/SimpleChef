package com.example.simplechef.data;

import android.app.Application;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllRecipesViewModel extends ViewModel {
    private RecipeRepository repository;
    private LiveData<List<Recipe>> recipes;

/*
    public AllRecipesViewModel(@NonNull Application application) {
        super(application);
        repository = new RecipeRepository(application);
        recipes = repository.getAllRecipes();
    }
*/

    public LiveData<List<Recipe>> getRecipes() {
        if (recipes == null) {
            recipes = new MutableLiveData<>();
        }
        return recipes;
    }

    private void loadRecipes() {
        //asynchronously load all recipes
    }

    public void insert(Recipe recipe) {
        repository.insert(recipe);
    }

    public void update(Recipe recipe) {
        repository.update(recipe);
    }

    public void delete(Recipe recipe) {
        repository.delete(recipe);
    }


}
