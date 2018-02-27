package com.dupuy.remi.outerspacemanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.Adapters.SearchesAdapter;
import com.dupuy.remi.outerspacemanager.Adapters.ShipAdapter;
import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.MainActivity;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.ListingSearches;
import com.dupuy.remi.outerspacemanager.Models.ListingShips;
import com.dupuy.remi.outerspacemanager.Models.Search;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.dupuy.remi.outerspacemanager.R;
import com.dupuy.remi.outerspacemanager.SearchesActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by rdupuy on 27/02/2018.
 */

public class FragmentListingSearches extends Fragment {
    private ListView lvSearches;
    public ListingSearches listSearches;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_searches,container);
        lvSearches = (ListView)v.findViewById(R.id.lv_searches);
        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        OuterSpaceManagerInterface service = WrapperCall.initialization();
        Call<ListingSearches> request = service.getSearches(SharedPreferencesHelper.getPrefsName((SearchesActivity)getActivity(), "token", null));
        request.enqueue(new Callback<ListingSearches>(){

            @Override
            public void onResponse(Call<ListingSearches> call, retrofit2.Response<ListingSearches> response) {
                if(response.code() == 200) {
                    listSearches = response.body();
                    lvSearches = (ListView)getView().findViewById(R.id.lv_searches);
                    lvSearches.setAdapter(new SearchesAdapter((SearchesActivity)getActivity(), listSearches.getSearches()));
                    lvSearches.setOnItemClickListener((SearchesActivity)getActivity());
                } else {
                    Toast toast = Toast.makeText((SearchesActivity)getActivity(), "Erreur de connexion", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.wtf("PLOUF", t.toString());
            }
        });


    }
}
