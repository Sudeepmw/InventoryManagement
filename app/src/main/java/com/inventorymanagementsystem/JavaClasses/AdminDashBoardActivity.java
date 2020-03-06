package com.inventorymanagementsystem.JavaClasses;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.inventorymanagementsystem.R;

public class AdminDashBoardActivity extends AppCompatActivity {
    //Button cd_view_orders,cd_pending_orders,cd_develer_orders,btn_add_items,btn_update_items;
    CardView cd_delivered_orders,cd_cancelled,cd_additem,cd_updateitem;
    CardView cd_view_orders;
    Button btn_logout;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);
        getSupportActionBar().setTitle("Admin DashBoard");

        btn_logout=(Button)findViewById(R.id.btn_logout);
        cd_view_orders=(CardView)findViewById(R.id.cd_view_orders);
        cd_delivered_orders=(CardView) findViewById(R.id.cd_delivered_orders);
        cd_cancelled=(CardView)findViewById(R.id.cd_cancelled);
        cd_additem=(CardView)findViewById(R.id.cd_additem);
        cd_updateitem=(CardView)findViewById(R.id.cd_updateitem);

        cd_view_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, ViewOrdersActivity.class);
                startActivity(intent);
            }
        });


        cd_delivered_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, DeliveredItemsAdminActivity.class);
                startActivity(intent);

            }
        });


        cd_cancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, OutOfStockItemsAdminActivity.class);
                startActivity(intent);

            }
        });



        cd_additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,AddItemsActivity.class);
                startActivity(intent);

            }
        });


        cd_updateitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this,UpdateItemsActivity.class);
                startActivity(intent);

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

                final AlertDialog.Builder builder = new AlertDialog.Builder(AdminDashBoardActivity.this);
                builder.setMessage("Are you sure you want to logout?");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog = new ProgressDialog(AdminDashBoardActivity.this);
                        progressDialog.setMessage("logging out....");
                        progressDialog.show();

                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
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




            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void onBackPressed(){
        //  Toast.makeText(UserDashBoardActivity.this,"  ",Toast.LENGTH_LONG).show();

    }
}
