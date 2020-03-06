package com.inventorymanagementsystem.JavaClasses;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inventorymanagementsystem.InventoryEndURL;
import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.RetrofitInstance;
import com.inventorymanagementsystem.Utils;
import com.inventorymanagementsystem.adapterView.ReceivedGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceivedGoodsActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<ReceivedGoods> a1;
    SharedPreferences sharedPreferences;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_goods);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");

        getSupportActionBar().setTitle("Delivered Goods");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);


        a1= new ArrayList<>();
        serverData();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(ReceivedGoodsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<List<ReceivedGoods>> call = service.getUserDeliveredOrders(uname);
        call.enqueue(new Callback<List<ReceivedGoods>>() {
            @Override
            public void onResponse(Call<List<ReceivedGoods>> call, Response<List<ReceivedGoods>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(ReceivedGoodsActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    list_view.setAdapter(new ReceivedGoodsAdapter(a1, ReceivedGoodsActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<ReceivedGoods>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ReceivedGoodsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.idlogout:
                final AlertDialog.Builder builder = new AlertDialog.Builder(ReceivedGoodsActivity.this);
                builder.setMessage("Are you sure you want to logout?");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog = new ProgressDialog(ReceivedGoodsActivity.this);
                        progressDialog.setMessage("logging out....");
                        progressDialog.show();

                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        progressDialog.cancel();
                        startActivity(i);
                        finish();

                    }
                });

                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}


