package com.dupuy.remi.outerspacemanager.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dupuy.remi.outerspacemanager.Classes.ProgressBuilding;
import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Building;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.Helpers.Tools;
import com.dupuy.remi.outerspacemanager.Models.Responses.ResponseCreateBuilding;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.dupuy.remi.outerspacemanager.R;
import com.dupuy.remi.outerspacemanager.ViewBuildingActivity;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by lledent on 23/01/2018.
 */

public class BuildingAdapter extends ArrayAdapter<Building> {
    private final Context context;
    private final List<Building> values;
    public BuildingAdapter(Context context, List<Building> values) {
        super(context, R.layout.building_adapter, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.building_adapter, parent, false);

        this.initLayout(rowView, position);

        return rowView;
    }

    private void initLayout(View rowView, final int position) {
        TextView building_name = (TextView) rowView.findViewById(R.id.building_name);
        building_name.setText(values.get(position).getName());

        ImageView image = (ImageView) rowView.findViewById(R.id.image_building);
        Glide.with(rowView).load(values.get(position).getImageUrl()).into(image);

        final Button btnUpgrade = (Button) rowView.findViewById(R.id.btnUpgradeBuilding);
        btnUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildingAdapter.this.upgradeHandler(position, v);
            }
        });

        ImageButton btnExpand = (ImageButton)rowView.findViewById(R.id.btnExpandCard);
        btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildingAdapter.this.toggleIcExpand(v);

                ExpandableRelativeLayout expandableLayout
                        = (ExpandableRelativeLayout) ((LinearLayout)v.getParent().getParent().getParent()).findViewById(R.id.expandableLayout);

                expandableLayout.toggle();
            }
        });

        if(SharedPreferencesHelper.getPrefsName(context, "upgrade_"+values.get(position).getBuildingId(), null) != null) {
            final ProgressBar buildingTimer = (ProgressBar)rowView.findViewById(R.id.progressUpgrade);
            Gson gson = new Gson();
            final ProgressBuilding pbBuilding = gson.fromJson(SharedPreferencesHelper.getPrefsName(context, "upgrade_"+values.get(position).getBuildingId(), null), ProgressBuilding.class);
            pbBuilding.getCal().setTimeZone(TimeZone.getTimeZone("GMT+0"));
            long milliseconds = (pbBuilding.getCal().getTimeInMillis() - Calendar.getInstance().getTimeInMillis());
            if(milliseconds > 0) {
                btnUpgrade.setEnabled(false);
                btnUpgrade.setTextColor(Color.GRAY);
                new CountDownTimer(milliseconds, 1000) {
                    public void onTick(long millisUntilFinished) {
                        buildingTimer.setProgress((int)(millisUntilFinished / 1000 * 100) / pbBuilding.getBuildingTime());
                    }

                    public void onFinish() {
                        btnUpgrade.setEnabled(true);
                        btnUpgrade.setTextColor(BuildingAdapter.this.getThemeAccentColor(context));
                    }
                }.start();
                buildingTimer.setVisibility(View.VISIBLE);
            } else {
                SharedPreferencesHelper.deletePrefs(context, "upgrade_"+values.get(position).getBuildingId());
            }
        }

        int level = values.get(position).getLevel();
        TextView buildingLevel = (TextView)rowView.findViewById(R.id.building_level);
        buildingLevel.setText(Integer.toString(level));

        TextView buildingGas = (TextView)rowView.findViewById(R.id.building_gas);
        buildingGas.setText(Integer.toString(values.get(position).getGasCostByLevel() * level + values.get(position).getGasCostLevel0()));

        TextView buildingMinerals = (TextView)rowView.findViewById(R.id.building_materials);
        buildingMinerals.setText(Integer.toString(values.get(position).getMineralCostByLevel() * level + values.get(position).getMineralCostLevel0()));

        TextView buildingTime = (TextView)rowView.findViewById(R.id.building_construction_time);
        buildingTime.setText(Tools.secondToHumanReadableTime(values.get(position).getTimeToBuildByLevel() * level + values.get(position).getTimeToBuildLevel0()));

        TextView buildingEffect = (TextView)rowView.findViewById(R.id.building_effect);
        buildingEffect.setText(values.get(position).getEffect() == null ? "Pas d'effets" : values.get(position).getEffect());
    }

    private void toggleIcExpand(final View v) {
        RotateAnimation rotate;
        if (v.getRotation() == 180.0) {
            v.setRotation(0);
            rotate = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        } else {
            v.setRotation(180);
            rotate = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }

        rotate.setDuration(200);
        rotate.setInterpolator(new LinearInterpolator());

        v.startAnimation(rotate);
    }

    private void upgradeHandler(final int position, final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // Add the buttons
        builder.setMessage(R.string.confirm_create);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                OuterSpaceManagerInterface service = WrapperCall.initialization();
                Call<ResponseCreateBuilding> request = service.createBuilding(SharedPreferencesHelper.getPrefsName(context, "token", null), values.get(position).getBuildingId());
                request.enqueue(new Callback<ResponseCreateBuilding>(){

                    @Override
                    public void onResponse(Call<ResponseCreateBuilding> call, retrofit2.Response<ResponseCreateBuilding> response) {
                        if(response.code() == 200) {
                            Toast toast = Toast.makeText(context, "Création en cours", Toast.LENGTH_SHORT);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeZone(TimeZone.getTimeZone("GMT+0"));

                            final int timeBuild = values.get(position).getTimeToBuildByLevel()*values.get(position).getLevel() + values.get(position).getTimeToBuildLevel0();
                            calendar.add(Calendar.SECOND, timeBuild);

                            ProgressBuilding progress = new ProgressBuilding(calendar, timeBuild);

                            Gson gson = new Gson();
                            SharedPreferencesHelper.setPrefsName(context, "upgrade_"+values.get(position).getBuildingId(), gson.toJson(progress));

                            ((Button)v).setEnabled(false);
                            ((Button)v).setTextColor(Color.GRAY);
                            final ProgressBar pb = (ProgressBar) ((LinearLayout) v.getParent().getParent()).findViewById(R.id.progressUpgrade);
                            pb.setVisibility(View.VISIBLE);
                            new CountDownTimer(timeBuild * 1000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    pb.setProgress((int)(millisUntilFinished / 1000 * 100) / timeBuild);
                                }

                                public void onFinish() {
                                }
                            }.start();
                            toast.show();
                        } else {
                            try {
                                JSONObject jsonObj = new JSONObject(response.errorBody().string());
                                Toast toast = Toast.makeText(context, jsonObj.getString("message"), Toast.LENGTH_SHORT);
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

    public static int getThemeAccentColor (final Context context) {
        final TypedValue value = new TypedValue ();
        context.getTheme ().resolveAttribute (R.attr.colorAccent, value, true);
        return value.data;
    }
}