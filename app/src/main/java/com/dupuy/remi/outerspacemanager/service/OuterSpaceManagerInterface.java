package com.dupuy.remi.outerspacemanager.service;

import com.dupuy.remi.outerspacemanager.models.Fleet;
import com.dupuy.remi.outerspacemanager.models.ListingBuildings;
import com.dupuy.remi.outerspacemanager.models.ListingSearches;
import com.dupuy.remi.outerspacemanager.models.ListingShips;
import com.dupuy.remi.outerspacemanager.models.ListingShipsSend;
import com.dupuy.remi.outerspacemanager.models.ListingUsers;
import com.dupuy.remi.outerspacemanager.models.responses.Response;
import com.dupuy.remi.outerspacemanager.models.responses.ResponseCreateBuilding;
import com.dupuy.remi.outerspacemanager.models.ShipCreate;
import com.dupuy.remi.outerspacemanager.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
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

    @POST("/api/v1/fleet/attack/{user}")
    Call<ResponseBody> attackUser(@Header("x-access-token") String token, @Path("user") String userName, @Body ListingShipsSend shipsSend);
}
