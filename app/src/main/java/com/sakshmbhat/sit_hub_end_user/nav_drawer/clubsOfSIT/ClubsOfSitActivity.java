package com.sakshmbhat.sit_hub_end_user.nav_drawer.clubsOfSIT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakshmbhat.sit_hub_end_user.MainActivity;
import com.sakshmbhat.sit_hub_end_user.R;
import com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook.EbookActivity;
import com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook.EbookAdapter;
import com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook.EbookData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClubsOfSitActivity extends AppCompatActivity {

    private RecyclerView clubsRecycler;
    private List<ClubData> list;
    private ClubAdapter clubAdapter;
    private LinearLayout noClubsData;
    private ShimmerFrameLayout shimmerFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_of_sit);

        //show back arrow in nav bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //SetNav bar title
        getSupportActionBar().setTitle("Clubs Of SIT");

        initialize();
        getAndSetClubData();

    }

    private void getAndSetClubData() {

        FirebaseDatabase.getInstance().getReference().child("ClubsOfSIT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list=new ArrayList<>();

                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        ClubData clubData= dataSnapshot.getValue(ClubData.class);
                        assert clubData != null;
                        if(clubData.getValidClub().equals("1")) {
                            list.add(clubData);
                        }
                    }

                    clubAdapter=new ClubAdapter(ClubsOfSitActivity.this,list);
                    clubsRecycler.setLayoutManager(new LinearLayoutManager(ClubsOfSitActivity.this,RecyclerView.VERTICAL,false));
                    clubsRecycler.setAdapter(clubAdapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);

                }else{
                    noClubsData.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ClubsOfSitActivity.this, "Database Error!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initialize() {

            clubsRecycler=findViewById(R.id.clubsRecycler);
            noClubsData=findViewById(R.id.noClubsFound);
            shimmerFrameLayout=findViewById(R.id.shimmer_view_container);
            shimmerFrameLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ClubsOfSitActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        shimmerFrameLayout.startShimmer();
        super.onResume();
    }

    //To implement search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_for_search,menu);

        MenuItem item= menu.findItem(R.id.action_search);

        SearchView searchView= (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //newText contains the search string.
                clubAdapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}