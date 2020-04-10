package com.KJS.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb = new DatabaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
