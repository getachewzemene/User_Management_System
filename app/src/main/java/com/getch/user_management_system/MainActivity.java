package com.getch.user_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView welcometv, registertv;
    private EditText username, password;
    private Button login, register;
    private UserInfoDatabas userInfoDatabas;
    SQLiteDatabase userSqlDb;
    private UserSessionPreference loginSessionPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcometv = findViewById(R.id.id_welcometv);
        username = findViewById(R.id.id_username);
        password = findViewById(R.id.id_password);
        registertv = findViewById(R.id.id_tv2);
        login = findViewById(R.id.id_btn1);
        register = findViewById(R.id.registerbtn);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        loginSessionPreference=new UserSessionPreference(this);
        if(loginSessionPreference.userLoggedIn()){
            startActivity(new Intent(getApplicationContext(),user_page.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn1:
                String getUsername = username.getText().toString().trim();
                String getPassword = password.getText().toString().trim();
                userInfoDatabas = new UserInfoDatabas(this);
                if (getUsername.isEmpty()){
                    username.setError("please enter username!");
                    username.requestFocus();
                } else if(getPassword.isEmpty()){
                    password.setError("please enter password!");
                }
                Boolean getResult=userInfoDatabas.checkUserLogin(getUsername, getPassword);
                if(getResult==true){
                    SharedPreferences sharedPreferences=this.getSharedPreferences("userApp",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("username", getUsername);
                    editor.putString("password",getPassword);
                    editor.commit();
                    loginSessionPreference.setUserLoggedIn(true);
                            Intent loginIntent = new Intent(new Intent(getApplicationContext(), user_page.class));
                            startActivity(loginIntent);
                            finish();
                        }
                else {
                                username.setError("Invalid  user name and password try Again");
                                username.setText("");
                                password.setText("");
                            }
                break;
            case R.id.registerbtn:
                startActivity(new Intent(getApplicationContext(), user_registration.class));
                finish();
                break;
        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.about:
                   startActivity(new Intent(getApplicationContext(),About_Developer.class));
                   finish();
                break;
            case R.id.id_share:
                 Intent shareIntent=new Intent(Intent.ACTION_SEND);
                 shareIntent.setType("text/plain");
                 shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share This App");
                 shareIntent.putExtra(Intent.EXTRA_TEXT, "User_Management System");
                 startActivity(Intent.createChooser(shareIntent, "Share Via"));
                 finish();
                break;
            case R.id.id_exit:
                      finish();
                break;
        }
        return true;
    }
}
