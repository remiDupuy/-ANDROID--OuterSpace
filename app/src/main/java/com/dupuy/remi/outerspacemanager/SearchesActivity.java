package com.dupuy.remi.outerspacemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.Adapters.ShipAdapter;
import com.dupuy.remi.outerspacemanager.Fragments.FragmentDetailSearches;
import com.dupuy.remi.outerspacemanager.Fragments.FragmentListingSearches;
import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.ListingShips;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searches);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentListingSearches fragList = (FragmentListingSearches)getSupportFragmentManager().findFragmentById(R.id.fragmentListSearches);
        FragmentDetailSearches fragDetail = (FragmentDetailSearches)getSupportFragmentManager().findFragmentById(R.id.fragmentDetailSearches);
        Gson gson = new Gson();
        String json = gson.toJson(fragList.listSearches.getSearches().get(position));

        if(fragDetail == null || !fragDetail.isInLayout()){
            Intent i = new Intent(getApplicationContext(),SearchesDetailActivity.class);
            i.putExtra("search", json);
            startActivity(i);
        } else {
            fragDetail.fillFragment(json);
        }
    }
}
