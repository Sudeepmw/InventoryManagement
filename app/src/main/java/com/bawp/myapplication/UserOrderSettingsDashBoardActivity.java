package com.bawp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserOrderSettingsDashBoardActivity extends AppCompatActivity {
    Button btn_orders,btn_settings;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order_settings_dashboard);

        getSupportActionBar().setTitle("User Dashboard");


        btn_orders=(Button)findViewById(R.id.btn_orders);
        btn_settings=(Button)findViewById(R.id.btn_settings);

        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserOrderSettingsDashBoardActivity.this,UserDashBoardActivity.class);
                startActivity(intent);
            }
        });

      //Intent Code for other screen should be added
        //Arun Namai
    }

}
