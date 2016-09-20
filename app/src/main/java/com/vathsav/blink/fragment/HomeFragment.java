package com.vathsav.blink.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vathsav.blink.R;
import com.vathsav.blink.model.LogAdapter;
import com.vathsav.blink.model.LogItem;

import java.util.ArrayList;

/**
 * HomeFragment
 * Contains a StaggeredGridLayout of cards
 */

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_fragment_home);
        LogAdapter logAdapter = new LogAdapter(dummyLogs(), getContext());

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(logAdapter);

        return view;
    }

    private ArrayList<LogItem> dummyLogs() {
        ArrayList<LogItem> userLogs = new ArrayList<>();
        userLogs.add(0, new LogItem("PUSH_KEY", "Darkside", System.currentTimeMillis()));
        userLogs.add(1, new LogItem("PUSH_KEY", "Fellowship", System.currentTimeMillis()));
        userLogs.add(2, new LogItem("PUSH_KEY", "Starlord", System.currentTimeMillis()));
        userLogs.add(3, new LogItem("PUSH_KEY", "Blimp", System.currentTimeMillis()));
        return userLogs;
    }
}