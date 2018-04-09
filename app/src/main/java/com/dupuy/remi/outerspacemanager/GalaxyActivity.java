package com.dupuy.remi.outerspacemanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.Adapters.GalaxyAdapter;
import com.dupuy.remi.outerspacemanager.Adapters.ShipAdapter;
import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Fleet;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.ListingUsers;
import com.dupuy.remi.outerspacemanager.Models.Ship;
import com.dupuy.remi.outerspacemanager.Models.User;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class GalaxyActivity extends AppCompatActivity {


    private ListView lv_galaxy;
    private ListingUsers users;
    private ProgressBar progressLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaxy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        OuterSpaceManagerInterface service = WrapperCall.initialization();
        Call<ListingUsers> request = service.getUsers(SharedPreferencesHelper.getPrefsName(getApplicationContext(), "token", null));
        request.enqueue(new Callback<ListingUsers>(){

            @Override
            public void onResponse(Call<ListingUsers> call, retrofit2.Response<ListingUsers> response) {
                progressLoader = findViewById(R.id.progress_loader);
                progressLoader.setVisibility(View.INVISIBLE);
                if(response.code() == 200) {
                    users = response.body();
                    lv_galaxy = (ListView)findViewById(R.id.lv_galaxy);
                    TextView emptyText = (TextView)findViewById(R.id.empty_galaxy);
                    lv_galaxy.setEmptyView(emptyText);
                    lv_galaxy.setAdapter(new GalaxyAdapter(GalaxyActivity.this, users.getUsers()));
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

}
