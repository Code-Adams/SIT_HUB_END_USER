package com.sakshmbhat.sit_hub_end_user.ui.about;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakshmbhat.sit_hub_end_user.R;
import com.sakshmbhat.sit_hub_end_user.ui.home.GoldMedalistData;
import com.sakshmbhat.sit_hub_end_user.ui.home.GoldMedalistRecyclerAdapter;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;

public class AboutFragment extends Fragment {

    private SliderLayout aboutSliderLayout;

    private  TextView clickToViewVision,clickToViewMission,clickToViewQualityPolicy,clickToViewOverview,clickToViewAdminManagers;
    private  TextView visionTV,missionTV,qualityPolicyTV;
    private LinearLayout overviewItemContainerLL;

    private DatabaseReference databaseReference;
    private RecyclerView adminManagerRecyclerView;
    private ArrayList<AdminManagementData> list;
    private AdminManagementAdapter adminManagementAdapter;

    private TextView overviewTitle,visionTitle,missionTitle,qualityPolicyTitle,adminManagementTitle;
    private boolean  overviewTitleTap=false,visionTitleTap=false,missionTitleTap=false,qualityPolicyTitleTap=false,adminManagementTitleTap=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        initialization(view);
        setSliderAttributes(view);
        setSliderImages();
        getAndSetOverview();
        getAndSetAdminManagerData();

