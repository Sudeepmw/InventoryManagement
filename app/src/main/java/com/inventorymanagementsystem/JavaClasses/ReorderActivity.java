package com.inventorymanagementsystem.JavaClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inventorymanagementsystem.InventoryEndURL;
import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.ResponseData;
import com.inventorymanagementsystem.RetrofitInstance;
import com.inventorymanagementsystem.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReorderActivity extends AppCompatActivity {
    TextView tv_item_name, tv_quantity, tv_price,tv_rest_name;
    ProgressDialog progressDialog;
    Button btn_reorder;
    SharedPreferences sharedPreferences;
    String session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reorder);

        getSupportActionBar().setTitle("Order Goods");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_name", "");

        tv_rest_name= (TextView) findViewById(R.id.tv_rest_name);
        tv_item_name = (TextView) findViewById(R.id.tv_item_name);
        tv_quantity = (TextView) findViewById(R.id.tv_quantity);
        tv_price = (TextView) findViewById(R.id.tv_price);
        btn_reorder = (Button) findViewById(R.id.btn_reorder);

        tv_item_name.setText("Items   :"+getIntent().getStringExtra("iteam_name"));
       // tv_quantity.setText(getIntent().getStringExtra("iteam_name"));
        tv_price.setText("Total Price   :"+"$"+getIntent().getStringExtra("price"));
        tv_rest_name.setText("Restaurent Name   :"+getIntent().getStringExtra("restaurent_name"));

        btn_reorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });

    }

    private void submitData() {

        progressDialog = new ProgressDialog(ReorderActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);

        Call<ResponseData> call = service.add_orders(tv_item_name.getText().toString(), tv_price.getText().toString(), "25/02/2020", session, "20", tv_rest_name.getText().toString());
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(ReorderActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ReorderActivity.this, OrderConfirmationSplash.class);
                    startActivity(intent);
                    //finish();

                } else {
                    Toast.makeText(ReorderActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ReorderActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    //add this method in your program
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
