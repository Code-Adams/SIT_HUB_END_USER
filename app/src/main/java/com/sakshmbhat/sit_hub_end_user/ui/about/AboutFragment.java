package com.sakshmbhat.sit_hub_end_user.ui.about;

import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakshmbhat.sit_hub_end_user.R;
import com.sakshmbhat.sit_hub_end_user.ZoomableImageViewActivity;
import com.sakshmbhat.sit_hub_end_user.ui.home.GoldMedalistData;
import com.sakshmbhat.sit_hub_end_user.ui.home.GoldMedalistRecyclerAdapter;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;

public class AboutFragment extends Fragment {

    private SliderLayout aboutSliderLayout;

    private TextInputLayout overViewTitleContainer,ourValuesTitleContainer,adminManagementTitleContainer;
   private  LinearLayout   visionTitleContainer,missionTitleContainer,qualityPolicyTitleContainer,institutionalValuesTitleContainer,coreValuesTitleContainer;;
    private  ImageView   coreValuesImage;
    private  TextView visionTV,missionTV,qualityPolicyTV,institutionalValuesTV;
    private LinearLayout overviewItemContainerLL,ourValuesItemContainerLL;

    private DatabaseReference databaseReference;
    private RecyclerView adminManagerRecyclerView;
    private ArrayList<AdminManagementData> list;
    private AdminManagementAdapter adminManagementAdapter;
    String coreValueImageUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        initialization(view);
        setSliderAttributes(view);
       // setSliderImages();
        getAndSetAbout();
        //getAndSetAdminManagerData();
      //  getAndSetOurValuesCoreValueImageUrl();

        ourValuesTitleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ourValuesItemContainerLL.getVisibility()==View.VISIBLE){
                    ourValuesItemContainerLL.setVisibility(View.GONE);
                }else{
                    ourValuesItemContainerLL.setVisibility(View.VISIBLE);
                }
            }
        });
        institutionalValuesTitleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(institutionalValuesTV.getVisibility()==View.VISIBLE){
                    institutionalValuesTV.setVisibility(View.GONE);
                }else{
                    institutionalValuesTV.setVisibility(View.VISIBLE);
                }
            }
        });
        coreValuesTitleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(coreValuesImage.getVisibility()==View.VISIBLE){
                    coreValuesImage.setVisibility(View.GONE);
                }else{
                    coreValuesImage.setVisibility(View.VISIBLE);
                }
            }
        });

        visionTitleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visionTV.getVisibility()==View.VISIBLE){
                    visionTV.setVisibility(View.GONE);
                }else{
                    visionTV.setVisibility(View.VISIBLE);
                }

            }
        });

        missionTitleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(missionTV.getVisibility()==View.VISIBLE){
                    missionTV.setVisibility(View.GONE);
                }else{
                    missionTV.setVisibility(View.VISIBLE);
                }
            }
        });

        qualityPolicyTitleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qualityPolicyTV.getVisibility()==View.VISIBLE){
                    qualityPolicyTV.setVisibility(View.GONE);

                }else{
                    qualityPolicyTV.setVisibility(View.VISIBLE);
                }
            }
        });

        overViewTitleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(overviewItemContainerLL.getVisibility()==View.VISIBLE){
                    overviewItemContainerLL.setVisibility(View.GONE);
                }else {
                    overviewItemContainerLL.setVisibility(View.VISIBLE);
                }
            }
        });

