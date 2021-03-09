package com.spikotech.sndapp.distributorapp;

import com.google.gson.annotations.SerializedName;

public class Order {

    private int orderId;

    @SerializedName("Shop")
    private String shop;

    @SerializedName("Agent")
    private String agent;

    private int totalAmount;

    private int totalProfit;

    private String orderDate;

    public int getOrderId() {
        return orderId;
    }

    public String getShop() {
        return shop;
    }

    public String getAgent() {
        return agent;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getTotalProfit() {
        return totalProfit;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
