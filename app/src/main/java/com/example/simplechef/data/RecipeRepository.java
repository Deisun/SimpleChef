package com.example.simplechef.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeRepository {
    private RecipeDao recipeDao;
    private LiveData<List<Recipe>> allRecipes;

    public RecipeRepository(Application application) {
        RecipeDatabase database = RecipeDatabase.getInstance(application);
        recipeDao = database.recipeDao();
        allRecipes = recipeDao.getAllRecipes();
    }

    public void insert(Recipe recipe) {

    }

    public void update(Recipe recipe) {

    }

    public void delete(Recipe recipe) {

    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    private static class InsertRecipeAsyncTask extends AsyncTask<Recipe, Void, Void> {

    }
}
