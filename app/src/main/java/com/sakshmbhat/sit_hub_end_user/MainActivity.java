package com.sakshmbhat.sit_hub_end_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class    MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        navController= Navigation.findNavController(this,R.id.fragment_layout);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        //setting up(initialising) toggle, it tells us if drawer is closing or opening
        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.start,R.string.close);
        //setting up drawer listener
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //Showing toggle option toolbar
       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
       //Listens which item is clicked and gets it's id
        navigationView.setNavigationItemSelectedListener(this);



    }
    //to enable toggle. otherwise clicking on toggle won't open drawer. only slide action will work
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return  true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        
        switch(item.getItemId())
        {
            case R.id.navigation_about:
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_developer:
                Toast.makeText(this, "Developer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_ebook:
                Toast.makeText(this, "ebook", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_faculty:
                Toast.makeText(this, "faculty", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_review:
                Toast.makeText(this, "review", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_theme:
                Toast.makeText(this, "theme", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_video:
                Toast.makeText(this, "video", Toast.LENGTH_SHORT).show();
                break;
             case R.id.navigation_website:
                Toast.makeText(this, "website", Toast.LENGTH_SHORT).show();
                break;

            
            
            
        }
        
        
        return true;
    }
}