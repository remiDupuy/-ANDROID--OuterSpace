package com.dupuy.remi.outerspacemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;

public class MainActivity extends AppCompatActivity {
    private TextView txtView_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtView_username = (TextView)findViewById(R.id.txtView_username);
        
        this.txtView_username.setText(SharedPreferencesHelper.getPrefsName(getApplicationContext(), "username", null));
    }
}