        clickToViewVision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visionTitleTap){

                    visionTV.setVisibility(View.GONE);
                    visionTitleTap=false;

                }else{
                    visionTV.setVisibility(View.VISIBLE);
                    visionTitleTap=true;
                }

            }
        });

        clickToViewMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(missionTitleTap){

                    missionTV.setVisibility(View.GONE);
                    missionTitleTap=false;

                }else{
                    missionTV.setVisibility(View.VISIBLE);
                    missionTitleTap=true;
                }
            }
        });

        clickToViewQualityPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qualityPolicyTitleTap){

                    qualityPolicyTV.setVisibility(View.GONE);
                    qualityPolicyTitleTap=false;

                }else{
                    qualityPolicyTV.setVisibility(View.VISIBLE);
                    qualityPolicyTitleTap=true;
                }
            }
        });

        clickToViewOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(overviewTitleTap){

                    overviewItemContainerLL.setVisibility(View.GONE);
                    overviewTitleTap=false;

                }else {
                    overviewItemContainerLL.setVisibility(View.VISIBLE);
                    overviewTitleTap = true;
                }
            }
        });

        clickToViewAdminManagers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adminManagementTitleTap){

                    adminManagerRecyclerView.setVisibility(View.GONE);
                    adminManagementTitleTap=false;

                }else{
                    adminManagerRecyclerView.setVisibility(View.VISIBLE);
                    adminManagementTitleTap=true;
                }
            }
        });


        visionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visionTitleTap){

                    visionTV.setVisibility(View.GONE);
                    visionTitleTap=false;

                }else{
                    visionTV.setVisibility(View.VISIBLE);
                    visionTitleTap=true;
                }

            }
        });

        missionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(missionTitleTap){

                    missionTV.setVisibility(View.GONE);
                    missionTitleTap=false;

                }else{
                    missionTV.setVisibility(View.VISIBLE);
                    missionTitleTap=true;
                }
            }
        });

        qualityPolicyTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qualityPolicyTitleTap){

                    qualityPolicyTV.setVisibility(View.GONE);
                    qualityPolicyTitleTap=false;

                }else{
                    qualityPolicyTV.setVisibility(View.VISIBLE);
                    qualityPolicyTitleTap=true;
                }
            }
        });

        overviewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(overviewTitleTap){

                    overviewItemContainerLL.setVisibility(View.GONE);
                    overviewTitleTap=false;

                }else {
                    overviewItemContainerLL.setVisibility(View.VISIBLE);
                    overviewTitleTap = true;
                }
            }
        });

        adminManagementTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adminManagementTitleTap){

                    adminManagerRecyclerView.setVisibility(View.GONE);
                    adminManagementTitleTap=false;

                }else{
                    adminManagerRecyclerView.setVisibility(View.VISIBLE);
                    adminManagementTitleTap=true;
                }
            }
        });




        return view;
    }

    private void getAndSetOverview() {

        databaseReference.child("AboutInstitution").child("Overview").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    visionTV.setText(snapshot.child("Vision").getValue().toString());
                    missionTV.setText(snapshot.child("Mission").getValue().toString());
                    qualityPolicyTV.setText(snapshot.child("QualityPolicy").getValue().toString());
                }


            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


    }

    private void getAndSetAdminManagerData() {


        databaseReference.child("AboutInstitution").child("AdministrativeManagement").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    AdminManagementData adminManagementData= dataSnapshot.getValue(AdminManagementData.class);
                    list.add(adminManagementData);

                }

                adminManagementAdapter = new AdminManagementAdapter(getContext(),list);
                //notify the adapter that new data is available so that adapter can reset it
                adminManagementAdapter.notifyDataSetChanged();
                //As recycler is reset disable progress bar
               // progressBar.setVisibility(View.GONE);
               adminManagerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
               adminManagerRecyclerView.setAdapter(adminManagementAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                //If Deletion fails first stop progress bar
               // progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Operation Failed: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setSliderImages() {

        databaseReference.child("AboutInstitution").child("AboutSliderImages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.exists()){

                    long imageCount= snapshot.getChildrenCount();
                    for(int i=1 ; i<=imageCount;i++){
                        DefaultSliderView defaultSliderView= new DefaultSliderView(getContext());
                        defaultSliderView.setImageUrl(snapshot.child(String.valueOf(i)).getValue().toString());
                        defaultSliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
                        aboutSliderLayout.addSliderView(defaultSliderView );
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

    }

    private void setSliderAttributes(View view) {


        //setting slider indicator
        aboutSliderLayout.setIndicatorAnimation(IndicatorAnimations.DROP);
        //setting animation for slider image transition
        aboutSliderLayout.setSliderTransformAnimation(SliderAnimations.TOSSTRANSFORMATION);
        //setting scroll time
        aboutSliderLayout.setScrollTimeInSec(3);
    }

    private void initialization(View view) {
        aboutSliderLayout=view.findViewById(R.id.aboutSliderLayout);

        clickToViewVision=view.findViewById(R.id.clickToViewVision);
        clickToViewMission=view.findViewById(R.id.clickToViewMission);
        clickToViewQualityPolicy=view.findViewById(R.id.clickToViewQualityPolicy);
        clickToViewOverview=view.findViewById(R.id.clickToViewOverview);

        visionTV=view.findViewById(R.id.visionTV);
        visionTV.setVisibility(View.GONE);

        missionTV=view.findViewById(R.id.missionTV);
        missionTV.setVisibility(View.GONE);

        qualityPolicyTV=view.findViewById(R.id.qualityPolicyTV);
        qualityPolicyTV.setVisibility(View.GONE);

        overviewItemContainerLL=view.findViewById(R.id.overviewItemContainerLL);
        overviewItemContainerLL.setVisibility(View.GONE);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        adminManagerRecyclerView=view.findViewById(R.id.adminManagerRecyclerView);
        adminManagerRecyclerView.setVisibility(View.GONE);

        clickToViewAdminManagers=view.findViewById(R.id.clickToViewAdminManagers);

        overviewTitle=view.findViewById(R.id.overviewTitle);
        visionTitle=view.findViewById(R.id.visionTitle);
        missionTitle=view.findViewById(R.id.missionTitle);
        qualityPolicyTitle=view.findViewById(R.id.qualityPolicyTitle);
        adminManagementTitle=view.findViewById(R.id.adminManagementTitle);


    }
}