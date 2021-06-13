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

    RecyclerView rvCatA,rvCatB,rvCatC,rvCatD;
    GalleryPictureAdapter galleryPictureAdapter;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        rvCatA=view.findViewById(R.id.categoryRecyclerA);
        rvCatB=view.findViewById(R.id.categoryRecyclerB);
        rvCatC=view.findViewById(R.id.categoryRecyclerC);
        rvCatD=view.findViewById(R.id.categoryRecyclerD);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Gallery");

        getCatPicturesA();
        getCatPicturesB();
        getCatPicturesC();
        getCatPicturesD();


         return view;
    }

    private void getCatPicturesA() {

        databaseReference.child("A").addValueEventListener(new ValueEventListener() {

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
                rvCatA.setLayoutManager(new GridLayoutManager(getContext(),3));
                rvCatA.setAdapter(galleryPictureAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Category A gallery fault! ", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getCatPicturesB() {


        databaseReference.child("B").addValueEventListener(new ValueEventListener() {

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
                rvCatB.setLayoutManager(new GridLayoutManager(getContext(),3));
                rvCatB.setAdapter(galleryPictureAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Category B gallery fault! ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void getCatPicturesC() {


        databaseReference.child("C").addValueEventListener(new ValueEventListener() {

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
                rvCatC.setLayoutManager(new GridLayoutManager(getContext(),3));
                rvCatC.setAdapter(galleryPictureAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Category C gallery fault! ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void getCatPicturesD() {


        databaseReference.child("D").addValueEventListener(new ValueEventListener() {

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
                rvCatD.setLayoutManager(new GridLayoutManager(getContext(),3));
                rvCatD.setAdapter(galleryPictureAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Category D gallery fault! ", Toast.LENGTH_SHORT).show();
            }
        });


    }
}