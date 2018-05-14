package com.dupuy.remi.outerspacemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.adapters.FleetAdapter;
import com.dupuy.remi.outerspacemanager.service.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.models.Fleet;
import com.dupuy.remi.outerspacemanager.models.helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.models.ShipFleet;
import com.dupuy.remi.outerspacemanager.models.WrapperCall;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class FleetActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lv_fleet;
    private List<ShipFleet> fleet;

    private ProgressBar progressLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet);

        OuterSpaceManagerInterface service = WrapperCall.initialization();
        Call<Fleet> request = service.getFleetUser(SharedPreferencesHelper.getPrefsName(getApplicationContext(), "token", null));
        request.enqueue(new Callback<Fleet>(){

            @Override
            public void onResponse(Call<Fleet> call, retrofit2.Response<Fleet> response) {
                progressLoader = findViewById(R.id.progress_loader);
                progressLoader.setVisibility(View.INVISIBLE);

                if(response.code() == 200) {

                    fleet = response.body().getShips();
                    lv_fleet = (ListView)findViewById(R.id.lv_fleet);
                    TextView emptyText = (TextView)findViewById(R.id.empty);
                    lv_fleet.setEmptyView(emptyText);
                    lv_fleet.setAdapter(new FleetAdapter(FleetActivity.this, fleet));
                    lv_fleet.setOnItemClickListener(FleetActivity.this);
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

    }
}
