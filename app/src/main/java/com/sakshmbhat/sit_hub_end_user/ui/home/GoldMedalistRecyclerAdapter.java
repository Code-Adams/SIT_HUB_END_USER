package com.sakshmbhat.sit_hub_end_user.ui.home;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class GoldMedalistRecyclerAdapter extends RecyclerView.Adapter<GoldMedalistRecyclerAdapter.GoldMedalistViewAdapter> {

    private Context context;
    private ArrayList<GoldMedalistData> list;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialogBox;

    public GoldMedalistRecyclerAdapter(Context passedContext, ArrayList<GoldMedalistData> passedList) {
        this.context = passedContext;
        this.list = passedList;
    }

    @NonNull
    @Override
    public GoldMedalistViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.gold_medalist_card, parent, false);

        return new GoldMedalistViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoldMedalistRecyclerAdapter.GoldMedalistViewAdapter holder, final int position) {

        final GoldMedalistData goldMedalistData = list.get(position);


        holder.goldMedalistName.setText(goldMedalistData.getName());
        holder.goldMedalistUSN.setText(goldMedalistData.getUSN());

   //     Set Image in card
        try {
            if (goldMedalistData.getImageUrl() != null)
                Glide.with(context).load(goldMedalistData.getImageUrl()).into(holder.goldMedalistImage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class GoldMedalistViewAdapter extends RecyclerView.ViewHolder {

        private TextView goldMedalistName, goldMedalistUSN;
        private CircleImageView goldMedalistImage;

        public GoldMedalistViewAdapter(@NonNull View itemView) {
            super(itemView);

            goldMedalistName = itemView.findViewById(R.id.goldMedalistName);
            goldMedalistImage = itemView.findViewById(R.id.goldMedalistImage);
            goldMedalistUSN = itemView.findViewById(R.id.goldMedalistUSN);

        }


    }
}

