package com.sakshmbhat.sit_hub_end_user.ui.faculty;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sakshmbhat.sit_hub_end_user.R;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
//import android.content.Context;

public class FacultyInfoAdapter extends  RecyclerView.Adapter<FacultyInfoAdapter.facultyInfoViewAdapter> {

    private List<FacultyAttributes> list;
    private Context context;

    //Constructor for list and context
    public FacultyInfoAdapter(List<FacultyAttributes> passedList, Context passedContext, String passedCategory) {
        this.list = passedList;
        this.context = passedContext;

    }

    @NonNull
    @Override
    public facultyInfoViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflating the view
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_info_layout_card,parent,false);

        //Return object of facultyInfoViewAdapter
        return new facultyInfoViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull facultyInfoViewAdapter holder, int position) {

        final FacultyAttributes facultyAttributes = list.get(position);
        holder.name.setText(facultyAttributes.getName());
        holder.post.setText(facultyAttributes.getPost());
        holder.email.setText(facultyAttributes.getEmail());
        holder.facultyPageUrl.setText(facultyAttributes.getProfileUrl());

        try {
            Glide.with(context).load(facultyAttributes.getImageUrl()).apply(new RequestOptions().override(90,90)).placeholder(R.drawable.user).into(holder.facultyCircleImageview);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.visitFacultyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{ Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                    myWebLink.setData(Uri.parse(holder.facultyPageUrl.getText().toString()));
                    context.startActivity(myWebLink);
                }catch (Exception e){
                    Toast.makeText(context, "Webpage Unavailable!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class facultyInfoViewAdapter extends RecyclerView.ViewHolder {

         private TextView  name,email,post,visitFacultyPage,facultyPageUrl;
         private CircleImageView facultyCircleImageview;

        public facultyInfoViewAdapter(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.facultyNameDisplay);
            email = itemView.findViewById(R.id.facultyEmailDisplay);
            post = itemView.findViewById(R.id.facultyPostDisplay);
            facultyCircleImageview = itemView.findViewById(R.id.facultyImageDisplay);
            visitFacultyPage=itemView.findViewById(R.id.visitFacultyPage);
            facultyPageUrl=itemView.findViewById(R.id.facultyPageUrl);


        }
    }

}
