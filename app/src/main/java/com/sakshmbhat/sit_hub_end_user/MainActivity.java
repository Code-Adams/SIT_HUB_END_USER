package com.sakshmbhat.sit_hub_end_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook.EbookActivity;
import com.sakshmbhat.sit_hub_end_user.nav_drawer.internal_links.InternalLinksActivity;

import java.util.Objects;

public class    MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController bottomNavController;
    private DrawerLayout mainActivityDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationDrawerView;
    private  Menu navDrawerMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       
         initialize();

        NavigationUI.setupWithNavController(bottomNavigationView,bottomNavController);

        //setting up(initialising) toggle, it tells us if drawer is closing or opening
        toggle = new ActionBarDrawerToggle(this, mainActivityDrawerLayout,R.string.start,R.string.close);
        //setting up drawer listener
        mainActivityDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //Showing menu items
       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
       //Listens which item is clicked and gets it's id
        navigationDrawerView.setNavigationItemSelectedListener(this);
        



    }



    private void initialize() {
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavController= Navigation.findNavController(this,R.id.fragment_layout);

        mainActivityDrawerLayout=findViewById(R.id.drawer_layout);
        navigationDrawerView=findViewById(R.id.navigationDrawerView);
        navDrawerMenu=navigationDrawerView.getMenu();
        
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
            case R.id.navigation_internalLinks:
                 startActivity(new Intent(MainActivity.this, InternalLinksActivity.class));
                break;
            case R.id.navigation_ebook:
                          startActivity(new Intent(MainActivity.this,EbookActivity.class));
                break;
            case R.id.navigation_gallery:
                 startActivity(new Intent(getApplicationContext(), EbookActivity.class));
                break;
            case R.id.navigation_faculty:
                Toast.makeText(this, "faculty", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_review:
                Toast.makeText(this, "review", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_share:

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