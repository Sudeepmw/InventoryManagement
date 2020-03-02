package com.inventorymanagementsystem.JavaClasses;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.inventorymanagementsystem.R;

public class UserDashBoardActivity extends AppCompatActivity {
   // Button cd_order_goods,cd_receive_goods,cd_not_received,btn_viewmyorders;
    CardView cd_new_order,cd_order_history,cd_notifications,edit_myprofile;
    TextView txt;
    String st;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdashboard);
        txt = (TextView) findViewById(R.id.hitext);


        getSupportActionBar().setTitle("  User Dashboard");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            st = getIntent().getExtras().getString("Welcome");
            txt.setText("Welcome "+st +"!");
        }else{
            txt.setText("'Thank You for your Order'");

        }
        cd_new_order=(CardView) findViewById(R.id.cd_new_order);
        cd_new_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this,NewOrdersActivity.class);

                startActivity(intent);
            }
        });

        cd_order_history=(CardView)findViewById(R.id.cd_order_history);
        cd_order_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this,ViewAllMyOrdersActivity.class);
                startActivity(intent);

            }
        });

        cd_notifications=(CardView)findViewById(R.id.cd_notifications);
        cd_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this,ReceivedGoodsActivity.class);
                startActivity(intent);

            }
        });

        edit_myprofile=(CardView)findViewById(R.id.edit_myprofile);
        edit_myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this,UserEditProfileActivity.class);
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
                // Toast.makeText(getApplicationContext(),"logout Selected",Toast.LENGTH_LONG).show();

                final AlertDialog.Builder builder = new AlertDialog.Builder(UserDashBoardActivity.this);
                builder.setMessage("Are you sure you want to logout?");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog = new ProgressDialog(UserDashBoardActivity.this);
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
