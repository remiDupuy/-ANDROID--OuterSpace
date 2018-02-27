package com.dupuy.remi.outerspacemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.dupuy.remi.outerspacemanager.Fragments.FragmentDetailSearches;
import com.dupuy.remi.outerspacemanager.Fragments.FragmentListingSearches;
import com.dupuy.remi.outerspacemanager.Models.Building;
import com.dupuy.remi.outerspacemanager.Models.Search;
import com.google.gson.Gson;

public class SearchesDetailActivity extends AppCompatActivity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_searches);

        Intent intent = getIntent();
        final String jsonSearch = intent.getStringExtra("search");

        FragmentDetailSearches fragDetail = (FragmentDetailSearches)getSupportFragmentManager().findFragmentById(R.id.fragmentDetailSearches);
        fragDetail.fillFragment(jsonSearch);


    }
}
