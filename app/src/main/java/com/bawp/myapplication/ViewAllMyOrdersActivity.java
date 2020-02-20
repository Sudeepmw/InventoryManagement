package com.bawp.myapplication;


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

public class ViewAllMyOrdersActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;
    String uname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       


        uname = sharedPreferences.getString("user_name", "");

        getSupportActionBar().setTitle("In Progress Orders");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view = (ListView) findViewById(R.id.list_view);



    }
}