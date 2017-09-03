package com.vathsav.blink.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.vathsav.blink.R;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity implements Preference.OnPreferenceChangeListener {

    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */

    SwitchPreference switchPreferenceOfflineMode;
    EditTextPreference editTextPreferenceDisplayName;
    Preference preferenceAndroidWear;
    SwitchPreference switchPreferencePasswordProtection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        addPreferencesFromResource(R.xml.preferences);

        editTextPreferenceDisplayName = (EditTextPreference) findPreference("display_name");
        switchPreferenceOfflineMode = (SwitchPreference) findPreference("offline_mode");
        preferenceAndroidWear = findPreference("android_wear");
        switchPreferencePasswordProtection = (SwitchPreference) findPreference("password_protection");

        editTextPreferenceDisplayName.setOnPreferenceChangeListener(this);
        switchPreferenceOfflineMode.setOnPreferenceChangeListener(this);
        preferenceAndroidWear.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                return false;
            }
        });
        switchPreferencePasswordProtection.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object value) {
        if (preference.getKey().equals("display_name")) {
            preference.setSummary(value.toString());
            return true;
        } else if (preference.getKey().equals("offline_mode")) {
            if (switchPreferenceOfflineMode.isChecked()) {
                preference.setSummary(getResources().getString(R.string.preference_offline_mode_summary_enabled));

            } else {
                preference.setSummary(getResources().getString(R.string.preference_offline_mode_summary_disabled));
            }
            return true;
        } else if (preference.getKey().equals("android_wear")) {

            return true;
        } else if (preference.getKey().equals("password_protection")) {
            if (switchPreferencePasswordProtection.isChecked()) {
                preference.setSummary(getResources().getString(R.string.preference_password_protection_disabled));
            } else {
                preference.setSummary(getResources().getString(R.string.preference_password_protection_enabled));
            }
            return true;
        }

        return false;
    }

    @Override
    public void onResume() {
        super.onResume();

        // TODO Restore preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);

        String display_name = preferences.getString("display_name", null);
        boolean offline_mode = preferences.getBoolean("offline_mode", false);
        boolean password_protection = preferences.getBoolean("password_protection", false);

        if (display_name != null) {
            editTextPreferenceDisplayName.setSummary(display_name);
        }

        if (offline_mode) {
            switchPreferenceOfflineMode.setSummary(getResources().getString(R.string.preference_offline_mode_summary_disabled));
        } else {
            switchPreferenceOfflineMode.setSummary(getResources().getString(R.string.preference_offline_mode_summary_enabled));
        }

        if (password_protection) {
            switchPreferencePasswordProtection.setSummary(getResources().getString(R.string.preference_password_protection_enabled));
        } else {
            switchPreferencePasswordProtection.setSummary(getResources().getString(R.string.preference_password_protection_disabled));
        }

    }


    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (!super.onMenuItemSelected(featureId, item)) {
                NavUtils.navigateUpFromSameTask(this);
            }
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

}
