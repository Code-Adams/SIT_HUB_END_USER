package com.sakshmbhat.sit_hub_end_user.nav_drawer.developerReference;

import android.content.ClipData;
import android.content.ClipboardManager;
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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.DeveloperViewAdapter>{

    private Context context;
    private List<DeveloperData> list;

    public DeveloperAdapter(Context context, List<DeveloperData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DeveloperViewAdapter onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.developer_info_single_item_view,parent,false);

        return new DeveloperAdapter.DeveloperViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  DeveloperAdapter.DeveloperViewAdapter holder, int position) {

        final DeveloperData developerData = list.get(position);


        try{
            holder.developerName.setText(developerData.getName());
            holder.developerRole.setText(developerData.getRole());

            if(developerData.getImageUrl()!=null){
                try{
                    Glide.with(context).load(developerData.getImageUrl()).into(holder.developerImage);

                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context, developerData.getName()+"'s image unavailable", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(context, "Error: Inconsistent data!", Toast.LENGTH_SHORT).show();
        }

        holder.linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                    myWebLink.setData(Uri.parse(developerData.getLinkedin()));
                    context.startActivity(myWebLink);

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context, "Broken Url!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                    myWebLink.setData(Uri.parse(developerData.getInstagram()));
                    context.startActivity(myWebLink);

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context, "Broken Url!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!developerData.getGmail().isEmpty()){

                    try {

                        Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + developerData.getGmail()));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Namma-SIT Feedback");
                        context.startActivity(intent);

                    }catch (Exception e1){
                        e1.printStackTrace();
                        Toast.makeText(context, "Open Gmail: Failed!", Toast.LENGTH_SHORT).show();
                        try {
                            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("address", String.valueOf(developerData.getGmail()));
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(context, "Developer Email ID copied to clipboard!", Toast.LENGTH_SHORT).show();

                        } catch (Exception e2){
                            e2.printStackTrace();
                        }
                    }

                }else {
                    Toast.makeText(context, "Email not found!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DeveloperViewAdapter extends RecyclerView.ViewHolder{

        private CircleImageView developerImage;
        private TextView developerName,developerRole;
        private ImageView linkedin,gmail,instagram;

        public DeveloperViewAdapter(@NonNull  View itemView) {
            super(itemView);

            developerImage=itemView.findViewById(R.id.developerImage);
            developerName=itemView.findViewById(R.id.developerName);
            developerRole=itemView.findViewById(R.id.developerRole);
            linkedin=itemView.findViewById(R.id.developerLinkedin);
            gmail=itemView.findViewById(R.id.developerGmail);
            instagram=itemView.findViewById(R.id.developerInstagram);
        }
    }
}
