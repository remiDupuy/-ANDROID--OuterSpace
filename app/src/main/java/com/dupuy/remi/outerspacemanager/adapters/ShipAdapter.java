package com.dupuy.remi.outerspacemanager.adapters;

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
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.service.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.models.helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.models.Ship;
import com.dupuy.remi.outerspacemanager.models.ShipCreate;
import com.dupuy.remi.outerspacemanager.models.WrapperCall;
import com.dupuy.remi.outerspacemanager.R;
import com.dupuy.remi.outerspacemanager.databinding.ShipAdapterBinding;
import com.warkiz.widget.IndicatorSeekBar;

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
    private Button btnCreateShip;

    private IndicatorSeekBar ship_quantity;


    public ShipAdapter(AppCompatActivity context, List<Ship> values) {
        super(context, R.layout.fleet_adapter, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        final ShipAdapterBinding binding = ShipAdapterBinding.inflate(inflater, parent, false);
        binding.setShip(values.get(position));
        binding.setShipCreate(new ShipCreate(values.get(position).getShipId(), 1));

        ship_quantity = (IndicatorSeekBar)binding.getRoot().findViewById(R.id.ship_quantity);
        ship_quantity.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
                ShipCreate ship = binding.getShipCreate();
                ship.setAmount(progress);
                binding.setShipCreate(ship);
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {}

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {}

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {}
        });


        btnCreateShip = binding.getRoot().findViewById(R.id.btnCreateShip);
        btnCreateShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                // Add the buttons
                builder.setMessage(R.string.confirm_create_ship);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        OuterSpaceManagerInterface service = WrapperCall.initialization();

                        Call<ResponseBody> request = service.createShip(SharedPreferencesHelper.getPrefsName(getContext(), "token", null), binding.getShipCreate().getShipId(), binding.getShipCreate());
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

        return binding.getRoot();
    }
}