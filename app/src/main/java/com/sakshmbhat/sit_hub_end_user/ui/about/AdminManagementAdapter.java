package com.sakshmbhat.sit_hub_end_user.ui.about;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sakshmbhat.sit_hub_end_user.R;
import com.sakshmbhat.sit_hub_end_user.ui.feed.FeedAdapter;
import com.sakshmbhat.sit_hub_end_user.ui.feed.FeedData;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminManagementAdapter extends RecyclerView.Adapter<AdminManagementAdapter.AdminManagementViewAdapter> {

    private Context context;
    private ArrayList<AdminManagementData> list;

    public AdminManagementAdapter(Context context, ArrayList<AdminManagementData> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public AdminManagementAdapter.AdminManagementViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.admin_manager_card,parent,false);

        return new AdminManagementAdapter.AdminManagementViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminManagementAdapter.AdminManagementViewAdapter holder, final int position) {

        final AdminManagementData adminManagementData = list.get(position);

        //save title in title text view in card
        holder.managerName.setText(adminManagementData.getName());
        holder.managerPost.setText(adminManagementData.getPost());
        holder.managerPageUrl.setText(adminManagementData.getPageUrl());

        //Set Image in card
        try {
            if(adminManagementData.getImageUrl()!=null)
                Glide.with(context).load(adminManagementData.getImageUrl()).into(holder.managerImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.visitManagerPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               try{ Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(holder.managerPageUrl.getText().toString()));
                context.startActivity(myWebLink);
               }catch (Exception e){
                   Toast.makeText(context, "Webpage Unavailable!", Toast.LENGTH_SHORT).show();
               }

//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                intent.setData(Uri.parse(holder.managerPageUrl.toString()));
//                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class AdminManagementViewAdapter extends RecyclerView.ViewHolder {

        private TextView managerName,managerPost,visitManagerPage,managerPageUrl;
        private CircleImageView managerImage;

        public AdminManagementViewAdapter(@NonNull View itemView) {
            super(itemView);

            managerName=itemView.findViewById(R.id.managerName);
            managerImage=itemView.findViewById(R.id.managerImage);
            managerPost=itemView.findViewById(R.id.managerPost);
            visitManagerPage=itemView.findViewById(R.id.visitManagerPage);
            managerPageUrl=itemView.findViewById(R.id.managerPageUrl);

        }
    }


}
