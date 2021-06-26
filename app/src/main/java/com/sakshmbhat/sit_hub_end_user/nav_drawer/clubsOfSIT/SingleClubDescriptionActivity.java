package com.sakshmbhat.sit_hub_end_user.nav_drawer.clubsOfSIT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakshmbhat.sit_hub_end_user.R;
import com.sakshmbhat.sit_hub_end_user.ui.gallery.GalleryPictureAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SingleClubDescriptionActivity extends AppCompatActivity {

    private CircleImageView clubLogo;
    private TextView clubName,clubType,visitPage,clubDescription;
    private RecyclerView clubGalleryRecycler;
    private ClubGalleryAdapter clubGalleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_club_description);

        initialize();
        getClubGallery();
        visitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                    myWebLink.setData(Uri.parse(getIntent().getStringExtra("clubWebPageUrl")));
                    startActivity(myWebLink);
                }catch(Exception e){
                    Toast.makeText(SingleClubDescriptionActivity.this, "Webpage Unavailable!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getClubGallery() {


        FirebaseDatabase.getInstance().getReference().child("Gallery").child("clubs").child(getIntent().getStringExtra("clubName")).addValueEventListener(new ValueEventListener() {

            List<String> imageUrlList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()) {

                        String imageUrl =  String.valueOf(dataSnapshot.getValue());
                        //  Toast.makeText(getContext(), imageUrl, Toast.LENGTH_SHORT).show();
                        imageUrlList.add(imageUrl);
                    }
                }

                clubGalleryAdapter= new ClubGalleryAdapter(SingleClubDescriptionActivity.this,imageUrlList);
                clubGalleryRecycler.setLayoutManager(new GridLayoutManager(SingleClubDescriptionActivity.this,3));
                clubGalleryRecycler.setAdapter(clubGalleryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SingleClubDescriptionActivity.this, "Category A gallery fault! ", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initialize() {

        clubLogo=findViewById(R.id.clubLogo);
        clubName=findViewById(R.id.clubName);
        clubType=findViewById(R.id.clubType);
        visitPage=findViewById(R.id.visitPage);
        clubDescription=findViewById(R.id.clubDescription);
        clubGalleryRecycler=findViewById(R.id.clubGalleryRecycler);

        try{
            Glide.with(SingleClubDescriptionActivity.this).load(getIntent().getStringExtra("clubLogoUrl")).into(clubLogo);
        }catch(Exception e){
            e.printStackTrace();
        }
        clubName.setText(getIntent().getStringExtra("clubName"));
        clubType.setText(getIntent().getStringExtra("clubType"));
        clubDescription.setText(getIntent().getStringExtra("clubDescription"));

    }
}