package com.dupuy.remi.outerspacemanager.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dupuy.remi.outerspacemanager.Models.Search;
import com.dupuy.remi.outerspacemanager.Models.Ship;
import com.dupuy.remi.outerspacemanager.R;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class SearchesAdapter extends ArrayAdapter<Search> {
    private final Context context;
    private final List<Search> values;
    public SearchesAdapter(Context context, List<Search> values) {
        super(context, R.layout.fleet_adapter, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fleet_adapter, parent, false);
        TextView ship_name = (TextView) rowView.findViewById(R.id.ship_name);
        ship_name.setText(values.get(position).getName());

        return rowView;
    }
}