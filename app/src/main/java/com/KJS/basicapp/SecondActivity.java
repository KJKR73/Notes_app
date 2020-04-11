package com.KJS.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class SecondActivity extends AppCompatActivity implements Serializable {
    private EditText user_note;
    private Button save, clear, logout;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mydb = new DatabaseHelper(this);
        String user_name = (String)getIntent().getSerializableExtra("user_name");
        user_note = (EditText)findViewById(R.id.text_area);
        save = (Button)findViewById(R.id.save_text);
        clear = (Button)findViewById(R.id.clear_text);
        logout = (Button)findViewById(R.id.logout);
        Cursor res = mydb.text_query(user_name);
        while(res.moveToNext()){
            user_note.setText(res.getString(0));
        }
        save_text_db(user_name);
        clear_text_not_commit();
        logout();
    }

    public void save_text_db(final String user_name){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean res = mydb.save_text(user_name, user_note.getText().toString());
                if (res){
                    Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void clear_text_not_commit(){
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_note.setText("");
            }
        });
    }
    public void logout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_page = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(main_page);
            }
        });
    }
}
