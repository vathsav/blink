package com.vathsav.blink.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vathsav.blink.R;

/**
 * Profile fragment, shows user activity and display picture with a short bio.
 */
public class ProfileFragment extends Fragment {

    // TODO: 02/09/17 Hide the Fab in this Fragment

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        getActivity().setTitle("Profile");

        TextView textViewProfileUserName = view.findViewById(R.id.text_view_profile_user_name);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        textViewProfileUserName.setText(sharedPreferences.getString("display_name", null));

        return view;
    }

}
