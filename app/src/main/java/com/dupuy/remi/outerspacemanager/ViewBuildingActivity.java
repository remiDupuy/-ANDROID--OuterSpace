package com.dupuy.remi.outerspacemanager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Building;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.Responses.Response;
import com.dupuy.remi.outerspacemanager.Models.Responses.ResponseCreateBuilding;
import com.dupuy.remi.outerspacemanager.Models.User;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

public class ViewBuildingActivity extends Activity {

    private ImageView img_building;
    private TextView building_level;
    private TextView amountOfEffectByLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_building);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent intent = getIntent();
        final String jsonBuilding = intent.getStringExtra("building");
        Gson gson = new Gson();
        final Building building = gson.fromJson(jsonBuilding, Building.class);

        this.initLayout(building);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(ViewBuildingActivity.this);
            // Add the buttons
            builder.setMessage(R.string.confirm_create);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    OuterSpaceManagerInterface service = WrapperCall.initialization();
                    Call<ResponseCreateBuilding> request = service.createBuilding(SharedPreferencesHelper.getPrefsName(getApplicationContext(), "token", null), building.getBuildingId());
                    request.enqueue(new Callback<ResponseCreateBuilding>(){

                        @Override
                        public void onResponse(Call<ResponseCreateBuilding> call, retrofit2.Response<ResponseCreateBuilding> response) {
                            if(response.code() == 200) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Ajout réussi", Toast.LENGTH_SHORT);
                            } else {
                                try {
                                    JSONObject jsonObj = new JSONObject(response.errorBody().string());
                                    Toast toast = Toast.makeText(getApplicationContext(), jsonObj.getString("message"), Toast.LENGTH_SHORT);
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
        });
    }

    private void initLayout(Building building) {
        this.img_building = findViewById(R.id.img_building);
        Glide.with(this).load(building.getImageUrl()).into(this.img_building);

        this.building_level = findViewById(R.id.building_level);
        this.building_level.setText(Integer.toString(building.getLevel()));


        this.amountOfEffectByLevel = findViewById(R.id.amountOfEffectByLevel);
        this.amountOfEffectByLevel.setText(Integer.toString(building.getAmountOfEffectByLevel()));

    }

}
