package com.dupuy.remi.outerspacemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.Responses.Response;
import com.dupuy.remi.outerspacemanager.Models.User;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtView_username;

    private TextView txtViewPoints;
    private TextView txtGasUser;
    private TextView txtRockUser;

    private Button btn_buildings;
    private Button btn_flotte;
    private Button btn_galaxy;
    private Button btn_ships;
    private Button btn_searches;
    private Button btn_disconnect;

    @Override
    protected void onResume() {
        super.onResume();

        this.fillInfosUser();
    }

    private void fillInfosUser() {
        OuterSpaceManagerInterface service = WrapperCall.initialization();
        Call<User> request = service.getCurrentUser(SharedPreferencesHelper.getPrefsName(getApplicationContext(), "token", null));
        request.enqueue(new Callback<User>(){

            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if(response.code() == 200) {
                    SharedPreferencesHelper.setPrefsName(getApplicationContext(), "username", response.body().username);
                    User currentuser = response.body();
                    currentuser.save();
                    txtView_username = (TextView)findViewById(R.id.txtView_username);
                    txtView_username.setText(currentuser.username);

                    txtViewPoints = (TextView)findViewById(R.id.txtViewPoints);
                    txtViewPoints.setText(Integer.toString(currentuser.points));

                    txtGasUser = (TextView)findViewById(R.id.gas_user);
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(2);
                    txtGasUser.setText(df.format(currentuser.gas));

                    txtRockUser = (TextView)findViewById(R.id.rock_user);
                    txtRockUser.setText(df.format(currentuser.minerals));
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OuterSpaceManagerInterface service = WrapperCall.initialization();
        Call<User> request = service.getCurrentUser(SharedPreferencesHelper.getPrefsName(getApplicationContext(), "token", null));
        request.enqueue(new Callback<User>(){

            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if(response.code() == 200) {
                    SharedPreferencesHelper.setPrefsName(getApplicationContext(), "username", response.body().username);
                    User currentuser = response.body();
                    currentuser.save();
                    txtView_username = (TextView)findViewById(R.id.txtView_username);
                    txtView_username.setText(currentuser.username);

                    txtViewPoints = (TextView)findViewById(R.id.txtViewPoints);
                    txtViewPoints.setText(Integer.toString(currentuser.points));

                    txtGasUser = (TextView)findViewById(R.id.gas_user);
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(2);
                    txtGasUser.setText(df.format(currentuser.gas));

                    txtRockUser = (TextView)findViewById(R.id.rock_user);
                    txtRockUser.setText(df.format(currentuser.minerals));
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

        btn_buildings = findViewById(R.id.btn_buildings);
        btn_buildings.setOnClickListener(this);

        btn_flotte = findViewById(R.id.btn_flotte);
        btn_flotte.setOnClickListener(this);

        btn_galaxy = findViewById(R.id.btn_galaxie);
        btn_galaxy.setOnClickListener(this);

        btn_ships = findViewById(R.id.btn_chantier);
        btn_ships.setOnClickListener(this);

        btn_searches = findViewById(R.id.btn_searches);
        btn_searches.setOnClickListener(this);

        btn_disconnect = findViewById(R.id.btn_logout);
        btn_disconnect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_buildings:
                Intent iBuildings = new Intent(getApplicationContext(), BuildingsActivity.class);
                startActivity(iBuildings);
                break;

            case R.id.btn_flotte:
                Intent iFleet = new Intent(getApplicationContext(), FleetActivity.class);
                startActivity(iFleet);
                break;

            case R.id.btn_galaxie:
                Intent iGalaxy = new Intent(getApplicationContext(), GalaxyActivity.class);
                startActivity(iGalaxy);
                break;

            case R.id.btn_chantier:
                Intent iShips = new Intent(getApplicationContext(), ShipsActivity.class);
                startActivity(iShips);
                break;

            case R.id.btn_searches:
                Intent iSearches = new Intent(getApplicationContext(), SearchesActivity.class);
                startActivity(iSearches);
                break;

            case R.id.btn_logout:
                SharedPreferencesHelper.deletePrefs(getApplicationContext(), "token");
                Intent iLogin = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(iLogin);
                break;
        }
    }
}
