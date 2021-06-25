package com.sakshmbhat.sit_hub_end_user.developerReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.Objects;

public class DeveloperReferenceActivity extends AppCompatActivity {

    private RecyclerView developerRecycler;
    private List<DeveloperData> list;
    private DeveloperAdapter developerAdapter;
    private LinearLayout noDeveloperData;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_reference);

        //show back arrow in nav bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //SetNav bar title
        getSupportActionBar().setTitle("Developers");

        initialize();
        getAndSetDeveloperData();

    }

    private void getAndSetDeveloperData() {

        FirebaseDatabase.getInstance().getReference().child("Developers").child("DeveloperInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list= new ArrayList<>();

                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        DeveloperData developerData= dataSnapshot.getValue(DeveloperData.class);
                        list.add(developerData);
                    }

                    developerAdapter=new DeveloperAdapter(DeveloperReferenceActivity.this,list);
                    developerRecycler.setLayoutManager(new LinearLayoutManager(DeveloperReferenceActivity.this,RecyclerView.VERTICAL,false));
                    developerRecycler.setAdapter(developerAdapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);

                }else{
                    noDeveloperData.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DeveloperReferenceActivity.this, "Database Error!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initialize() {

        developerRecycler=findViewById(R.id.developerRecycler);
        noDeveloperData=findViewById(R.id.noDeveloperFound);
        shimmerFrameLayout=findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.setVisibility(View.VISIBLE);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DeveloperReferenceActivity.this, MainActivity.class));
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

}