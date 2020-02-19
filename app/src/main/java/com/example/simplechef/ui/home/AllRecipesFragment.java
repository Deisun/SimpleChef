package com.example.simplechef.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplechef.R;
import com.example.simplechef.data.AllRecipesViewModel;

public class AllRecipesFragment extends Fragment {
    private static final String TAG = "AllRecipesFragment";
    private AllRecipesViewModel allRecipesViewModel;
    private AllRecipesListAdapter adapter;

    public static AllRecipesFragment newInstance() {
        AllRecipesFragment fragment = new AllRecipesFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.allrecipes_recyclerview, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.allrecipes_recyclerview);
        adapter= new AllRecipesListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        allRecipesViewModel = new ViewModelProvider(this).get(AllRecipesViewModel.class);
        allRecipesViewModel.getRecipes().observe(this, recipes -> {
            // update RecyclerView
            adapter.setRecipes(recipes);
            Toast.makeText(getContext(), "HELLO WORLD", Toast.LENGTH_LONG).show();

        });
    }
}
