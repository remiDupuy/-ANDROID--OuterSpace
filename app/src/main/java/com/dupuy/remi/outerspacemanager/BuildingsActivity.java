package com.dupuy.remi.outerspacemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.Adapters.BuildingAdapter;
import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Building;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.ListingBuildings;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class BuildingsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lstView_buildings;

    private List<Building> list_buildings;
    private ProgressBar progressLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);

        OuterSpaceManagerInterface service = WrapperCall.initialization();
        Call<ListingBuildings> request = service.getBuildings(SharedPreferencesHelper.getPrefsName(getApplicationContext(), "token", null));
        request.enqueue(new Callback<ListingBuildings>(){

            @Override
            public void onResponse(Call<ListingBuildings> call, retrofit2.Response<ListingBuildings> response) {

                progressLoader = findViewById(R.id.progress_loader);
                progressLoader.setVisibility(View.INVISIBLE);

                if(response.code() == 200) {
                    list_buildings = response.body().getBuildings();
                    lstView_buildings = (ListView)findViewById(R.id.lstView_buildings);
                    lstView_buildings.setAdapter(new BuildingAdapter(getApplicationContext(), list_buildings));
                    lstView_buildings.setOnItemClickListener(BuildingsActivity.this);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Erreur de connexion", Toast.LENGTH_SHORT);
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

        Intent iViewBuilding = new Intent(getApplicationContext(), ViewBuildingActivity.class);
        Gson gson = new Gson();
        String json = gson.toJson(this.list_buildings.get(position));
        iViewBuilding.putExtra("building", json);
        startActivity(iViewBuilding);
    }
}
