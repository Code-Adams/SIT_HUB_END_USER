package com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook;

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

import java.util.ArrayList;
import java.util.List;

public class EbookActivity extends AppCompatActivity {

    private RecyclerView ebookRecycler;
    private List<EbookData> list;
    private EbookAdapter ebookAdapter;
    private ProgressBar progressBar;
    private LinearLayout noEbookData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

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

                    ebookAdapter=new EbookAdapter(EbookActivity.this,list);
                    ebookRecycler.setLayoutManager(new LinearLayoutManager(EbookActivity.this,RecyclerView.VERTICAL,false));
                    progressBar.setVisibility(View.GONE);
                    ebookRecycler.setAdapter(ebookAdapter);

                }else{
                    noEbookData.setVisibility(View.VISIBLE);
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
        progressBar=findViewById(R.id.progressBar);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EbookActivity.this, MainActivity.class));
        finish();
    }
}