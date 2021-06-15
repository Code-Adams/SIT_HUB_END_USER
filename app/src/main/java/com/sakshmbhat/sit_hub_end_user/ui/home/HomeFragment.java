package com.sakshmbhat.sit_hub_end_user.ui.home;

import android.content.Intent;
import android.icu.lang.UCharacter;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakshmbhat.sit_hub_end_user.R;
import com.sakshmbhat.sit_hub_end_user.ui.feed.FeedAdapter;
import com.sakshmbhat.sit_hub_end_user.ui.feed.FeedData;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private SliderLayout homeSliderLayout;
    private ImageView collegeLocationImage,collegeMHRDImage;
    private TextView companiesVisited,offersMade,internshipsOffered,highestPackage,placementYear,goldMedalistYear;
    private DatabaseReference databaseReference;
    private RecyclerView goldMedalistRecyclerView;
    private ArrayList<GoldMedalistData> list;
    private  GoldMedalistRecyclerAdapter goldMedalistRecyclerAdapter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initialization(view);
        setSliderAttributes(view);
        setSliderImages();
        setMHRDImage();
        setPlacementStats();
        getAndSetGoldMedalistData();


        collegeLocationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;
    }

    private void getAndSetGoldMedalistData() {

        FirebaseDatabase.getInstance().getReference().child("goldMedalists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                   goldMedalistYear.setText(snapshot.child("Year").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        databaseReference.child("goldMedalists").child("goldMedalistList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    GoldMedalistData goldMedalistData= dataSnapshot.getValue(GoldMedalistData.class);
                    list.add(goldMedalistData);

                }

                goldMedalistRecyclerAdapter = new GoldMedalistRecyclerAdapter(getContext(),list);
                //notify the adapter that new data is available so that adapter can reset it
                goldMedalistRecyclerAdapter.notifyDataSetChanged();
                //As recycler is reset disable progress bar
                progressBar.setVisibility(View.GONE);
                goldMedalistRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
                goldMedalistRecyclerView.setAdapter(goldMedalistRecyclerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                //If Deletion fails first stop progress bar
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Operation Failed: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setPlacementStats() {

        databaseReference.child("PlacementStats").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    companiesVisited.setText(snapshot.child("CompaniesVisited").getValue().toString());
                    offersMade.setText(snapshot.child("OffersMade").getValue().toString());
                    internshipsOffered.setText(snapshot.child("InternshipsOffered").getValue().toString());
                    highestPackage.setText(snapshot.child("HighestPackage").getValue().toString());
                    placementYear.setText(snapshot.child("Year").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void setMHRDImage() {

        try {
            Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/MHRD%2C%20CollegeRank%2C%20ISO%20etc%20Image%2FPic.webp?alt=media&token=19c5de5a-3fd2-45fa-8ade-455eab74c13e").into(collegeMHRDImage);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Oops some components can't be loaded!: "+ e.getMessage() , Toast.LENGTH_SHORT).show();
        }


    }

    private void openMap() {

        Uri uri= Uri.parse("geo:0,0?q=SIT Tumkur");
        Intent toMaps= new Intent(Intent.ACTION_VIEW,uri);
        toMaps.setPackage("com.google.android.apps.maps");
        startActivity(toMaps);
    }

    private void initialization(View view) {

        collegeLocationImage=view.findViewById(R.id.collegeLocationImage);
        collegeMHRDImage=view.findViewById(R.id.collegeMHRDImage);

        companiesVisited=view.findViewById(R.id.companiesVisited);
        offersMade=view.findViewById(R.id.offersMade);
        internshipsOffered=view.findViewById(R.id.internshipsOffered);
        highestPackage=view.findViewById(R.id.highestPackage);
        placementYear=view.findViewById(R.id.placementYear);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        goldMedalistYear=view.findViewById(R.id.goldMedalistYear);
        goldMedalistRecyclerView=view.findViewById(R.id.goldMedalistRecycler);
        progressBar=view.findViewById(R.id.goldMedalistProgressBar);


    }


    private void setSliderImages() {

        databaseReference.child("HomeSliderImages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    long imageCount= snapshot.getChildrenCount();
                    for(int i=1 ; i<=imageCount;i++){
                        DefaultSliderView defaultSliderView= new DefaultSliderView(getContext());
                        defaultSliderView.setImageUrl(snapshot.child(String.valueOf(i)).getValue().toString());
                        defaultSliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
                        homeSliderLayout.addSliderView(defaultSliderView );
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

//        for(int i=0;i<5;i++){
//            DefaultSliderView defaultSliderView= new DefaultSliderView(getContext());
//
//            switch(i){
//
//                case 0: defaultSliderView.setDescription("ImageOne");
//                        defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/homeImageSlider%2FsliderImageOne.webp?alt=media&token=d5563209-3027-4c66-bda4-934c46b51a38");
//                        break;
//
//                  case 1: defaultSliderView.setDescription("ImageTwo");
//                        defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/homeImageSlider%2FsliderImageTwo.webp?alt=media&token=5901680a-f4bc-428c-a6d8-9fd068b09d9e");
//                        break;
//
//                  case 2: defaultSliderView.setDescription("ImageThree");
//                        defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/homeImageSlider%2FsliderImageThree.webp?alt=media&token=cb5c91b1-4e95-4138-8574-e70f48036a92");
//                        break;
//
//                  case 3: defaultSliderView.setDescription("ImageFour");
//                        defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/homeImageSlider%2FsliderImageFour.webp?alt=media&token=39945d84-9462-442f-ad38-63609ce942cd");
//                        break;
//
//                  case 4: defaultSliderView.setDescription("ImageFive");
//                        defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/homeImageSlider%2FsliderImagefive.webp?alt=media&token=b54626b7-116e-438d-bb30-6a64d25c6b3c");
//                        break;
//
//
//
//
//
//            }
//
//          defaultSliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
//           homeSliderLayout.addSliderView(defaultSliderView );
//
//        }

    }

    private void setSliderAttributes(View view) {

        homeSliderLayout=view.findViewById(R.id.homeSliderLayout);
        //setting slider indicator
        homeSliderLayout.setIndicatorAnimation(IndicatorAnimations.DROP);
        //setting animation for slider image transition
        homeSliderLayout.setSliderTransformAnimation(SliderAnimations.TOSSTRANSFORMATION);
        //setting scroll time
        homeSliderLayout.setScrollTimeInSec(2);
    }
}