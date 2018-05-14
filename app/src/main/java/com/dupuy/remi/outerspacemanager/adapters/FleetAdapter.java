package com.dupuy.remi.outerspacemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.dupuy.remi.outerspacemanager.models.ShipFleet;
import com.dupuy.remi.outerspacemanager.R;
import com.dupuy.remi.outerspacemanager.databinding.FleetAdapterBinding;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class FleetAdapter extends ArrayAdapter<ShipFleet> {
    private final Context context;
    private final List<ShipFleet> values;
    public FleetAdapter(Context context, List<ShipFleet> values) {
        super(context, R.layout.fleet_adapter, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        FleetAdapterBinding binding = FleetAdapterBinding.inflate(inflater, parent, false);

        binding.setShip(values.get(position));
        return binding.getRoot();
    }
}