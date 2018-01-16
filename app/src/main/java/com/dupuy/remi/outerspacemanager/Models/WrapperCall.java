package com.dupuy.remi.outerspacemanager.Models;

import com.dupuy.remi.outerspacemanager.Interface.OuterSpaceManagerInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rdupuy on 16/01/2018.
 */

public class WrapperCall {

    public static OuterSpaceManagerInterface initialization() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OuterSpaceManagerInterface service = retrofit.create(OuterSpaceManagerInterface.class);

        return service;
    }

}
