package com.vathsav.blink.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vathsav.blink.R;
import com.vathsav.blink.model.LogAdapter;
import com.vathsav.blink.model.LogItem;

import java.util.ArrayList;

/**
 * HomeFragment
 * Contains a StaggeredGridLayout of cards
 */

public class HomeFragment extends Fragment {

    FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebase.getReference();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<LogItem> userLogs = new ArrayList<>();

                for (DataSnapshot logSnapshot : dataSnapshot.getChildren()) {
                    String logTitle = logSnapshot.child("log_title").getValue().toString();
                    Long logTimestamp = Long.parseLong(logSnapshot.child("log_timestamp").getValue().toString());

                    userLogs.add(new LogItem(logTitle, logTimestamp));
                }

                RecyclerView recyclerView = view.findViewById(R.id.recycler_view_fragment_home);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);

                LogAdapter logAdapter = new LogAdapter(userLogs, getContext());
                recyclerView.setAdapter(logAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}