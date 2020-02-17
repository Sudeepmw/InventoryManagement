package com.bawp.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminDashBoardActivity extends AppCompatActivity {
    Button cd_view_orders,cd_pending_orders,cd_develer_orders,btn_add_items,btn_update_items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);
        getSupportActionBar().setTitle("Admin DashBoard");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        cd_view_orders=(Button)findViewById(R.id.cd_view_orders);
        cd_pending_orders=(Button)findViewById(R.id.cd_pending_orders);
        cd_develer_orders=(Button)findViewById(R.id.cd_develer_orders);
        btn_add_items=(Button)findViewById(R.id.btn_add_items);
        btn_update_items=(Button)findViewById(R.id.btn_update_items);

        Typeface fontstyle=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        cd_view_orders.setTypeface(fontstyle);
        cd_pending_orders.setTypeface(fontstyle);
        cd_develer_orders.setTypeface(fontstyle);
        btn_add_items.setTypeface(fontstyle);
        btn_update_items.setTypeface(fontstyle);




        cd_view_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,ViewOrdersActivity.class);
                startActivity(intent);
            }
        });


        cd_pending_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, OutOfStockItemsActivity.class);
                startActivity(intent);

            }
        });


        cd_develer_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, DeliveredItemsActivity.class);
                startActivity(intent);

            }
        });



        btn_add_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,AddItemsActivity.class);
                startActivity(intent);

            }
        });


        btn_update_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,UpdateItemsActivity.class);
                startActivity(intent);

            }
        });
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
