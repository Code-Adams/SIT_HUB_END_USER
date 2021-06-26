package com.sakshmbhat.sit_hub_end_user.nav_drawer.clubsOfSIT;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sakshmbhat.sit_hub_end_user.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> implements Filterable {

    private Context context;
    private List<ClubData> list,fullList;// fullList is used to store data for all item, "list" will undergo search

    public ClubAdapter(Context context, List<ClubData> list) {
        this.context = context;
        this.list = list;
        this.fullList = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.club_single_view_item,parent,false);

        return new ClubAdapter.ClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ClubAdapter.ClubViewHolder holder, int position) {

        ClubData clubData = list.get(position);
        holder.clubName.setText(clubData.getClubName());
        holder.clubType.setText(clubData.getClubType());

        try{
            Glide.with(context).load(clubData.getClubLogoUrl()).into(holder.clubLogo);
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.knowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleClubDescriptionActivity.class);
                intent.putExtra("clubName",clubData.getClubName());
                intent.putExtra("clubDescription",clubData.getClubDescription());
                intent.putExtra("clubLogoUrl",clubData.getClubLogoUrl());
                intent.putExtra("validClub",clubData.getValidClub());
                intent.putExtra("phoneNumber",clubData.getPhoneNumber());
                intent.putExtra("clubType",clubData.getClubType());
                intent.putExtra("clubWebPageUrl",clubData.getClubWebPageUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    // performFiltering will run in background thread. Thread will be striated automatically.
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<ClubData> filteredList= new ArrayList<>();
            if(String.valueOf(constraint).isEmpty()){
                filteredList.addAll(fullList);
            }else {
                for(ClubData clubData : fullList){

                    if(clubData.getClubName().toLowerCase().contains(String.valueOf(constraint).toLowerCase())){
                        filteredList.add(clubData);
                    }

                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }
        //publishResults will run on UI thread. Take care
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear(); // remove all the items in the list, so that we can add items which match the search

            list.addAll((Collection<? extends ClubData>) results.values);
            notifyDataSetChanged();

        }
    };

    public class ClubViewHolder extends RecyclerView.ViewHolder{

        CircleImageView clubLogo;
        TextView knowMore,clubName,clubType;
        public ClubViewHolder(@NonNull View itemView) {
            super(itemView);
            clubLogo= itemView.findViewById(R.id.clubLogo);
            knowMore=itemView.findViewById(R.id.knowMore);
            clubName=itemView.findViewById(R.id.clubName);
            clubType=itemView.findViewById(R.id.clubType);
        }
    }

}
