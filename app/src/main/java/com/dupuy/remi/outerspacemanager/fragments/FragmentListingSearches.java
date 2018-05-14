package com.dupuy.remi.outerspacemanager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.adapters.SearchesAdapter;
import com.dupuy.remi.outerspacemanager.service.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.models.helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.models.ListingSearches;
import com.dupuy.remi.outerspacemanager.models.WrapperCall;
import com.dupuy.remi.outerspacemanager.R;
import com.dupuy.remi.outerspacemanager.SearchesActivity;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by rdupuy on 27/02/2018.
 */

public class FragmentListingSearches extends Fragment implements AdapterView.OnItemClickListener {
    private ListView lvSearches;
    public ListingSearches listSearches;
    public Button btn_get;
    private ProgressBar progressLoader;

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

                progressLoader = getView().findViewById(R.id.progress_loader);
                progressLoader.setVisibility(View.INVISIBLE);
                if(response.code() == 200) {
                    listSearches = response.body();
                    lvSearches = (ListView)getView().findViewById(R.id.lv_searches);
                    lvSearches.setAdapter(new SearchesAdapter((SearchesActivity)getActivity(), listSearches.getSearches()));
                    lvSearches.setOnItemClickListener(FragmentListingSearches.this);
                    ((OnFragmentInteractionListener)getActivity()).updateRowSelected(0, false);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((OnFragmentInteractionListener)getActivity()).updateRowSelected(position, true);
    }
}
