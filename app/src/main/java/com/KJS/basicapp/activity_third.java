package com.KJS.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class activity_third extends AppCompatActivity {
    DatabaseHelper mydb;
    private EditText username, password, confirm_password;
    private Button sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        username  = (EditText)findViewById(R.id.username);
        password  = (EditText)findViewById(R.id.pass);
        confirm_password  = (EditText)findViewById(R.id.conf_pass);
        sign_up  = (Button) findViewById(R.id.sign_up_user);
        mydb = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        RelativeLayout mylayout = new RelativeLayout(this);
    }

    public void add_data(){
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.name_query(username.getText().toString());
                if (res.getCount() == 0){
                    if (password.getText().toString() == confirm_password.getText().toString()){
                        boolean reply = mydb.insertData(username.getText().toString(), password.getText().toString());
                    }
                }
            }
        });
    }
}
