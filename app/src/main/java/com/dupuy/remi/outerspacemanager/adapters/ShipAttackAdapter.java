package com.dupuy.remi.outerspacemanager.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.AttackActivity;
import com.dupuy.remi.outerspacemanager.databinding.ShipAttackAdapterBinding;
import com.dupuy.remi.outerspacemanager.fragments.AttackBottomDialogFragment;
import com.dupuy.remi.outerspacemanager.interfaces.OnShipAdded;
import com.dupuy.remi.outerspacemanager.service.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.models.helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.models.Ship;
import com.dupuy.remi.outerspacemanager.models.ShipCreate;
import com.dupuy.remi.outerspacemanager.models.WrapperCall;
import com.dupuy.remi.outerspacemanager.R;
import com.warkiz.widget.IndicatorSeekBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by lledent on 23/01/2018.
 */

public class ShipAttackAdapter extends ArrayAdapter<Ship> {
    private final Context context;
    private final List<Ship> values;
    private final OnShipAdded onShipAdded;
    private ImageButton btnAddShip;

    private List<Ship> shipAdded;

    public ShipAttackAdapter(AppCompatActivity context, List<Ship> values, OnShipAdded onShipAdded, List<Ship> shipAdded) {
        super(context, R.layout.ship_attack_adapter, values);
        this.context = context;
        this.values = values;
        this.onShipAdded = onShipAdded;
        this.shipAdded = shipAdded;
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        final ShipAttackAdapterBinding binding = ShipAttackAdapterBinding.inflate(inflater, parent, false);
        binding.setShip(values.get(position));

        btnAddShip = binding.getRoot().findViewById(R.id.add_ship_attack);

        if(shipAdded.contains(values.get(position))) {
            btnAddShip.setEnabled(false);
            btnAddShip.setClickable(false);
            btnAddShip.setBackgroundResource(R.drawable.circle_shape_disabled);
        }

        btnAddShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShipAdded.onShipAdd(values.get(position));
            }
        });

        return binding.getRoot();
    }
}