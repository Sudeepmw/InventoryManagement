package com.inventorymanagementsystem.JavaClasses;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.inventorymanagementsystem.adapterView.DeliveredItemsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutOfStockItemsAdminActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<DeliveredItems> a1;
    SharedPreferences sharedPreferences;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivered);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");

        getSupportActionBar().setTitle("Cancelled Orders");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);

        a1= new ArrayList<>();
        serverData();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(OutOfStockItemsAdminActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<List<DeliveredItems>> call = service.getAdminOutOfStockItems();
        call.enqueue(new Callback<List<DeliveredItems>>() {
            @Override
            public void onResponse(Call<List<DeliveredItems>> call, Response<List<DeliveredItems>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(OutOfStockItemsAdminActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    list_view.setAdapter(new DeliveredItemsAdapter(a1, OutOfStockItemsAdminActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<DeliveredItems>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(OutOfStockItemsAdminActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
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
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}