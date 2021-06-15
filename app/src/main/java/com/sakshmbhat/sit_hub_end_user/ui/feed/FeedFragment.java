package com.sakshmbhat.sit_hub_end_user.ui.feed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakshmbhat.sit_hub_end_user.R;

import java.util.ArrayList;

public class FeedFragment extends Fragment {

    private RecyclerView feedRecycler;
    private ProgressBar progressBar;
    private ArrayList<FeedData> list;
    private FeedAdapter feedAdapter;

    private DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_feed, container, false);
        feedRecycler=view.findViewById(R.id.feedRecycler);
        progressBar=view.findViewById(R.id.feedProgressBar);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        feedRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        feedRecycler.setHasFixedSize(true);

        getFeed();

        return view;
    }

    private void getFeed() {

        databaseReference.child("Feed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    FeedData feedData= dataSnapshot.getValue(FeedData.class);
                    list.add(feedData);

                }

                feedAdapter = new FeedAdapter(getContext(),list);
                //notify the adapter that new data is available so that adapter can reset it
                feedAdapter.notifyDataSetChanged();
                //As recycler is reset disable progress bar
                progressBar.setVisibility(View.GONE);
                feedRecycler.setAdapter(feedAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                //If Deletion fails first stop progress bar
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Operation Failed: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}