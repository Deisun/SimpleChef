package com.example.simplechef.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplechef.R;
import com.example.simplechef.ui.account.AccountActivity;
import com.example.simplechef.ui.login.LoginActivity;
import com.example.simplechef.ui.shared.SectionsStatePagerAdapter;
import com.example.simplechef.ui.recipe_create.CreateRecipeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private final static String TAG = "HomeActivity";
    private EditText editTextSearchPopUp;
    private Context context;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        view = ((HomeActivity) context).view;
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuHome:
                        //TODO:home menu
                        return true;
                    case R.id.menuSearch:
                        //TODO:this is where the animation will go for search
                        if(findViewById(R.id.editTextPopUpSearch).getVisibility() == View.VISIBLE){
                            findViewById(R.id.editTextPopUpSearch).setVisibility(View.INVISIBLE);
                        }
                        else{
                            findViewById(R.id.editTextPopUpSearch).setVisibility(View.VISIBLE);
                            Animation a = AnimationUtils.loadAnimation(context, R.anim.slide_bottom);
                            a.reset();
                            findViewById(R.id.editTextPopUpSearch).clearAnimation();
                            findViewById(R.id.editTextPopUpSearch).startAnimation(a);
                        }
                        return true;
                    case R.id.imageViewAdd:
                        Intent myIntent = new Intent(getBaseContext(), CreateRecipeActivity.class);
                        startActivity(myIntent);
                        return true;
                }
                return false;
            }
        });


        //ToolBar Setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                Intent profileIntent = new Intent(HomeActivity.this, AccountActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.action_signout:
                FirebaseAuth.getInstance().signOut();
                Intent signOutIntent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(signOutIntent);
                break;
            default:
                break;
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_home, menu);
        return super.onCreateOptionsMenu(menu);
    }



}
