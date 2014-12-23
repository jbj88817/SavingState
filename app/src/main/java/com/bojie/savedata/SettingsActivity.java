package com.bojie.savedata;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by bojiejiang on 12/23/14.
 */
public class SettingsActivity extends PreferenceActivity {

     @SuppressWarnings("deprecation")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         addPreferencesFromResource(R.xml.preferences);
    }
}
