package com.sakshmbhat.sit_hub_end_user.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sakshmbhat.sit_hub_end_user.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;


public class HomeFragment extends Fragment {

    private SliderLayout homeSliderLayout;
    private ImageView collegeLocationImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initialization(view);
        setSliderAttributes(view);
        setSliderImages();

        collegeLocationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;
    }

    private void openMap() {

        Uri uri= Uri.parse("geo:0,0?q=SIT Tumkur");
        Intent toMaps= new Intent(Intent.ACTION_VIEW,uri);
        toMaps.setPackage("com.google.android.apps.maps");
        startActivity(toMaps);
    }

    private void initialization(View view) {

        collegeLocationImage=view.findViewById(R.id.collegeLocationImage);

    }


    private void setSliderImages() {

        for(int i=0;i<5;i++){
            DefaultSliderView defaultSliderView= new DefaultSliderView(getContext());

            switch(i){

                case 0: defaultSliderView.setDescription("ImageOne");
                        defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/homeImageSlider%2FsliderImageOne.webp?alt=media&token=d5563209-3027-4c66-bda4-934c46b51a38");
                        break;

                  case 1: defaultSliderView.setDescription("ImageTwo");
                        defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/homeImageSlider%2FsliderImageTwo.webp?alt=media&token=5901680a-f4bc-428c-a6d8-9fd068b09d9e");
                        break;

                  case 2: defaultSliderView.setDescription("ImageThree");
                        defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/homeImageSlider%2FsliderImageThree.webp?alt=media&token=cb5c91b1-4e95-4138-8574-e70f48036a92");
                        break;

                  case 3: defaultSliderView.setDescription("ImageFour");
                        defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/homeImageSlider%2FsliderImageFour.webp?alt=media&token=39945d84-9462-442f-ad38-63609ce942cd");
                        break;

                  case 4: defaultSliderView.setDescription("ImageFive");
                        defaultSliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/sit-hub-master.appspot.com/o/homeImageSlider%2FsliderImagefive.webp?alt=media&token=b54626b7-116e-438d-bb30-6a64d25c6b3c");
                        break;





            }

          defaultSliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
           homeSliderLayout.addSliderView(defaultSliderView );

        }

    }

    private void setSliderAttributes(View view) {

        homeSliderLayout=view.findViewById(R.id.homeSliderLayout);
        //setting slider indicator
        homeSliderLayout.setIndicatorAnimation(IndicatorAnimations.DROP);
        //setting animation for slider image transition
        homeSliderLayout.setSliderTransformAnimation(SliderAnimations.TOSSTRANSFORMATION);
        //setting scroll time
        homeSliderLayout.setScrollTimeInSec(4);
    }
}