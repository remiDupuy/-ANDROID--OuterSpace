package com.dupuy.remi.outerspacemanager.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dupuy.remi.outerspacemanager.Models.Ship;
import com.dupuy.remi.outerspacemanager.Models.UserScore;
import com.dupuy.remi.outerspacemanager.R;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class GalaxyAdapter extends ArrayAdapter<UserScore> {
    private final Context context;
    private final List<UserScore> values;
    public GalaxyAdapter(Context context, List<UserScore> values) {
        super(context, R.layout.building_adapter, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.galaxy_adapter, parent, false);
        TextView username = (TextView) rowView.findViewById(R.id.txtView_username_galaxy);
        username.setText(values.get(position).getUsername());

        TextView score = (TextView) rowView.findViewById(R.id.txtView_score);
        score.setText(String.valueOf(values.get(position).getPoints()));

        return rowView;
    }
}