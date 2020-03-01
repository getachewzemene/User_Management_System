package com.getch.user_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class user_registration extends AppCompatActivity{
    private TextView headertv;
    private EditText fulname,username,email,password,phone;
    private RadioGroup genderGroup;
    private RadioButton maleradiobutton,femaleRadioButten;
    private Button registeruser;
    private String user_fulname,username2,user_email,user_password,user_phone,user_gender;
    UserInfoDatabas userInfodb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration);
        headertv=findViewById(R.id.id_rgtv1);
        fulname=findViewById(R.id.id_fulname);
        username=findViewById(R.id.id_username);
        email=findViewById(R.id.id_email);
        password=findViewById(R.id.id_password);
        phone=findViewById(R.id.id_mobile);
        genderGroup=findViewById(R.id.radiogendergroup);
        maleradiobutton=findViewById(R.id.radioButtonMale);
        femaleRadioButten=findViewById(R.id.radioButtonfemale);
        registeruser=findViewById(R.id.id_regbutten);
        userInfodb=new UserInfoDatabas(this);
    }
    public void registerUser(View view) {
        userInfodb=new UserInfoDatabas(this);
        user_fulname=fulname.getText().toString().trim();
        username2=username.getText().toString().trim();
        user_email=email.getText().toString().trim();
        user_password=password.getText().toString().trim();
        user_phone=phone.getText().toString().trim();
        user_gender=((RadioButton)findViewById(genderGroup.getCheckedRadioButtonId())).getText().toString();
        if(user_fulname.isEmpty())
            fulname.setError("User FulName Required!");
        else if(username2.isEmpty())
            username.setError("Username Required!");
       else  if(user_email.isEmpty())
            email.setError("User email Required!");
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(user_email).matches())
            email.setError("please enter valid Email ending like @gmail.com or @yahoomail.com");
        else if(user_password.isEmpty())
            password.setError("User password Required!");
       else  if(user_phone.isEmpty())
            phone.setError("User phone Required!");
        else {
            userInfodb.addUser(user_fulname, username2, user_email, user_password, user_phone, user_gender);
            fulname.setText("");
            username.setText("");
            email.setText("");
            password.setText("");
            phone.setText("");
            Intent intent= new Intent(getApplicationContext(),user_page.class);
            startActivity(intent);
        }
    }
}
