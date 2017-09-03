package com.vathsav.blink.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
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
        getActivity().findViewById(R.id.fab).setVisibility(View.INVISIBLE);

        TextView textViewProfileUserName = view.findViewById(R.id.text_view_profile_user_name);
        ImageView imageViewProfilePicture = view.findViewById(R.id.image_view_profile_picture);

        TextView textViewStatsTextCount = view.findViewById(R.id.text_view_stats_text_count);
        TextView textViewStatsAudioCount = view.findViewById(R.id.text_view_stats_audio_count);
        TextView textViewStatsVideoCount = view.findViewById(R.id.text_view_stats_video_count);

        textViewStatsTextCount.setText("0");
        textViewStatsAudioCount.setText("0");
        textViewStatsVideoCount.setText("0");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        // textViewProfileUserName.setText(sharedPreferences.getString("display_name", null));

        textViewProfileUserName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        Log.v("PhotoURL", FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString());

        Picasso.with(getContext()).load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()).into(imageViewProfilePicture);

        return view;
    }

}
