package com.sakshmbhat.sit_hub_end_user.nav_drawer.clubsOfSIT;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sakshmbhat.sit_hub_end_user.R;
import com.sakshmbhat.sit_hub_end_user.ZoomableImageViewActivity;

import java.util.List;

public class ClubGalleryAdapter extends RecyclerView.Adapter<ClubGalleryAdapter.ClubGalleryViewHolder> {
    private Context context;
    private List<String> imageUrlList;


    public ClubGalleryAdapter(Context context, List<String> imageUrlList) {
        this.context = context;
        this.imageUrlList = imageUrlList;
    }

    @NonNull
    @Override
    public ClubGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.picture_gallery_card,parent,false);

        return new ClubGalleryAdapter.ClubGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubGalleryAdapter.ClubGalleryViewHolder holder, int position) {

        try {
            Glide.with(context).load(imageUrlList.get(position)).into(holder.clubPicture);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.clubPicture.setOnClickListener(new View.OnClickListener() {
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

    public class ClubGalleryViewHolder extends RecyclerView.ViewHolder{

        ImageView clubPicture;
        public ClubGalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            clubPicture=itemView.findViewById(R.id.pictureGalleryImageView);
        }
    }
}
