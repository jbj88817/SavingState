package com.bojie.savedata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    EditText et_nodes;
    Button btn_settings;
    private static final int SETTINGS_INFO = 1;
    private static final String KEY_NODES = "NOTES";
    private static final String KEY_EMPTY = "EMPTY";


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_NODES, et_nodes.getText().toString());

        super.onSaveInstanceState(outState);

    }

    private void saveSettings() {
        SharedPreferences.Editor sPEditor = getPreferences(Context.MODE_PRIVATE).edit();
        sPEditor.putString(KEY_NODES, et_nodes.getText().toString());
        sPEditor.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nodes = (EditText) findViewById(R.id.et_nodes);
        btn_settings = (Button) findViewById(R.id.btn_settings);

        if(savedInstanceState != null){
            String notes = savedInstanceState.getString(KEY_NODES);
            et_nodes.setText(notes);
        }

        String sPnodes = getPreferences(Context.MODE_PRIVATE).getString(KEY_NODES, KEY_EMPTY);
        if (!sPnodes.equals(KEY_EMPTY)) {
            et_nodes.setText(sPnodes);
        }

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivityForResult(intent, SETTINGS_INFO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SETTINGS_INFO) {
            updateNodeText();
        }
    }

    private void updateNodeText(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPreferences.getBoolean("pref_text_bold", false)) {
            et_nodes.setTypeface(null, Typeface.BOLD);
        } else {
            et_nodes.setTypeface(null, Typeface.NORMAL);
        }

        String textSizeStr = sharedPreferences.getString("pref_text_size", "16");
        float textSizeFloot = Float.parseFloat(textSizeStr);
        et_nodes.setTextSize(textSizeFloot);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveSettings();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