//        adminManagementTitleContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(adminManagerRecyclerView.getVisibility()==View.VISIBLE){
//                    adminManagerRecyclerView.setVisibility(View.GONE);
//
//                }else{
//                    adminManagerRecyclerView.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        return view;

    }


    private void getAndSetAbout() {

        databaseReference.child("AboutInstitution").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                if(snapshot.child("Overview").exists()){

                    visionTV.setText(String.valueOf(snapshot.child("Overview").child("Vision").getValue()));
                    missionTV.setText(String.valueOf(snapshot.child("Overview").child("Mission").getValue()));
                    qualityPolicyTV.setText(String.valueOf(snapshot.child("Overview").child("QualityPolicy").getValue()));
                }
                if(snapshot.child("AboutSliderImages").exists()){

                    long imageCount= snapshot.child("AboutSliderImages").getChildrenCount();
                    for(int i=1 ; i<=imageCount;i++){
                        DefaultSliderView defaultSliderView= new DefaultSliderView(getContext());
                        defaultSliderView.setImageUrl(String.valueOf(snapshot.child("AboutSliderImages").child(String.valueOf(i)).getValue()));
                        defaultSliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
                        aboutSliderLayout.addSliderView(defaultSliderView );
                    }

                }
                if(snapshot.child("AdministrativeManagement").exists()){

                    list = new ArrayList<>();
                    for(DataSnapshot dataSnapshot: snapshot.child("AdministrativeManagement").getChildren()){

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
                if(snapshot.child("InstitutionalValues").exists()){

                    institutionalValuesTV.setText(String.valueOf(snapshot.child("InstitutionalValues").child("InstitutionValues").getValue()));
                    coreValueImageUrl=String.valueOf(snapshot.child("InstitutionalValues").child("CoreValues").getValue());
                    try {
                        Glide.with(getContext()).load(coreValueImageUrl).into(coreValuesImage);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    coreValuesImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            try{ Intent zoomImage= new Intent(getContext(), ZoomableImageViewActivity.class);
                                zoomImage.putExtra("imageUrl",coreValueImageUrl);
                                getContext().startActivity(zoomImage);}catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


    }

//    private void getAndSetAdminManagerData() {
//
//
//        databaseReference.child("AboutInstitution").child("AdministrativeManagement").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                list = new ArrayList<>();
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//
//                    AdminManagementData adminManagementData= dataSnapshot.getValue(AdminManagementData.class);
//                    list.add(adminManagementData);
//
//                }
//
//                adminManagementAdapter = new AdminManagementAdapter(getContext(),list);
//                //notify the adapter that new data is available so that adapter can reset it
//                adminManagementAdapter.notifyDataSetChanged();
//                //As recycler is reset disable progress bar
//               // progressBar.setVisibility(View.GONE);
//               adminManagerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
//               adminManagerRecyclerView.setAdapter(adminManagementAdapter);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//                //If Deletion fails first stop progress bar
//               // progressBar.setVisibility(View.GONE);
//                Toast.makeText(getContext(), "Operation Failed: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }

//    private void setSliderImages() {
//
//        databaseReference.child("AboutInstitution").child("AboutSliderImages").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull  DataSnapshot snapshot) {
//
//                if(snapshot.exists()){
//
//                    long imageCount= snapshot.getChildrenCount();
//                    for(int i=1 ; i<=imageCount;i++){
//                        DefaultSliderView defaultSliderView= new DefaultSliderView(getContext());
//                        defaultSliderView.setImageUrl(String.valueOf(snapshot.child(String.valueOf(i)).getValue()));
//                        defaultSliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
//                        aboutSliderLayout.addSliderView(defaultSliderView );
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull  DatabaseError error) {
//
//            }
//        });
//
//    }

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

        visionTV=view.findViewById(R.id.visionTV);
        visionTV.setVisibility(View.GONE);

        missionTV=view.findViewById(R.id.missionTV);
        missionTV.setVisibility(View.GONE);

        qualityPolicyTV=view.findViewById(R.id.qualityPolicyTV);
        qualityPolicyTV.setVisibility(View.GONE);

        overviewItemContainerLL=view.findViewById(R.id.overviewItemContainerLL);
        overviewItemContainerLL.setVisibility(View.GONE);

        ourValuesItemContainerLL=view.findViewById(R.id.ourValuesItemContainerLL);
        ourValuesItemContainerLL.setVisibility(View.GONE);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        adminManagerRecyclerView=view.findViewById(R.id.adminManagerRecyclerView);
//        adminManagerRecyclerView.setVisibility(View.GONE);

        institutionalValuesTV=view.findViewById(R.id.institutionalValuesTV);
        institutionalValuesTV.setVisibility(View.GONE);
        coreValuesImage=view.findViewById(R.id.coreValuesIMage);
        coreValuesImage.setVisibility(View.GONE);
        overViewTitleContainer=view.findViewById(R.id.overviewTitleContainer);
                visionTitleContainer=view.findViewById(R.id.visionTitleContainer);
        missionTitleContainer=view.findViewById(R.id.missionTitleContainer);
                qualityPolicyTitleContainer=view.findViewById(R.id.qualityPolicyTitleContainer);
        ourValuesTitleContainer=view.findViewById(R.id.ourValuesTitleContainer);
                institutionalValuesTitleContainer=view.findViewById(R.id.institutionalValuesTitleContainer);
        coreValuesTitleContainer=view.findViewById(R.id.coreValuesTitleContainer);
//        adminManagementTitleContainer=view.findViewById(R.id.adminManagementTitleContainer);




    }
}