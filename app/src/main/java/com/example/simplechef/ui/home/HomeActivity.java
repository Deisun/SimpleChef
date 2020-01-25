package com.example.simplechef.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.example.simplechef.R;
import com.example.simplechef.ui.account.AccountActivity;
import com.example.simplechef.ui.login.LoginActivity;
import com.example.simplechef.ui.recipe_create.CreateRecipeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private final static String TAG = "HomeActivity";
    private EditText editTextSearchPopUp;
    private Context context;
    private View view;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        view = ((HomeActivity) context).view;

        viewPager = findViewById(R.id.pager);
        viewPagerAdapter  = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);


        // ToolBar setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);

        // Tabs setup with View Pager
        tabLayout = findViewById(R.id.tabsHome);
        tabLayout.setupWithViewPager(viewPager);


        //Search
        editTextSearchPopUp = (EditText) findViewById(R.id.editTextSearch);
        editTextSearchPopUp.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                search();
                return false;
            }
        });


        // Bottom Nav setup
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

        viewPagerAdapter.setSelected(0);
    }

    public void search(){
        String hold = editTextSearchPopUp.getText().toString();


        viewPagerAdapter.search(hold,viewPagerAdapter.getSelected());




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
