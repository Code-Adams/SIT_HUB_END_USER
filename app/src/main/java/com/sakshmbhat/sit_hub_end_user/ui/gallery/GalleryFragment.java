package com.sakshmbhat.sit_hub_end_user.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakshmbhat.sit_hub_end_user.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    RecyclerView rvCatInfrastructure,rvCatIncubationCell,rvCatResearch,rvCatSports,rvCatCampus;
    GalleryPictureAdapter galleryPictureAdapter;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        rvCatInfrastructure=view.findViewById(R.id.categoryRecyclerInfrastructure);
        rvCatIncubationCell=view.findViewById(R.id.categoryRecyclerIncubationCell);
        rvCatResearch=view.findViewById(R.id.categoryRecyclerResearch);
        rvCatSports=view.findViewById(R.id.categoryRecyclerSports);
        rvCatCampus=view.findViewById(R.id.categoryRecyclerCampus);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Gallery");

        getCatPicturesInfrastructure();
        getCatPicturesIncubationCell();
        getCatPicturesResearch();
        getCatPicturesSports();
        getCatPicturesCampus();


         return view;
    }

    private void getCatPicturesInfrastructure() {

        databaseReference.child("Infrastructure").addValueEventListener(new ValueEventListener() {

            List<String> imageUrlList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()) {

                        String imageUrl =  dataSnapshot.getValue().toString();
                      //  Toast.makeText(getContext(), imageUrl, Toast.LENGTH_SHORT).show();
                        imageUrlList.add(imageUrl);
                    }
                }

                galleryPictureAdapter= new GalleryPictureAdapter(getContext(),imageUrlList);
                rvCatInfrastructure.setLayoutManager(new GridLayoutManager(getContext(),3));
                rvCatInfrastructure.setAdapter(galleryPictureAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Category A gallery fault! ", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getCatPicturesIncubationCell() {


        databaseReference.child("Incubation Cell").addValueEventListener(new ValueEventListener() {

            List<String> imageUrlList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()) {

                        String imageUrl =  dataSnapshot.getValue().toString();
                        imageUrlList.add(imageUrl);
                    }
                }

                galleryPictureAdapter= new GalleryPictureAdapter(getContext(),imageUrlList);
                rvCatIncubationCell.setLayoutManager(new GridLayoutManager(getContext(),3));
                rvCatIncubationCell.setAdapter(galleryPictureAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Category B gallery fault! ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void getCatPicturesResearch() {


        databaseReference.child("Research").addValueEventListener(new ValueEventListener() {

            List<String> imageUrlList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()) {

                        String imageUrl =  dataSnapshot.getValue().toString();
                        imageUrlList.add(imageUrl);

                    }
                }

                galleryPictureAdapter= new GalleryPictureAdapter(getContext(),imageUrlList);
                rvCatResearch.setLayoutManager(new GridLayoutManager(getContext(),3));
                rvCatResearch.setAdapter(galleryPictureAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Category C gallery fault! ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void getCatPicturesSports() {


        databaseReference.child("Sports").addValueEventListener(new ValueEventListener() {

            List<String> imageUrlList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()) {

                        String imageUrl = dataSnapshot.getValue().toString();

                        imageUrlList.add(imageUrl);
                    }
                }

                galleryPictureAdapter= new GalleryPictureAdapter(getContext(),imageUrlList);
                rvCatSports.setLayoutManager(new GridLayoutManager(getContext(),3));
                rvCatSports.setAdapter(galleryPictureAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Sports Gallery Error! ", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getCatPicturesCampus() {


        databaseReference.child("Campus").addValueEventListener(new ValueEventListener() {

            List<String> imageUrlList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for(DataSnapshot dataSnapshot: snapshot.getChildren()) {

                        String imageUrl = dataSnapshot.getValue().toString();

                        imageUrlList.add(imageUrl);
                    }
                }

                galleryPictureAdapter= new GalleryPictureAdapter(getContext(),imageUrlList);
                rvCatCampus.setLayoutManager(new GridLayoutManager(getContext(),3));
                rvCatCampus.setAdapter(galleryPictureAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Campus Gallery Error! ", Toast.LENGTH_SHORT).show();
            }
        });


    }
}