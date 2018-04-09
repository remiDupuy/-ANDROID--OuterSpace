package com.dupuy.remi.outerspacemanager.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.dupuy.remi.outerspacemanager.Classes.InputFilterMinMax;
import com.dupuy.remi.outerspacemanager.Models.ShipFleet;
import com.dupuy.remi.outerspacemanager.R;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class FleetAttackAdapter extends ArrayAdapter<ShipFleet> {
    private final Context context;
    private final List<ShipFleet> values;
    public FleetAttackAdapter(Context context, List<ShipFleet> values) {
        super(context, R.layout.fleet_adapter, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fleet_attack_adapter, parent, false);
        TextView ship_name = (TextView) rowView.findViewById(R.id.ship_name);
        TextView ship_quantity = (TextView) rowView.findViewById(R.id.ship_quantity);
        EditText ship_amount = (EditText) rowView.findViewById(R.id.ship_amount);

        ship_name.setText(values.get(position).getName());
        ship_quantity.setText("/"+String.valueOf(values.get(position).getAmount()));

        ship_amount.setFilters(new InputFilter[]{ new InputFilterMinMax("0", String.valueOf(values.get(position).getAmount()))});
        return rowView;
    }
}