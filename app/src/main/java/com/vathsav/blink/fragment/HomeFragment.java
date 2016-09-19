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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_fragment_home);

        if (recyclerView != null) {
            LogAdapter logAdapter = new LogAdapter(dummyLogs(), getContext());
            recyclerView.setAdapter(logAdapter);
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
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