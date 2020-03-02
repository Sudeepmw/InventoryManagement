package com.inventorymanagementsystem.JavaClasses;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inventorymanagementsystem.R;

public class OrderConfirmationSplash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slashscreen);
        getSupportActionBar().setTitle("Order Confirmation");

        final int ScreenDisplay = 1500;

        Thread t1=new Thread(){
            int wait1=0;
            public void run(){
                try{
                    while(wait1<=ScreenDisplay )
                    {
                        sleep(100);
                        wait1+=100;
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    Intent intent= new Intent(OrderConfirmationSplash.this, UserDashBoardActivity.class);

                    startActivity(intent);
                    finish();

                }
            }
        };
        t1.start();
    }
}
