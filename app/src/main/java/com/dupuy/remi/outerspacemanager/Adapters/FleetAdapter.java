package com.dupuy.remi.outerspacemanager.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.Ship;
import com.dupuy.remi.outerspacemanager.Models.ShipCreate;
import com.dupuy.remi.outerspacemanager.Models.ShipFleet;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.dupuy.remi.outerspacemanager.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by lledent on 23/01/2018.
 */

public class FleetAdapter extends ArrayAdapter<ShipFleet> {
    private final Context context;
    private final List<ShipFleet> values;
    public FleetAdapter(AppCompatActivity context, List<ShipFleet> values) {
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
        TextView ship_quantity = (TextView) rowView.findViewById(R.id.ship_quantity);

        ship_name.setText(values.get(position).getName());
        ship_quantity.setText(String.valueOf(values.get(position).getAmount()));
        return rowView;
    }
}