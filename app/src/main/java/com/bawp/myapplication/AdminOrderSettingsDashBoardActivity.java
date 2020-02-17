package com.bawp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminOrderSettingsDashBoardActivity extends AppCompatActivity {
    Button btn_orders,btn_settings;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_settings_dashboard);

        getSupportActionBar().setTitle("Admin Dashboard");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btn_orders=(Button)findViewById(R.id.btn_orders);
        btn_settings=(Button)findViewById(R.id.btn_settings);

        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminOrderSettingsDashBoardActivity.this,AdminDashBoardActivity.class);
                startActivity(intent);
            }
        });

        //Intent Code to be written
        // By Sudee
    }
    @Override
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
