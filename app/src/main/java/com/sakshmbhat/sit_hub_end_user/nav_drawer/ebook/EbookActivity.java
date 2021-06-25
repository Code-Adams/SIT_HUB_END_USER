package com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakshmbhat.sit_hub_end_user.MainActivity;
import com.sakshmbhat.sit_hub_end_user.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EbookActivity extends AppCompatActivity {

    private RecyclerView ebookRecycler;
    private List<EbookData> list;
    private EbookAdapter ebookAdapter;
    private LinearLayout noEbookData;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        //show back arrow in nav bar
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //SetNav bar title
        getSupportActionBar().setTitle("Ebooks");

        initialize();
        getAndSetEbookData();

    }

    private void getAndSetEbookData() {

        FirebaseDatabase.getInstance().getReference().child("Ebook").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                list=new ArrayList<>();

                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        EbookData ebookData= dataSnapshot.getValue(EbookData.class);
                        list.add(ebookData);
                    }

                    Collections.reverse(list);
                    ebookAdapter=new EbookAdapter(EbookActivity.this,list);
                    ebookRecycler.setLayoutManager(new LinearLayoutManager(EbookActivity.this,RecyclerView.VERTICAL,false));
                    ebookRecycler.setAdapter(ebookAdapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);

                }else{
                    noEbookData.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(EbookActivity.this, "Database Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initialize() {

        ebookRecycler=findViewById(R.id.ebookRecycler);
        noEbookData=findViewById(R.id.noEbookFound);
        shimmerFrameLayout=findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EbookActivity.this, MainActivity.class));
        finish();
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
                ebookAdapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
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