package com.dupuy.remi.outerspacemanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.Adapters.GalaxyAdapter;
import com.dupuy.remi.outerspacemanager.Adapters.ShipAdapter;
import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.ListingShips;
import com.dupuy.remi.outerspacemanager.Models.ListingUsers;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;

import retrofit2.Call;
import retrofit2.Callback;

public class ShipsActivity extends AppCompatActivity {
    private ListingShips ships;
    private ListView lv_ships;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ships);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        OuterSpaceManagerInterface service = WrapperCall.initialization();
        Call<ListingShips> request = service.getShips(SharedPreferencesHelper.getPrefsName(getApplicationContext(), "token", null));
        request.enqueue(new Callback<ListingShips>(){

            @Override
            public void onResponse(Call<ListingShips> call, retrofit2.Response<ListingShips> response) {
                if(response.code() == 200) {
                    ships = response.body();
                    lv_ships = (ListView)findViewById(R.id.lv_ships);
                    TextView emptyText = (TextView)findViewById(R.id.empty_galaxy);
                    lv_ships.setEmptyView(emptyText);
                    lv_ships.setAdapter(new ShipAdapter(ShipsActivity.this, ships.getShips()));
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
