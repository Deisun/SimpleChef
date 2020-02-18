package com.example.simplechef.ui.home;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplechef.R;
import com.example.simplechef.data.AllRecipesViewModel;

public class AllRecipesFragment extends Fragment {
    private static final String TAG = "AllRecipesFragment";
    private AllRecipesViewModel allRecipesViewModel;
    private RecipeListAdapter recipeListAdapter;
    private RecyclerView recyclerView;

    public static AllRecipesFragment newInstance() {
        AllRecipesFragment fragment = new AllRecipesFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recipeListAdapter = new RecipeListAdapter(recipeObject, favoritesList);
        recyclerView.setAdapter(recipeListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);


        allRecipesViewModel = new ViewModelProvider(this).get(AllRecipesViewModel.class);
        allRecipesViewModel.getRecipes().observe(this, recipes -> {
            // update RecyclerView

        });
    }

}
