package com.example.simplechef.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplechef.R;
import com.example.simplechef.data.Recipe;

import java.util.List;


public class AllRecipesListAdapter extends RecyclerView.Adapter<AllRecipesListAdapter.AllRecipesViewHolder> {

    class AllRecipesViewHolder extends RecyclerView.ViewHolder {
        private final TextView recipeItemView;

        private AllRecipesViewHolder(View itemView) {
            super(itemView);
            recipeItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Recipe> mRecipes;

    AllRecipesListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public AllRecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.allrecipes_recyclerview_item, parent, false);
        return new AllRecipesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AllRecipesViewHolder holder, int position) {
        if (mRecipes != null) {
            Recipe current = mRecipes.get(position);
            holder.recipeItemView.setText(current.getTitle());
        } else {
            holder.recipeItemView.setText("No recipes yet");
        }
    }

    void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mRecipes != null) return mRecipes.size();
        else return 0;
    }
}
