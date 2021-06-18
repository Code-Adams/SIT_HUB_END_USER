package com.sakshmbhat.sit_hub_end_user.nav_drawer.internal_links;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import java.util.List;

public class InternalLinksActivity extends AppCompatActivity {

    private RecyclerView internalLinksRecycler;
    private List<InternalLinkData> list;
    private InternalLinksAdapter internalLinksAdapter;
    private ProgressBar progressBar;
    private LinearLayout noInternalLinkData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_links);
        initialize();
        getAndSetEbookData();
    }
    private void initialize() {

        internalLinksRecycler=findViewById(R.id.internalLinksRecycler);
        noInternalLinkData=findViewById(R.id.noInternalLinksFound);
        progressBar=findViewById(R.id.progressBar);

    }

    private void getAndSetEbookData() {

        FirebaseDatabase.getInstance().getReference().child("InternalLinks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list=new ArrayList<>();

                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        InternalLinkData internalLinkData= dataSnapshot.getValue(InternalLinkData.class);
                        list.add(internalLinkData);
                    }

                    internalLinksAdapter=new InternalLinksAdapter(InternalLinksActivity.this,list);
                    internalLinksRecycler.setLayoutManager(new LinearLayoutManager(InternalLinksActivity.this,RecyclerView.VERTICAL,false));
                    progressBar.setVisibility(View.GONE);
                    internalLinksRecycler.setAdapter(internalLinksAdapter);

                }else{
                    noInternalLinkData.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(InternalLinksActivity.this, "Database Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InternalLinksActivity.this, MainActivity.class));
        finish();
    }

}