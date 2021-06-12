package com.sakshmbhat.sit_hub_end_user.ui.feed;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sakshmbhat.sit_hub_end_user.R;




import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.feedViewAdapter> {

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

        //Set Image in card
        try {
             if(feedData.getImage()!=null)
            Glide.with(context).load(feedData.getImage()).into(holder.feedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class feedViewAdapter extends RecyclerView.ViewHolder {

        private TextView feedTitle,feedDate,feedTime;
        private ImageView feedImage;

        public feedViewAdapter(@NonNull View itemView) {
            super(itemView);

            feedTitle=itemView.findViewById(R.id.feedTitleDisplay);
            feedImage=itemView.findViewById(R.id.feedImageDisplay);
            feedDate=itemView.findViewById(R.id.feedItemDate);
            feedTime=itemView.findViewById(R.id.feedItemTime);

        }
    }

}
