package com.dupuy.remi.outerspacemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.adapters.GalaxyAdapter;
import com.dupuy.remi.outerspacemanager.service.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.models.helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.models.ListingUsers;
import com.dupuy.remi.outerspacemanager.models.WrapperCall;

import retrofit2.Call;
import retrofit2.Callback;

public class GalaxyActivity extends AppCompatActivity {


    private RecyclerView rv_galaxy;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ListingUsers users;
    private ProgressBar progressLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaxy);

        OuterSpaceManagerInterface service = WrapperCall.initialization();
        Call<ListingUsers> request = service.getUsers(SharedPreferencesHelper.getPrefsName(getApplicationContext(), "token", null));
        request.enqueue(new Callback<ListingUsers>(){

            @Override
            public void onResponse(Call<ListingUsers> call, retrofit2.Response<ListingUsers> response) {
                progressLoader = findViewById(R.id.progress_loader);
                progressLoader.setVisibility(View.INVISIBLE);
                if(response.code() == 200) {
                    users = response.body();
                    rv_galaxy = (RecyclerView) findViewById(R.id.rv_galaxy);
                    TextView emptyText = (TextView)findViewById(R.id.empty_galaxy);
                    rv_galaxy.setHasFixedSize(true);

                    // use a linear layout manager
                    mLayoutManager = new LinearLayoutManager(GalaxyActivity.this);
                    rv_galaxy.setLayoutManager(mLayoutManager);

                    // specify an adapter (see also next example)
                    mAdapter = new GalaxyAdapter(users.getUsers(), GalaxyActivity.this);
                    rv_galaxy.setAdapter(mAdapter);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Erreur de connexion", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.wtf("PLOUF", t.toString());
            }
        });

    }

}
