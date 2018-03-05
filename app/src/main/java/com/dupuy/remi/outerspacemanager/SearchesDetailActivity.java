package com.dupuy.remi.outerspacemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.Fragments.FragmentDetailSearches;
import com.dupuy.remi.outerspacemanager.Fragments.FragmentListingSearches;
import com.dupuy.remi.outerspacemanager.Models.Building;
import com.dupuy.remi.outerspacemanager.Models.Search;
import com.google.gson.Gson;

public class SearchesDetailActivity extends AppCompatActivity{

    private OrientationEventListener listener;
    private Button search_research;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_searches);

        Intent intent = getIntent();
        final String jsonSearch = intent.getStringExtra("search");

        FragmentDetailSearches fragDetail = (FragmentDetailSearches)getSupportFragmentManager().findFragmentById(R.id.fragmentDetailSearches);
        fragDetail.fillFragment(jsonSearch);
        this.listener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                if(orientation != 0) {
                    Intent i = new Intent(getApplicationContext(),SearchesActivity.class);
                    i.putExtra("search", jsonSearch);
                    setResult(Activity.RESULT_OK, i);
                    finish();
                }
            }
        };
        if (listener.canDetectOrientation()) {
            listener.enable();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this, "oidnsfionsodi", Toast.LENGTH_SHORT).show();
    }
}
