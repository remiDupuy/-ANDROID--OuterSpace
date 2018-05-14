package com.dupuy.remi.outerspacemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.OrientationEventListener;

import com.dupuy.remi.outerspacemanager.fragments.FragmentDetailSearches;

public class SearchesDetailActivity extends AppCompatActivity{

    private OrientationEventListener listener;


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
}
