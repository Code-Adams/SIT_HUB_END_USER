package com.sakshmbhat.sit_hub_end_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakshmbhat.sit_hub_end_user.developerReference.DeveloperReferenceActivity;
import com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook.EbookActivity;
import com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook.EbookViewActivity;
import com.sakshmbhat.sit_hub_end_user.nav_drawer.internal_links.InternalLinksActivity;

import java.util.Objects;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

public class    MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController bottomNavController;
    private DrawerLayout mainActivityDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationDrawerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!checkConnectivity()){
            android.app.AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this);
            builder.setTitle("No Internet!");
            builder.setCancelable(false);
            builder.setMessage("Close App");
            builder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            finishAffinity();
                            System.exit(0);
                        }
                    });
            builder.show();
        }
       
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
            case R.id.navigation_website:
                   try{
                       Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                       myWebLink.setData(Uri.parse("http://sit.ac.in"));
                       startActivity(myWebLink);
                   }catch (Exception e){
                       e.printStackTrace();
                       Toast.makeText(MainActivity.this, "Broken Url", Toast.LENGTH_SHORT).show();
                   }
                break;

            case R.id.navigation_developer:
                startActivity(new Intent(MainActivity.this, DeveloperReferenceActivity.class));
        }


        return true;
    }





    @Override
    public void onBackPressed() {

        if(mainActivityDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mainActivityDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public boolean checkConnectivity() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
        return connected;
    }

}