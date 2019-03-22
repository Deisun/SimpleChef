package com.example.simplechef.ui.recipe_create;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.simplechef.R;

import com.example.simplechef.ui.Recipe;
import com.example.simplechef.ui.home.HomeActivity;
import com.example.simplechef.ui.login.SectionsStatePagerAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.Date;

public class activity_recipe_create extends AppCompatActivity implements recipe_create_fragment_s1.onRecipeChangeIngredientListener, fragment_recipe_create_direction.onRecipeChangeDirectionListener, fragment_recipe_create_extra1.onRecipeChangeExtraListener {
    ViewPager fragmentContainer;
    Recipe mainRecipe;
    /*private FirebaseFirestore db;*/


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_create);
        mainRecipe = new Recipe();
         fragmentContainer= (ViewPager) findViewById(R.id.activity_create_recipe_fragment_view);

        setupViewPager(fragmentContainer);
        /*db = FirebaseFirestore.getInstance();*/

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setTitle("Add Ingredients!");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

    }
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_recipe_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new recipe_create_fragment_s1(), "Ingredient");
        adapter.addFragment(new fragment_recipe_create_direction(), "direction");
        adapter.addFragment(new fragment_recipe_create_extra1(), "extra");
        viewPager.setAdapter(adapter);
    }
    public void setViewPager(int FragmentNumber){
        fragmentContainer.setCurrentItem(FragmentNumber);
    }

    @Override
    public void onRecipeChangeIngredientListenerMethod(Recipe recipe) {
        mainRecipe.setIngredients(recipe.getIngredients());
    }

    @Override
    public void onRecipeChangeDirectionListenerMethod(Recipe recipe) {
        mainRecipe.setDirections(recipe.getDirections());
    }

    @Override
    public void onRecipeChangeExtraListenerMethod(Recipe recipe) {
        /*mainRecipe.setDescription(recipe.getDescription());
        Log.d("description passed", "onRecipeChangeExtraListenerMethod: ");
        mainRecipe.setCost(recipe.getCost());
        Log.d("cost passed", "onRecipeChangeExtraListenerMethod: ");
        mainRecipe.setCompletionTime(recipe.getCompletionTime());
        Log.d("time passed", "onRecipeChangeExtraListenerMethod: ");
        CollectionReference dbRecipe = db.collection("Recipe");
        Log.d("dbRecipe passed", "onRecipeChangeExtraListenerMethod: ");
        recipe.setRank(5);
        recipe.setDate(new Date().toString());
        Log.d(recipe.getDate(), "onRecipeChangeExtraListenerMethod: ");
        dbRecipe.add(mainRecipe);*/
    }



}
