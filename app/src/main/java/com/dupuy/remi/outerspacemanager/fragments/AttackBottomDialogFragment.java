package com.dupuy.remi.outerspacemanager.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.AttackActivity;
import com.dupuy.remi.outerspacemanager.R;
import com.dupuy.remi.outerspacemanager.adapters.ShipAdapter;
import com.dupuy.remi.outerspacemanager.adapters.ShipAttackAdapter;
import com.dupuy.remi.outerspacemanager.models.Fleet;
import com.dupuy.remi.outerspacemanager.service.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.models.helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.models.ListingShips;
import com.dupuy.remi.outerspacemanager.models.WrapperCall;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by rdupuy on 14/05/2018.
 */

public class AttackBottomDialogFragment extends BottomSheetDialogFragment {
    private ProgressBar progressLoader;

    public static AttackBottomDialogFragment newInstance() {
        return new AttackBottomDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.AppThemeBottomeSheet);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.bottom_sheet_ship, container,
                false);

        OuterSpaceManagerInterface service = WrapperCall.initialization();
        Call<Fleet> request = service.getFleetUser(SharedPreferencesHelper.getPrefsName(getContext(), "token", null));
        request.enqueue(new Callback<Fleet>(){

            @Override
            public void onResponse(Call<Fleet> call, retrofit2.Response<Fleet> response) {

                if(response.code() == 200) {

                    progressLoader = view.findViewById(R.id.progress_loader);
                    progressLoader.setVisibility(View.INVISIBLE);

                    Fleet ships = response.body();
                    ListView lvShipAdd = (ListView)view.findViewById(R.id.lv_attack_ships);
                    lvShipAdd.setAdapter(new ShipAttackAdapter((AppCompatActivity)getActivity(), ships.getShips(), (AttackActivity)getActivity(), ((AttackActivity) getActivity()).listShips));
                } else {
                    Toast toast = Toast.makeText(getContext(), "Erreur de connexion", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.wtf("PLOUF", t.toString());
            }
        });

        return view;

    }
}
