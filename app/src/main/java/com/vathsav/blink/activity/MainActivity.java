package com.vathsav.blink.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.vathsav.blink.R;
import com.vathsav.blink.fragment.DraftsFragment;
import com.vathsav.blink.fragment.FavoritesFragment;
import com.vathsav.blink.fragment.HomeFragment;
import com.vathsav.blink.fragment.ProfileFragment;
import com.vathsav.blink.utils.Constants;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static CoordinatorLayout coordinatorLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = findViewById(R.id.coordinator_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        openFragment(new HomeFragment());

        FabSpeedDial fab = findViewById(R.id.fab_speed_dial);
        fab.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem fabMenuItem) {
                int id = fabMenuItem.getItemId();

                switch (id) {
                    case R.id.fab_text:
                        startActivity(Constants.intentNewTextLogActivity);
                        break;
                    case R.id.fab_audio:
                        startActivity(Constants.intentNewAudioLogActivity);
                        break;
                    case R.id.fab_video:
                        startActivity(Constants.intentNewVideoLogActivity);
                        break;
                }

                return false;
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String display_name = preferences.getString("display_name", null);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        View navigationHeader = navigationView.getHeaderView(0);
        TextView textViewNavUserName = navigationHeader.findViewById(R.id.text_view_nav_user_name);
        ImageView imageViewProfilePicture = navigationHeader.findViewById(R.id.image_view_profile_picture);

        // TODO: 03/09/17 Handle this from the shared preferences
        // textViewNavUserName.setText(display_name);
        textViewNavUserName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        Picasso.with(getApplicationContext()).load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()).into(imageViewProfilePicture);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_about:
                startActivity(Constants.intentAboutActivity);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                openFragment(new HomeFragment());
                navigationView.getMenu().getItem(0).setChecked(true);
                break;
            case R.id.nav_profile:
                openFragment(new ProfileFragment());
                navigationView.getMenu().getItem(1).setChecked(true);
                break;
            case R.id.nav_favorites:
                openFragment(new FavoritesFragment());
                navigationView.getMenu().getItem(2).setChecked(true);
                break;
            case R.id.nav_drafts:
                openFragment(new DraftsFragment());
                navigationView.getMenu().getItem(3).setChecked(true);
                break;
            case R.id.nav_preferences:
                startActivity(Constants.intentSettingsActivity);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, fragment)
                .commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
