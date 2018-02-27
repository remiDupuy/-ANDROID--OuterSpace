package com.dupuy.remi.outerspacemanager.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dupuy.remi.outerspacemanager.Models.Building;
import com.dupuy.remi.outerspacemanager.Models.Search;
import com.dupuy.remi.outerspacemanager.R;
import com.google.gson.Gson;

/**
 * Created by rdupuy on 27/02/2018.
 */

public class FragmentDetailSearches extends Fragment {

    private TextView search_name;
    private TextView search_effect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_searches,container);

        search_name = (TextView)v.findViewById(R.id.search_name);
        search_effect = (TextView)v.findViewById(R.id.search_effect);

        return v;
    }

    public void fillFragment(String json) {
        Gson gson = new Gson();
        final Search search = gson.fromJson(json, Search.class);

        this.initLayout(search);
    }

    private void initLayout(Search search) {
        search_name.setText(search.getName());
        search_effect.setText(search.getEffect());
    }
}
