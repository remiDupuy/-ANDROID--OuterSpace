package com.dupuy.remi.outerspacemanager.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.MainActivity;
import com.dupuy.remi.outerspacemanager.Models.Building;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.Ship;
import com.dupuy.remi.outerspacemanager.Models.ShipCreate;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.dupuy.remi.outerspacemanager.R;
import com.dupuy.remi.outerspacemanager.ShipsActivity;

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

public class ShipAdapter extends ArrayAdapter<Ship> {
    private final Context context;
    private final List<Ship> values;
    private TextView ship_name;
    private TextView ship_amount;
    private Button btn_get;
    private int shipQuantityToAdd;

    public ShipAdapter(AppCompatActivity context, List<Ship> values) {
        super(context, R.layout.fleet_adapter, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ship_adapter, parent, false);
        ship_name = (TextView) rowView.findViewById(R.id.ship_name);

        btn_get = (Button)rowView.findViewById(R.id.btn_get);
        btn_get.setTag(position);

        ship_amount = (TextView) rowView.findViewById(R.id.ship_amount);
        ship_amount.addTextChangedListener(new TextWatcher() {
            private String beforeChar;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int val = Integer.parseInt(s.toString());
                    if(val > 9) {
                        s.replace(0, s.length(), "9", 0, 1);
                    } else if(val < 1) {
                        s.replace(0, s.length(), "1", 0, 1);
                    }
                    shipQuantityToAdd = Integer.parseInt(s.toString());
                } catch (NumberFormatException ex) {
                    // Do something
                }
            }
        });

        ship_name.setText(values.get(position).getName());


        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer)v.getTag();
                final long ship_id = values.get(position).getShipId();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                // Add the buttons
                builder.setMessage(R.string.confirm_create_ship);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        OuterSpaceManagerInterface service = WrapperCall.initialization();
                        Call<ResponseBody> request = service.createShip(SharedPreferencesHelper.getPrefsName(getContext(), "token", null), ship_id, new ShipCreate((int)ship_id, shipQuantityToAdd));
                        request.enqueue(new Callback<ResponseBody>(){

                            @Override
                            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                                if(response.code() == 200) {
                                    Toast toast = Toast.makeText(getContext(), "Création en cours", Toast.LENGTH_SHORT);
                                    toast.show();
                                } else {
                                    try {
                                        JSONObject jsonObj = new JSONObject(response.errorBody().string());
                                        Toast toast = Toast.makeText(getContext(), jsonObj.getString("message"), Toast.LENGTH_SHORT);
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

        return rowView;
    }
}