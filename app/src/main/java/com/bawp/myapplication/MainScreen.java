package com.bawp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    private Button User_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        User_Button = findViewById(R.id.User_Login);

        User_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstScreen();
            }
        });


    }

    public void FirstScreen(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
