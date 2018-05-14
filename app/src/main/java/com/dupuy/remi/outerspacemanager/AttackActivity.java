package com.dupuy.remi.outerspacemanager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.adapters.AttackAdapter;
import com.dupuy.remi.outerspacemanager.fragments.AttackBottomDialogFragment;
import com.dupuy.remi.outerspacemanager.interfaces.OnShipAdded;
import com.dupuy.remi.outerspacemanager.interfaces.OnShipChanged;
import com.dupuy.remi.outerspacemanager.models.ListingShipsSend;
import com.dupuy.remi.outerspacemanager.models.ShipAttack;
import com.dupuy.remi.outerspacemanager.models.ShipFleet;
import com.dupuy.remi.outerspacemanager.models.UserScore;
import com.dupuy.remi.outerspacemanager.models.WrapperCall;
import com.dupuy.remi.outerspacemanager.models.helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.service.OuterSpaceManagerInterface;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by rdupuy on 14/05/2018.
 */

public class AttackActivity extends AppCompatActivity implements OnShipAdded, OnShipChanged {
    private UserScore user;
    private ProgressBar progressLoader;

    private ListView lvAttack;
    public List<ShipFleet> listShips = new ArrayList<ShipFleet>();
    private List<ShipAttack> listAttack = new ArrayList<>();
    private AttackAdapter attackAdapter;
    private AttackBottomDialogFragment attackBottomDialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack);

        Intent i = getIntent();
        String stringUser = i.getStringExtra("user");
        Gson gson = new Gson();
        this.user = gson.fromJson(stringUser, UserScore.class);

        if(this.user == null) {
            finish();
        }

        progressLoader = findViewById(R.id.progress_loader);
        progressLoader.setVisibility(View.INVISIBLE);

        this.initLayout();
    }

    private void initLayout() {
        Resources res = getResources();
        String text = res.getString(R.string.attack_user, user.getUsername());

        TextView txtUser = (TextView)findViewById(R.id.txt_username_attack);
        txtUser.setText(text);

        View emptyView = getLayoutInflater().inflate(R.layout.footer_add_ship, lvAttack, false);
        lvAttack = (ListView)findViewById(R.id.lv_attack);

        lvAttack.addFooterView(emptyView);
        attackAdapter = new AttackAdapter(this, this.listShips, this);
        lvAttack.setAdapter(attackAdapter);

        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attackBottomDialogFragment =
                        AttackBottomDialogFragment.newInstance();
                attackBottomDialogFragment.show(getSupportFragmentManager(), "display");
            }
        });

        FloatingActionButton fabAttack = (FloatingActionButton)findViewById(R.id.fab_attack);
        fabAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack();
            }
        });
    }

    private void attack() {
        OuterSpaceManagerInterface service = WrapperCall.initialization();

        ListingShipsSend shipsSend = new ListingShipsSend();
        shipsSend.setShips(listAttack);
        Call<ResponseBody> request = service.attackUser(SharedPreferencesHelper.getPrefsName(this, "token", null), user.getUsername(), shipsSend);
        request.enqueue(new Callback<ResponseBody>(){

            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if(response.code() == 200) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Création en cours", Toast.LENGTH_SHORT);
                    toast.show();
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

    @Override
    public void onShipAdd(ShipFleet ship) {
        this.listShips.add(ship);

        ShipAttack shipAttack = new ShipAttack(ship.getShipId(), 0);
        this.listAttack.add(shipAttack);
        this.attackAdapter.notifyDataSetChanged();
        this.attackBottomDialogFragment.dismiss();
    }

    @Override
    public void onShipChange(ShipFleet ship, int quantity) {
        this.listAttack.get(ship.getShipId()).setAmount(quantity);
    }
}
