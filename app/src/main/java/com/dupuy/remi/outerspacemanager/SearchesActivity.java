package com.dupuy.remi.outerspacemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dupuy.remi.outerspacemanager.fragments.FragmentDetailSearches;
import com.dupuy.remi.outerspacemanager.fragments.FragmentListingSearches;
import com.dupuy.remi.outerspacemanager.fragments.OnFragmentInteractionListener;
import com.google.gson.Gson;

public class SearchesActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("search")) {
            search = savedInstanceState.getString("search", null);
        }
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentViewCreated(FragmentManager fm, android.support.v4.app.Fragment f, View v, Bundle savedInstanceState) {
                super.onFragmentViewCreated(fm, f, v, savedInstanceState);
                if (f.getClass() == FragmentDetailSearches.class) {
                    if (search != null) {
                        ((FragmentDetailSearches)f).fillFragment(search);
                        search = null;
                    }
                }
            }
        }, true);
        setContentView(R.layout.activity_searches);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("search", search);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if(resultCode == Activity.RESULT_OK) {
                search = data.getStringExtra("search");
            }
    }


    @Override
    public void updateRowSelected(int position, boolean redirect) {
        FragmentListingSearches fragList = (FragmentListingSearches)getSupportFragmentManager().findFragmentById(R.id.fragmentListSearches);
        FragmentDetailSearches fragDetail = (FragmentDetailSearches)getSupportFragmentManager().findFragmentById(R.id.fragmentDetailSearches);
        Gson gson = new Gson();
        String json = gson.toJson(fragList.listSearches.getSearches().get(position));

        if(fragDetail == null || !fragDetail.isInLayout()){
            Intent i = new Intent(getApplicationContext(),SearchesDetailActivity.class);
            i.putExtra("search", json);
            if(redirect) {
                startActivityForResult(i,0);
            }
        } else {
            fragDetail.fillFragment(json);
        }
    }
}
