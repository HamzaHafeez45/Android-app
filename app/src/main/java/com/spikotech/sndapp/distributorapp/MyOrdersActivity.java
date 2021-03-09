package com.spikotech.sndapp.distributorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyOrdersActivity extends AppCompatActivity {
    private TextView textViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        getSupportActionBar().setTitle("My Orders");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewOrder = findViewById(R.id.tv_order);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://sndwebapi.spikotech.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebappApi webappApi = retrofit.create(WebappApi.class);

        Call<List<Order>> call = webappApi.getOrder();

        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (!response.isSuccessful()) {
                    textViewOrder.setText("Code: "+response.code());
                    return;
                }

                List<Order> orders = response.body();

                for (Order order: orders) {
                    String content = "";
                    content += "Order Id: " + order.getOrderId() + "\n";
                    content += "Shop Name: " + order.getShop() + "\n";
                    content += "Agent Name: " + order.getAgent() + "\n";
                    content += "Total Amount: " + order.getTotalAmount() + "\n";
                    content += "Profit : " + order.getTotalProfit() + "\n";
                    content += "Order Date: " + order.getOrderDate() + "\n";

                    textViewOrder.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                textViewOrder.setText(t.getMessage());
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrdersActivity.this, AddOrderActivity.class);
                startActivity(intent);
            }
        });
    }

}