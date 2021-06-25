package com.sakshmbhat.sit_hub_end_user.ui.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.icu.lang.UCharacter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
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
import com.sakshmbhat.sit_hub_end_user.MainActivity;
import com.sakshmbhat.sit_hub_end_user.R;
import com.sakshmbhat.sit_hub_end_user.SplashScreenActivity;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment {

    private SliderLayout homeSliderLayout;
    private ImageView collegeLocationImage,collegeMHRDImage;
    private TextView companiesVisited,offersMade,internshipsOffered,highestPackage,placementYear,goldMedalistYear;
    private TextView address,principalMail,phone1,phone2,officePhone,placementMail;
    private DatabaseReference databaseReference;
    private RecyclerView goldMedalistRecyclerView;
    private ArrayList<GoldMedalistData> list;
    private  GoldMedalistRecyclerAdapter goldMedalistRecyclerAdapter;
    private ProgressBar progressBar;
    private Context mContext;


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
         setContactDetails();
         collegeLocationImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openMap();
             }
         });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("address", String.valueOf(address.getText()).trim());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getContext(), "Copied", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
                principalMail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("principalMail", String.valueOf(principalMail.getText()).trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(getContext(), "Copied", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });
        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("phone1", String.valueOf(phone1.getText()).trim());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getContext(), "Copied", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
                phone2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("phone2", String.valueOf(phone2.getText()).trim());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(getContext(), "Copied", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });
        officePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("officePhone", String.valueOf(officePhone.getText()).trim());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getContext(), "Copied", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        placementMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("placementMail", String.valueOf(placementMail.getText()).trim());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getContext(), "Copied", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        return view;
    }

    private void setContactDetails() {
        FirebaseDatabase.getInstance().getReference().child("contactSIT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
               try {
                   if (snapshot.exists()) {

                       address.setText(String.valueOf(snapshot.child("address").getValue()).trim());
                       principalMail.setText(String.valueOf(snapshot.child("principalMail").getValue()).trim());
                       phone1.setText(String.valueOf(snapshot.child("mobileNum1").getValue()).trim());
                       phone2.setText(String.valueOf(snapshot.child("mobileNum2").getValue()).trim());
                       officePhone.setText(String.valueOf(snapshot.child("landLine").getValue()).trim());
                       placementMail.setText(String.valueOf(snapshot.child("placementsMail").getValue()).trim());
                   }
               }catch (Exception e){
                   e.printStackTrace();
               }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
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

        FirebaseDatabase.getInstance().getReference().child("Home").child("MHRDrank").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    try {
                        Glide.with(getContext()).load(snapshot.child("PIC").getValue().toString()).into(collegeMHRDImage);
                    }catch (Exception e){
                        e.printStackTrace();
                       Toast.makeText(getActivity(), "MHRDrank pic missing" , Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });




    }

    private void openMap() {

            try{
                    Uri uri= Uri.parse("geo:0,0?q=SIT Tumkur");
                    Intent toMaps= new Intent(Intent.ACTION_VIEW,uri);
                    toMaps.setPackage("com.google.android.apps.maps");
                    startActivity(toMaps);
                        }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(getContext(), "Google Maps: Error!", Toast.LENGTH_SHORT).show();
            }
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

        address=view.findViewById(R.id.address);
                principalMail=view.findViewById(R.id.principalMailID);
        phone1=view.findViewById(R.id.phone1);
                phone2=view.findViewById(R.id.phone2);
        officePhone=view.findViewById(R.id.officePhone);
        placementMail=view.findViewById(R.id.placementMail);


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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }



}