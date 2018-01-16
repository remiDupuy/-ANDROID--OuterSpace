package com.dupuy.remi.outerspacemanager.Interface;

import com.dupuy.remi.outerspacemanager.Models.Response;
import com.dupuy.remi.outerspacemanager.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by rdupuy on 16/01/2018.
 */

public interface OuterSpaceManagerInterface {
    @POST("api/v1/auth/create")
    Call<Response> createUser(@Body User user);

    @POST("api/v1/auth/login")
    Call<Response> signInUser(@Body User user);
}
