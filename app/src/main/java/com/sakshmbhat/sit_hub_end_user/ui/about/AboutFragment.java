package com.sakshmbhat.sit_hub_end_user.ui.about;

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

public class AboutFragment extends Fragment {

    private SliderLayout aboutSliderLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        initialization(view);
        setSliderAttributes(view);
        setSliderImages();

        return view;
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
    }
}