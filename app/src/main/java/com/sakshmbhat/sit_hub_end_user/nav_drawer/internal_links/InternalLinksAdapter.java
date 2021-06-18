package com.sakshmbhat.sit_hub_end_user.nav_drawer.internal_links;

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

import com.sakshmbhat.sit_hub_end_user.R;
import com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook.EbookAdapter;
import com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook.EbookData;
import com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook.EbookViewActivity;

import java.util.List;

public class InternalLinksAdapter extends RecyclerView.Adapter<InternalLinksAdapter.InternalLinksViewHolder> {

    private Context context;
    private List<InternalLinkData> list;

    public InternalLinksAdapter(Context context, List<InternalLinkData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InternalLinksAdapter.InternalLinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.internal_links_single_view_card,parent,false);

        return new InternalLinksAdapter.InternalLinksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  InternalLinksAdapter.InternalLinksViewHolder holder, int position) {

        holder.linkName.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                    myWebLink.setData(Uri.parse(list.get(position).getLink()));
                    context.startActivity(myWebLink);
                }catch(Exception e){
                    Toast.makeText(context, "Broken Url!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.browseLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                    myWebLink.setData(Uri.parse(list.get(position).getLink()));
                    context.startActivity(myWebLink);
                }catch(Exception e){
                    Toast.makeText(context, "Broken Url!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InternalLinksViewHolder extends RecyclerView.ViewHolder {

        TextView linkName;
        ImageView browseLink;

        public InternalLinksViewHolder(@NonNull  View itemView) {
            super(itemView);
            linkName=itemView.findViewById(R.id.internalLinkName);
            browseLink=itemView.findViewById(R.id.browseInternalLink);
        }
    }

}
