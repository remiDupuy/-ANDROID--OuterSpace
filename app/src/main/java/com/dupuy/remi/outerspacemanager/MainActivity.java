package com.dupuy.remi.outerspacemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.Responses.Response;
import com.dupuy.remi.outerspacemanager.Models.User;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private TextView txtView_username;

    private TextView txtViewPoints;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OuterSpaceManagerInterface service = WrapperCall.initialization();
        Call<User> request = service.getCurrentUser(SharedPreferencesHelper.getPrefsName(getApplicationContext(), "token", null));
        request.enqueue(new Callback<User>(){

            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if(response.code() == 200) {
                    SharedPreferencesHelper.setPrefsName(getApplicationContext(), "username", response.body().username);
                    User currentuser = response.body();
                    currentuser.save();
                    txtView_username = (TextView)findViewById(R.id.txtView_username);
                    txtView_username.setText(currentuser.username);

                    txtViewPoints = (TextView)findViewById(R.id.txtViewPoints);
                    txtViewPoints.setText(Integer.toString(currentuser.points));
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
