package com.dupuy.remi.outerspacemanager.Interface;

import com.dupuy.remi.outerspacemanager.Models.Fleet;
import com.dupuy.remi.outerspacemanager.Models.ListingBuildings;
import com.dupuy.remi.outerspacemanager.Models.ListingSearches;
import com.dupuy.remi.outerspacemanager.Models.ListingShips;
import com.dupuy.remi.outerspacemanager.Models.ListingUsers;
import com.dupuy.remi.outerspacemanager.Models.Responses.Response;
import com.dupuy.remi.outerspacemanager.Models.Responses.ResponseCreateBuilding;
import com.dupuy.remi.outerspacemanager.Models.ShipCreate;
import com.dupuy.remi.outerspacemanager.Models.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @GET("api/v1/users/get")
    Call<User> getCurrentUser(@Header("x-access-token") String token);

    @GET("api/v1/users/0/20")
    Call<ListingUsers> getUsers(@Header("x-access-token") String token);

    @GET("api/v1/buildings/list")
    Call<ListingBuildings> getBuildings(@Header("x-access-token") String token);

    @POST("api/v1/buildings/create/{id}")
    Call<ResponseCreateBuilding> createBuilding(@Header("x-access-token") String token, @Path("id") int buildingId);

    @GET("api/v1/fleet/list")
    Call<Fleet> getFleetUser(@Header("x-access-token") String token);

    @GET("api/v1/ships")
    Call<ListingShips> getShips(@Header("x-access-token") String token);

    @POST("api/v1/ships/create/{id}")
    Call<ResponseBody> createShip(@Header("x-access-token") String token, @Path("id") long shipId, @Body ShipCreate shipCreate);

    @GET("api/v1/searches/list")
    Call<ListingSearches> getSearches(@Header("x-access-token") String token);

    @POST("api/v1/searches/create/{id}")
    Call<ResponseBody> createSearch(@Header("x-access-token") String token, @Path("id") int searchId);
}
