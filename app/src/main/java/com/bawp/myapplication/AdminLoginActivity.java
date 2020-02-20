package com.bawp.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;




public class AdminLoginActivity extends AppCompatActivity {
    TextView tv_username,tv_password,tv_forgetpwd,tv_newuser,tv_signup;
    EditText et_USERNAME,et_PWD;
    Button btn_add;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        //getSupportActionBar().hide();

        getSupportActionBar().setTitle("Admin Login");





        et_USERNAME=(EditText) findViewById(R.id.et_USERNAME);
        et_PWD=(EditText)findViewById(R.id.et_PWD);

        btn_add=(Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_USERNAME.getText().length()==0)
                {
                    et_USERNAME.setError("Please Enter Name");
                }
                 if (et_PWD.getText().length()==0)
                {
                    et_PWD.setError("Please Enter Password");
                }




            }
        });


    }




    }


