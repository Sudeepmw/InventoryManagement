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
import com.inventorymanagementsystem.adapterView.StartNewOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartNewOrderActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<AddItems> a1;
    public static int[] cnt;
    SharedPreferences sharedPreferences;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_goods);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");

        getSupportActionBar().setTitle("Start New Order");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        list_view=(ListView)findViewById(R.id.list_view);


        a1= new ArrayList<>();



        serverData();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(StartNewOrderActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        InventoryEndURL service = RetrofitInstance.getRetrofitInstance().create(InventoryEndURL.class);
        Call<List<AddItems>> call = service.getiteams();
        call.enqueue(new Callback<List<AddItems>>() {
            @Override
            public void onResponse(Call<List<AddItems>> call, Response<List<AddItems>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(StartNewOrderActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    list_view.setAdapter(new StartNewOrderAdapter(a1, StartNewOrderActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<AddItems>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(StartNewOrderActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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
                // Toast.makeText(getApplicationContext(),"logout Selected",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}


