package com.dupuy.remi.outerspacemanager.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.FleetActivity;
import com.dupuy.remi.outerspacemanager.GalaxyActivity;
import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Fleet;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.ListingShipsSend;
import com.dupuy.remi.outerspacemanager.Models.Ship;
import com.dupuy.remi.outerspacemanager.Models.ShipCreate;
import com.dupuy.remi.outerspacemanager.Models.ShipFleet;
import com.dupuy.remi.outerspacemanager.Models.UserScore;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.dupuy.remi.outerspacemanager.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by lledent on 23/01/2018.
 */

public class GalaxyAdapter extends RecyclerView.Adapter<GalaxyAdapter.ViewHolder> {

    private List<ShipFleet> fleet;
    private ProgressBar progressLoader;

    private View dialogView;

    private List<UserScore> users;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView username;
        private final TextView score;

        public ViewHolder(final View itemView) {
            super(itemView);

            username = ((TextView) itemView.findViewById(R.id.txtView_username_galaxy));
            score = ((TextView) itemView.findViewById(R.id.txtView_score));

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    new AlertDialog.Builder(itemView.getContext())
//                            .setTitle(currentPair.first)
//                            .setMessage(currentPair.second)
//                            .show();
//                }
//            });
        }
    }

    public GalaxyAdapter(List<UserScore> usersRetrieve) {
        users = usersRetrieve;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GalaxyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.galaxy_adapter, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.username.setText(users.get(position).getUsername());
        holder.score.setText(String.valueOf(users.get(position).getPoints()));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return users.size();
    }

//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View rowView = inflater.inflate(R.layout.galaxy_adapter, parent, false);
//        TextView username = (TextView) rowView.findViewById(R.id.txtView_username_galaxy);
//        username.setText(values.get(position).getUsername());
//
//        TextView score = (TextView) rowView.findViewById(R.id.txtView_score);
//        DecimalFormat df = new DecimalFormat("#,###,###");
//        score.setText(df.format(values.get(position).getPoints()).replaceAll(",", " "));
//
//
//        ImageButton attackUser = (ImageButton)rowView.findViewById(R.id.attackUser);
//        attackUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                manageAttack(values.get(position));
//            }
//        });
//
//        return rowView;
//    }

//    public void manageAttack(final UserScore user) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
//        this.setAlertDialogView(builder);
//        // Add the buttons
//        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//
//                ListingShipsSend listingShipsSend = new ListingShipsSend();
//                OuterSpaceManagerInterface service = WrapperCall.initialization();
//                Call<ResponseBody> request = service.attackUser(SharedPreferencesHelper.getPrefsName(getContext(), "token", null), user.getUsername(), listingShipsSend);
//                request.enqueue(new Callback<ResponseBody>(){
//
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                        if(response.code() == 200) {
//                            Toast toast = Toast.makeText(getContext(), "Création en cours", Toast.LENGTH_SHORT);
//                            toast.show();
//                        } else {
//                            try {
//                                JSONObject jsonObj = new JSONObject(response.errorBody().string());
//                                Toast toast = Toast.makeText(getContext(), jsonObj.getString("message"), Toast.LENGTH_SHORT);
//                                toast.show();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//                        Log.wtf("PLOUF", t.toString());
//                    }
//                });
//            }
//        });
//        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User cancelled the dialog
//            }
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//
//    private void setAlertDialogView(AlertDialog.Builder builder) {
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        dialogView = inflater.inflate(R.layout.activity_fleet, null);
//
//
//        OuterSpaceManagerInterface service = WrapperCall.initialization();
//        Call<Fleet> request = service.getFleetUser(SharedPreferencesHelper.getPrefsName(context, "token", null));
//        request.enqueue(new Callback<Fleet>(){
//
//            @Override
//            public void onResponse(Call<Fleet> call, retrofit2.Response<Fleet> response) {
//
//                progressLoader = dialogView.findViewById(R.id.progress_loader);
//                progressLoader.setVisibility(View.INVISIBLE);
//
//                if(response.code() == 200) {
//
//
//                    fleet = response.body().getShips();
//                    lv_fleet = (ListView)dialogView.findViewById(R.id.lv_fleet);
//                    TextView emptyText = (TextView)dialogView.findViewById(R.id.empty);
//                    lv_fleet.setEmptyView(emptyText);
//                    lv_fleet.setAdapter(new FleetAttackAdapter(context , fleet));
//                } else {
//                    Toast toast = Toast.makeText(context, "Erreur de connexion", Toast.LENGTH_SHORT);
//                    toast.show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                Log.wtf("PLOUF", t.toString());
//            }
//        });
//
//        builder.setView(dialogView);
//    }
}