package com.dupuy.remi.outerspacemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;
import com.dupuy.remi.outerspacemanager.Models.Helpers.SharedPreferencesHelper;
import com.dupuy.remi.outerspacemanager.Models.Response;
import com.dupuy.remi.outerspacemanager.Models.ResponseError;
import com.dupuy.remi.outerspacemanager.Models.User;
import com.dupuy.remi.outerspacemanager.Models.WrapperCall;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {


    private EditText txt_login_register;
    private EditText txt_passwd_register;
    private EditText txt_mail_register;
    private EditText txt_login_login;
    private EditText txt_passwd_login;
    
    private Button btn_login;
    private Button btn_register;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = SharedPreferencesHelper.getPrefsName(getApplicationContext(), "token", null);
        if(token != null && !token.isEmpty()) {
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(myIntent);
        }

        setContentView(R.layout.activity_signup);

        this.txt_mail_register = (EditText)findViewById(R.id.txt_mail_register);
        this.txt_login_register = (EditText)findViewById(R.id.txt_login_register);
        this.txt_passwd_register = (EditText)findViewById(R.id.txt_passwd_register);
        this.btn_register = (Button)findViewById(R.id.btn_register);
        this.btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.email = txt_mail_register.getText().toString();
                user.username = txt_login_register.getText().toString();
                user.password = txt_passwd_register.getText().toString();

                OuterSpaceManagerInterface service = WrapperCall.initialization();
                Call<Response> request = service.createUser(user);
                request.enqueue(new Callback<Response> (){

                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if(response.code() == 200) {
                            SharedPreferencesHelper.setPrefsName(getApplicationContext(), "token", response.body().token);
                            SharedPreferencesHelper.setPrefsName(getApplicationContext(), "username", txt_login_login.getText().toString());
                            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(myIntent);
                        } else {
                                Toast toast = Toast.makeText(getApplicationContext(), "Erreur de connexion", 1);
                                toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.wtf("PLOUF", t.toString());
                    }
                });
            }
        });


        this.txt_login_login = (EditText)findViewById(R.id.txt_login_login);
        this.txt_passwd_login = (EditText)findViewById(R.id.txt_passwd_login);
        this.btn_login = (Button)findViewById(R.id.btn_login);
        this.btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.username = txt_login_login.getText().toString();
                user.password = txt_passwd_login.getText().toString();

                OuterSpaceManagerInterface service = WrapperCall.initialization();
                Call<Response> request = service.signInUser(user);
                request.enqueue(new Callback<Response> (){

                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if(response.code() == 200) {
                            SharedPreferencesHelper.setPrefsName(getApplicationContext(), "token", response.body().token);
                            SharedPreferencesHelper.setPrefsName(getApplicationContext(), "username", txt_login_login.getText().toString());
                            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(myIntent);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Erreur de connexion", 1);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.wtf("PLOUF", "aahah2");
                    }
                });
            }
        });
    }
}
