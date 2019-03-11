package com.example.simplechef.ui.recipe_create;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.simplechef.R;
import com.example.simplechef.ui.Recipe;


public class fragment_recipe_create_extra1 extends Fragment {
        ImageView picture;
        EditText  price,time,description;
        Button okay;
        Recipe recipe = new Recipe();
        onRecipeChangeExtraListener onRecipeChangeExtraListenerVar;




    public fragment_recipe_create_extra1() {
        // Required empty public constructor
    }
    public interface onRecipeChangeExtraListener
    {
        public void onRecipeChangeExtraListenerMethod(Recipe recipe);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_recipe_create_extra1, container, false);
        setObjects(view);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(isInputsValid()){
                recipe.setDescription(description.getText().toString());
                recipe.setCost(Double.parseDouble(price.getText().toString()));
                Log.d("time", "onClick: " + time.getText().toString());
                String[] hold = time.getText().toString().split("\\W");
                recipe.setCompletionTime(Integer.parseInt(hold[0]) * 60 * 60 + Integer.parseInt(hold[1]) * 60);
                Log.d("maths", "onClick: " + recipe.getCompletionTime());
                onRecipeChangeExtraListenerVar.onRecipeChangeExtraListenerMethod(recipe);

                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode,resultCode,data);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            recipe.setPicture(bitmap);
            picture.setImageBitmap(bitmap);
    }

    private void setObjects(View view){
        picture = view.findViewById(R.id.fragment_recipe_create_extra_imageView_camera);
        price = view.findViewById(R.id.fragment_recipe_create_price);
        time = view.findViewById(R.id.fragment_create_recipe_EditText_time);
        description = view.findViewById(R.id.fragment_recipe_create_extra1_description);
        okay = view.findViewById(R.id.fragment_recipe_create_extra_Button_done);
    }
    private boolean isInputsValid(){
        boolean check = true;
        if (!time.getText().toString().matches(".*\\W.{2}")){
            time.setBackgroundColor(0xffff0000);
            check = false;
        }else
            time.setBackgroundColor(0xFFFFFF);

        if(description.getText().length() == 0){
            description.setBackgroundColor(0xffff0000);
            check = false;
        }else
            time.setBackgroundColor(0xFFFFFF);

        if (price.getText().length() == 0){
            price.setBackgroundColor(0xffff0000);
            check = false;
        }else
            time.setBackgroundColor(0xFFFFFF);

        if(recipe.getPicture() != null){
            check = false;
            picture.setBackgroundColor(0xffff0000);

        }else
            time.setBackgroundColor(0xFFFFFF);
        return check;
    }
}