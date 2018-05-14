package com.dupuy.remi.outerspacemanager.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.dupuy.remi.outerspacemanager.interfaces.OnShipChanged;
import com.dupuy.remi.outerspacemanager.models.Ship;
import com.dupuy.remi.outerspacemanager.R;
import com.dupuy.remi.outerspacemanager.databinding.AttackShipAdapterBinding;
import com.dupuy.remi.outerspacemanager.models.ShipCreate;
import com.dupuy.remi.outerspacemanager.models.ShipFleet;
import com.warkiz.widget.IndicatorSeekBar;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class AttackAdapter extends ArrayAdapter<ShipFleet> {
    private final Context context;
    private final List<ShipFleet> values;
    private final OnShipChanged onShipChanged;
    private IndicatorSeekBar ship_quantity;

    public AttackAdapter(Context context, List<ShipFleet> values, OnShipChanged onShipChanged) {
        super(context, R.layout.attack_ship_adapter, values);
        this.context = context;
        this.values = values;
        this.onShipChanged = onShipChanged;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final AttackShipAdapterBinding binding = AttackShipAdapterBinding.inflate(inflater, parent, false);


        binding.setShip(values.get(position));


        ship_quantity = (IndicatorSeekBar)binding.getRoot().findViewById(R.id.ship_quantity_attack);
        ship_quantity.setMax(values.get(position).getAmount());
        ship_quantity.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {

                onShipChanged.onShipChange(values.get(position), progress);
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {}

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {}

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {}
        });

        return binding.getRoot();
    }
}