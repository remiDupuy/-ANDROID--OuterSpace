package com.dupuy.remi.outerspacemanager.Fragments;


import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Building;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.Responses.ResponseCreateBuilding;
import com.dupuy.remi.outerspacemanager.Models.Search;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.dupuy.remi.outerspacemanager.R;
import com.dupuy.remi.outerspacemanager.SearchesDetailActivity;
import com.dupuy.remi.outerspacemanager.ViewBuildingActivity;
import com.dupuy.remi.outerspacemanager.databinding.FragmentDetailSearchesBinding;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rdupuy on 27/02/2018.
 */

public class FragmentDetailSearches extends Fragment implements View.OnClickListener {

    private Button search_research;
    private FragmentDetailSearchesBinding binding;

    private Search search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {

        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_searches, container, false);
        this.search_research = binding.getRoot().findViewById(R.id.btnSearch);
        this.search_research.setOnClickListener(this);

        return this.binding.getRoot();
    }

    public void fillFragment(String json) {
        Gson gson = new Gson();
        search = gson.fromJson(json, Search.class);

        this.binding.setSearch(search);
        this.search_research.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        // Add the buttons
        builder.setMessage(R.string.confirm_create);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                OuterSpaceManagerInterface service = WrapperCall.initialization();
                Call<ResponseBody> request = service.createSearch(SharedPreferencesHelper.getPrefsName(getContext(), "token", null), search.getSearchId());
                request.enqueue(new Callback<ResponseBody>(){

                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if(response.code() == 200) {
                            Toast toast = Toast.makeText(getContext(), "Création en cours", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            try {
                                JSONObject jsonObj = new JSONObject(response.errorBody().string());
                                Toast toast = Toast.makeText(getContext(), jsonObj.getString("message"), Toast.LENGTH_SHORT);
                                toast.show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.wtf("PLOUF", t.toString());
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
