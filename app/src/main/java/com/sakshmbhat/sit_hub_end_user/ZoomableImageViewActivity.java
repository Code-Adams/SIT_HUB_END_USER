package com.sakshmbhat.sit_hub_end_user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class ZoomableImageViewActivity extends AppCompatActivity {

    private String imageUrl;
    private PhotoView zoomImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomable_image_view);

        imageUrl=getIntent().getStringExtra("imageUrl");
        zoomImageView=findViewById(R.id.zoomImageView);

        try{
            Glide.with(ZoomableImageViewActivity.this).load(imageUrl).into(zoomImageView);
        }catch(Exception e){
            Toast.makeText(ZoomableImageViewActivity.this, "Oops! Image fault.", Toast.LENGTH_SHORT).show();
        }

    }
}