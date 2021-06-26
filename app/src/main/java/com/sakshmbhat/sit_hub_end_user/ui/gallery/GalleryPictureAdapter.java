package com.sakshmbhat.sit_hub_end_user.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sakshmbhat.sit_hub_end_user.R;
import com.sakshmbhat.sit_hub_end_user.ZoomableImageViewActivity;

import java.util.List;

public class GalleryPictureAdapter extends RecyclerView.Adapter<GalleryPictureAdapter.GalleryPictureViewAdapter> {

    private Context context;
    private List<String>  imageUrlList;

    public GalleryPictureAdapter(Context context, List<String> imageUrlList) {
        this.context = context;
        this.imageUrlList = imageUrlList;
    }

    @NonNull
    @Override
    public GalleryPictureViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //inflate the layout
       View view= LayoutInflater.from(context).inflate(R.layout.picture_gallery_card,parent,false);
        return new GalleryPictureAdapter.GalleryPictureViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  GalleryPictureAdapter.GalleryPictureViewAdapter holder, int position) {


        try {
            Glide.with(context).load(imageUrlList.get(position)).into(holder.pictureGalleryImageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    holder.pictureGalleryImageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                Intent zoomImage= new Intent(context, ZoomableImageViewActivity.class);
                zoomImage.putExtra("imageUrl",imageUrlList.get(position));
                context.startActivity(zoomImage);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    });

    }

    @Override
    public int getItemCount() {
        return imageUrlList.size();
    }

    public class GalleryPictureViewAdapter extends RecyclerView.ViewHolder {

        ImageView pictureGalleryImageView;

        public GalleryPictureViewAdapter(@NonNull View itemView) {
            super(itemView);

            pictureGalleryImageView= itemView.findViewById(R.id.pictureGalleryImageView);

        }
    }



}
