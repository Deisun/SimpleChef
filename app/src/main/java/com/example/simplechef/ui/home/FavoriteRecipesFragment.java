package com.example.simplechef.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplechef.R;
import com.example.simplechef.RecipeClass;
import com.example.simplechef.ui.recipe_view.ViewRecipeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteRecipesFragment extends Fragment {
    private static final String TAG = "FavoriteRecipesFragment";
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final FirebaseAuth currentUser = FirebaseAuth.getInstance();
    final DocumentReference docRef = db.collection("Users").document(currentUser.getUid());
    private ArrayList<String> favoritesList = new ArrayList<>();
    private ArrayList<RecipeClass> recipeObject = new ArrayList<>();

    private RecipeListAdapter recipeListAdapter;
    private RecyclerView recyclerView;
    private View fragView;

    public static FavoriteRecipesFragment newInstance() {
        FavoriteRecipesFragment fragment = new FavoriteRecipesFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //View To Return
        View view = inflater.inflate(R.layout.fragment_home_recipe_list, container, false);
        fragView = view;
        //See if the current user has any favorites
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.contains("MyFavorites")) {

                        favoritesList = (ArrayList<String>)document.get("MyFavorites");

                        for(int i = 0; i < favoritesList.size(); i++){
                             db.collection("Recipes").document(favoritesList.get(i)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                 @Override
                                 public void onSuccess(DocumentSnapshot documentSnapshot) {
                                     RecipeClass document = documentSnapshot.toObject(RecipeClass.class);
                                     recipeObject.add(document);
                                     Log.d("FAVORITE ITEMS", document.getID());

                                     //Recycler View
                                     recyclerView = fragView.findViewById(R.id.recyclerView);
                                     recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                                     recipeListAdapter = new RecipeListAdapter(recipeObject, favoritesList);
                                     recyclerView.setAdapter(recipeListAdapter);

                                     DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
                                     recyclerView.addItemDecoration(dividerItemDecoration);

                                     recipeListAdapter.setOnItemClickListener(new RecipeListAdapter.OnRecipeItemClickListener() {
                                         @Override
                                         public void onItemClick(int position) {
                                             Intent intent = recipeObject.get(position).toIntent(getActivity(), ViewRecipeActivity.class);

                                             startActivity(intent);
                                         }

                                         @Override
                                         public void onFavoriteItemClick(int position) {
                                             favoritesList.remove(position);
                                             recipeListAdapter.notifyDataSetChanged();
                                         }


                                     });
                                 }
                             });

                        }
                    } else {
                        //Possibly implement placeholder when no favorites exist
                    }

                } else {
                    Log.d("DocumentFailed", "get failed with ", task.getException());
                }

            }
        });


        return view;
    }
}
