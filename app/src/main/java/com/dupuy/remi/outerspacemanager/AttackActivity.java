package com.dupuy.remi.outerspacemanager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dupuy.remi.outerspacemanager.adapters.AttackAdapter;
import com.dupuy.remi.outerspacemanager.fragments.AttackBottomDialogFragment;
import com.dupuy.remi.outerspacemanager.interfaces.OnShipAdded;
import com.dupuy.remi.outerspacemanager.models.Ship;
import com.dupuy.remi.outerspacemanager.models.UserScore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdupuy on 14/05/2018.
 */

public class AttackActivity extends AppCompatActivity implements OnShipAdded{
    private UserScore user;
    private ProgressBar progressLoader;

    private ListView lvAttack;
    public List<Ship> listShips = new ArrayList<Ship>();
    private AttackAdapter attackAdapter;
    private AttackBottomDialogFragment attackBottomDialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack);

        Intent i = getIntent();
        String stringUser = i.getStringExtra("user");
        Gson gson = new Gson();
        this.user = gson.fromJson(stringUser, UserScore.class);

        if(this.user == null) {
            finish();
        }

        progressLoader = findViewById(R.id.progress_loader);
        progressLoader.setVisibility(View.INVISIBLE);

        this.initLayout();
    }

    private void initLayout() {
        Resources res = getResources();
        String text = res.getString(R.string.attack_user, user.getUsername());

        TextView txtUser = (TextView)findViewById(R.id.txt_username_attack);
        txtUser.setText(text);

        View emptyView = getLayoutInflater().inflate(R.layout.footer_add_ship, lvAttack, false);
        lvAttack = (ListView)findViewById(R.id.lv_attack);

        lvAttack.addFooterView(emptyView);
        attackAdapter = new AttackAdapter(this, this.listShips);
        lvAttack.setAdapter(attackAdapter);

        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attackBottomDialogFragment =
                        AttackBottomDialogFragment.newInstance();
                attackBottomDialogFragment.show(getSupportFragmentManager(), "display");
            }
        });
    }

    @Override
    public void onShipAdd(Ship ship) {
        this.listShips.add(ship);
        this.attackAdapter.notifyDataSetChanged();
        this.attackBottomDialogFragment.dismiss();
    }
}
