package com.spikotech.sndapp.distributorapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebappApi {

    @GET("stock")
    Call<List<Stock>> getStock();

    @GET("order")
    Call<List<Order>> getOrder();
}
