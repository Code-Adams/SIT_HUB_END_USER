package com.sakshmbhat.sit_hub_end_user.ui.feed;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sakshmbhat.sit_hub_end_user.R;
import com.sakshmbhat.sit_hub_end_user.ZoomableImageViewActivity;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.feedViewAdapter>{

    private Context context;
    private ArrayList<FeedData> list;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialogBox;

    public FeedAdapter(Context passedContext, ArrayList<FeedData> passedList) {
        this.context = passedContext;
        this.list = passedList;
    }

    @NonNull
    @Override
    public feedViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.feed_item_layout_card,parent,false);

        return new feedViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull feedViewAdapter holder, final int position) {

        final FeedData feedData = list.get(position);

        //save title in title text view in card
        holder.feedTitle.setText(feedData.getTitle());
        holder.feedDate.setText(feedData.getDate());
        holder.feedTime.setText(feedData.getTime());
        holder.feedUploader.setText(feedData.getUploader());
        if(!feedData.getLink().equals("noLink") && !feedData.getLinkText().equals("noLink")){
            holder.feedLinkText.setText(feedData.getLinkText());
            holder.feedLinkData.setVisibility(View.VISIBLE);
        }

        holder.feedLinkData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{ Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                    myWebLink.setData(Uri.parse(feedData.getLink()));
                    context.startActivity(myWebLink);
                }catch (Exception e){
                    Toast.makeText(context, "Webpage Unavailable!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Set feed Image in card
        try {
             if(feedData.getImage()!=null)
            Glide.with(context).load(feedData.getImage()).into(holder.feedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //set uploader profile pic
        try {
            if(feedData.getImage()!=null)
                Glide.with(context).load(feedData.getUploaderProfilePicUrl()).into(holder.feedUploaderProfilePic);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.feedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try{ Intent zoomImage= new Intent(context, ZoomableImageViewActivity.class);
                zoomImage.putExtra("imageUrl",feedData.getImage());
                context.startActivity(zoomImage);}catch (Exception e){
                   e.printStackTrace();
               }
            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }


    public class feedViewAdapter extends RecyclerView.ViewHolder {

        private TextView feedTitle,feedDate,feedTime,feedUploader,feedLinkText;
        private ImageView feedImage,feedBrowseLink;
        private LinearLayout feedLinkData;
        private CircleImageView feedUploaderProfilePic;

        public feedViewAdapter(@NonNull View itemView) {
            super(itemView);

            feedTitle=itemView.findViewById(R.id.feedTitleDisplay);
            feedImage=itemView.findViewById(R.id.feedImageDisplay);
            feedDate=itemView.findViewById(R.id.feedItemDate);
            feedTime=itemView.findViewById(R.id.feedItemTime);
            feedUploader=itemView.findViewById(R.id.feedUploader);
            feedLinkText=itemView.findViewById(R.id.feedLinkText);
            feedBrowseLink=itemView.findViewById(R.id.feedBrowseLink);
            feedLinkData=itemView.findViewById(R.id.feedLinkData);
            feedUploaderProfilePic=itemView.findViewById(R.id.feedUploaderProfilePic);

        }
    }

}
