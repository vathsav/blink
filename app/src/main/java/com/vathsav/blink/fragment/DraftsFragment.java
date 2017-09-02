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
import com.vathsav.blink.adapter.DraftAdapter;
import com.vathsav.blink.model.DraftItem;
import com.vathsav.blink.utils.Constants;

import java.util.ArrayList;

/**
 * Profile fragment, shows user activity and display picture with a short bio.
 */
public class DraftsFragment extends Fragment {

    final FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    final DatabaseReference reference = firebase.getReference()
            .child(Constants.user_id).child(Constants.referenceDrafts);

    public DraftsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_drafts, container, false);

        getActivity().setTitle("Drafts");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<DraftItem> userDrafts = new ArrayList<>();

                for (DataSnapshot draftSnapshot : dataSnapshot.getChildren()) {
                    String draftKey = draftSnapshot.getKey();
                    String draftTitle = draftSnapshot.child("draft_title").getValue().toString();
                    String draftContent = draftSnapshot.child("draft_content").getValue().toString();
                    String draftColor = draftSnapshot.child("draft_color").getValue().toString();
                    long draftTimestamp = Long.parseLong(draftSnapshot.child("draft_timestamp").getValue().toString());

                    userDrafts.add(new DraftItem(draftKey, draftTitle, draftContent, draftColor, draftTimestamp));
                }

                RecyclerView recyclerView = view.findViewById(R.id.recycler_view_drafts);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);

                DraftAdapter draftAdapter = new DraftAdapter(userDrafts, getContext());
                recyclerView.setAdapter(draftAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}
