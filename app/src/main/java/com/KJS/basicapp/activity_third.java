package com.KJS.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_third extends AppCompatActivity {
    DatabaseHelper mydb;
    private EditText username, password, confirm_password;
    private Button sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        username  = (EditText)findViewById(R.id.username);
        password  = (EditText)findViewById(R.id.pass);
        confirm_password  = (EditText)findViewById(R.id.conf_pass);
        sign_up  = (Button)findViewById(R.id.user_reg);
        mydb = new DatabaseHelper(getApplicationContext());
        add_data();
    }

    public void add_data(){
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.name_query(username.getText().toString());
                if (res.getCount() == 0) {
                    if (password.getText().toString().equals(confirm_password.getText().toString())) {
                        boolean reply = mydb.insertData(username.getText().toString(), password.getText().toString());
                        if (reply) {
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                            Intent redirect = new Intent(activity_third.this, MainActivity.class);
                            startActivity(redirect);
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Error", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "User_name already exits", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
