package com.dupuy.remi.outerspacemanager.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.dupuy.remi.outerspacemanager.models.Ship;
import com.dupuy.remi.outerspacemanager.R;
import com.dupuy.remi.outerspacemanager.databinding.AttackShipAdapterBinding;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class AttackAdapter extends ArrayAdapter<Ship> {
    private final Context context;
    private final List<Ship> values;
    public AttackAdapter(Context context, List<Ship> values) {
        super(context, R.layout.attack_ship_adapter, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AttackShipAdapterBinding binding = AttackShipAdapterBinding.inflate(inflater, parent, false);


        binding.setShip(values.get(position));
        return binding.getRoot();
    }
}