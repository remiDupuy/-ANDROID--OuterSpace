package com.dupuy.remi.outerspacemanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dupuy.remi.outerspacemanager.AttackActivity;
import com.dupuy.remi.outerspacemanager.models.ShipFleet;
import com.dupuy.remi.outerspacemanager.models.UserScore;
import com.dupuy.remi.outerspacemanager.R;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class GalaxyAdapter extends RecyclerView.Adapter<GalaxyAdapter.ViewHolder> {

    private List<ShipFleet> fleet;
    private ProgressBar progressLoader;
    private Context context;

    private View dialogView;

    public List<UserScore> users;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView username;
        private final TextView score;
        private final ImageButton btnAttack;

        public ViewHolder(final View itemView) {
            super(itemView);

            username = ((TextView) itemView.findViewById(R.id.txtView_username_galaxy));
            score = ((TextView) itemView.findViewById(R.id.txtView_score));
            btnAttack = (ImageButton) itemView.findViewById(R.id.attackUser);

            btnAttack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchAttack(users.get(getLayoutPosition()));
                }
            });
        }
    }

    public GalaxyAdapter(List<UserScore> usersRetrieve, Context contextActivity) {
        users = usersRetrieve;
        context = contextActivity;
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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.username.setText(users.get(position).getUsername());
        DecimalFormat df = new DecimalFormat("#,###,###");
        holder.score.setText(df.format(users.get(position).getPoints()).replaceAll(",", " "));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void launchAttack(UserScore userScore) {
        Intent i = new Intent(context, AttackActivity.class);
        Gson gson = new Gson();
        String user = gson.toJson(userScore);
        i.putExtra("user", user);
        context.startActivity(i);
    }

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