package com.bawp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;



public class UserDashBoardActivity extends AppCompatActivity {
    // Button cd_order_goods,cd_receive_goods,cd_not_received,btn_viewmyorders;
    CardView cd_new_order,cd_order_history,cd_notifications,edit_myprofile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdashboard);

        getSupportActionBar().setTitle("User Dashboard");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
