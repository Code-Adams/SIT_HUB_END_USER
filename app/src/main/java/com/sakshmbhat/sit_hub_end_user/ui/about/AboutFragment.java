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
                visionTV.setVisibility(View.VISIBLE);
            }
        });

        clickToViewMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                missionTV.setVisibility(View.VISIBLE);
            }
        });

        clickToViewQualityPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qualityPolicyTV.setVisibility(View.VISIBLE);
            }
        });

        clickToViewOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overviewItemContainerLL.setVisibility(View.VISIBLE);
            }
        });

        clickToViewAdminManagers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminManagerRecyclerView.setVisibility(View.VISIBLE);
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

        for(int i=0;i<6;i++){
            DefaultSliderView defaultSliderView= new DefaultSliderView(getContext());

            switch(i){

                case 0: defaultSliderView.setDescription("ImageOne");
                    defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/aboutImageSlider%2FOne.webp?alt=media&token=47856985-2015-4e51-aa90-d308ac378ba3");
                    break;

                case 1: defaultSliderView.setDescription("ImageTwo");
                    defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/aboutImageSlider%2FTwo.webp?alt=media&token=bb99ff39-1c03-4ef4-927e-bf6123e79f2c");
                    break;

                case 2: defaultSliderView.setDescription("ImageThree");
                    defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/aboutImageSlider%2FThree.webp?alt=media&token=07fbeb8c-cab0-4f44-8bc6-3583a2897091");
                    break;

                case 3: defaultSliderView.setDescription("ImageFour");
                    defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/aboutImageSlider%2FFour.webp?alt=media&token=a9276bfd-433a-4ff7-8084-c1dcf7f23591");
                    break;

                case 4: defaultSliderView.setDescription("ImageFive");
                    defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/aboutImageSlider%2FFive.webp?alt=media&token=befa3884-fe7b-48a5-9e51-ed816b4755d9");
                    break;

                case 5: defaultSliderView.setDescription("ImageSix");
                        defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/aboutImageSlider%2FSix.webp?alt=media&token=75cf7542-8a88-4b95-a28b-fcd94185a1cb");
                    break;
            }

            defaultSliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
            aboutSliderLayout.addSliderView(defaultSliderView );

        }

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

    }
}