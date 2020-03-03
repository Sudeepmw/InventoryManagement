package com.inventorymanagementsystem.JavaClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inventorymanagementsystem.R;
import com.inventorymanagementsystem.Utils;
import com.inventorymanagementsystem.adapterView.DeliveredAdapter;

import java.util.ArrayList;
import java.util.List;

public class DeveliredActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<Delivered> a1;
    SharedPreferences sharedPreferences;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivered);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");

        getSupportActionBar().setTitle("Delivered Orders");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);


        a1= new ArrayList<>();
        a1.add(new Delivered("Grliahdad","223","Abcdefg","9999999999"));
        a1.add(new Delivered("Grliahdad","223","hijklmno","0000000000"));
        a1.add(new Delivered("Grliahdad","223","Abcdefg","999999999"));
        a1.add(new Delivered("Grliahdad","223","hijklmno","9999999999"));
        a1.add(new Delivered("Grliahdad","223","Abcdefg","9999999999"));
        a1.add(new Delivered("Grliahdad","223","Abcdefg","0000000000"));
        a1.add(new Delivered("Grliahdad","223","Abcdefg","9999999999"));
        a1.add(new Delivered("Grliahdad","223","hijklmno","0000000000"));


        list_view.setAdapter(new DeliveredAdapter(a1, DeveliredActivity.this));


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
