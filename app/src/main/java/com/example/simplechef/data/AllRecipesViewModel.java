package com.example.simplechef.data;


import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AllRecipesViewModel extends AndroidViewModel {
    private RecipeRepository repository;
    private LiveData<List<Recipe>> recipes;

    public AllRecipesViewModel(Application application) {
        super(application);
        repository = new RecipeRepository(application);
        recipes = repository.getAllRecipes();
    }

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
