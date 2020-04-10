package com.KJS.basicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;

    private EditText name;
    private EditText password;
    private Button login;
    private Button forgot;
    private TextView signup;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.id_name);
        password = (EditText)findViewById(R.id.id_password);
        login = (Button)findViewById(R.id.btn_login);
        signup = (TextView)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_third.class);
                startActivity(intent);
            }
        });
        mydb = new DatabaseHelper(getApplicationContext());
        login_to_app();
    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        this.setTheme(R.style.exit_theme);
        builder.setMessage("Do you want to exit");
        builder.setCancelable(true);
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void login_to_app(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((name.getText().toString().equals("")) || (password.getText().toString().equals(""))){
                    Toast.makeText(getApplicationContext(), "Fields cannot be set empty", Toast.LENGTH_LONG).show();

                }
                else{
                    Cursor reply = mydb.login_data(name.getText().toString(), password.getText().toString());
                    Log.d("reply", "ans = " + reply.getCount());
                    if (reply.getCount() == 1){
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }


}
