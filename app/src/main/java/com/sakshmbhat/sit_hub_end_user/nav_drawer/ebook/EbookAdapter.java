package com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sakshmbhat.sit_hub_end_user.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder> implements Filterable {

    private Context context;
    private List<EbookData> list,fullList;// fullList is used to store data for all item, "list" will undergo search

    public EbookAdapter(Context context, List<EbookData> list) {
        this.context = context;
        this.list = list;
        this.fullList= new ArrayList<>(list);
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_single_item_card,parent,false);

        return new EbookAdapter.EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  EbookAdapter.EbookViewHolder holder, int position) {

       final EbookData ebookData= list.get(position);
        holder.ebookName.setText(ebookData.getEbookTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,EbookViewActivity.class);
                intent.putExtra("ebookUrl",ebookData.getEbookUrl());
                context.startActivity(intent);
            }
        });

        holder.ebookDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(ebookData.getEbookUrl()));
                    context.startActivity(intent);
                }catch(Exception e){

                    Toast.makeText(context, "Broken Link!", Toast.LENGTH_SHORT).show();

                }
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

            List<EbookData> filteredList= new ArrayList<>();
            if(String.valueOf(constraint).isEmpty()){
                filteredList.addAll(fullList);
            }else {
                for(EbookData ebookData : fullList){

                    if(ebookData.getEbookTitle().toLowerCase().contains(String.valueOf(constraint).toLowerCase())){
                        filteredList.add(ebookData);
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

            list.addAll((Collection<? extends EbookData>) results.values);
            notifyDataSetChanged();

        }
    };


    public class EbookViewHolder extends RecyclerView.ViewHolder {

        TextView ebookName;
        ImageView ebookDownload;

        public EbookViewHolder(@NonNull  View itemView) {
            super(itemView);
            ebookName=itemView.findViewById(R.id.ebookName);
            ebookDownload=itemView.findViewById(R.id.downloadEbook);
        }
    }

}
