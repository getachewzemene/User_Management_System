package com.getch.user_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class user_page extends AppCompatActivity {
   private TextView welcomeusertv,userinfodisplay;
   private Button logout,viewuser;
   private  UserSessionPreference userSessionPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);
        welcomeusertv=findViewById(R.id.id_welcomeuser);
        userinfodisplay=findViewById(R.id.Id_userinfo_display);
        logout=findViewById(R.id.id_logout);
        viewuser=findViewById(R.id.id_viewuserlistbtn);
        Intent intent=getIntent();
       SharedPreferences sharedPreferences=this.getSharedPreferences("userApp", MODE_PRIVATE);
       String username=sharedPreferences.getString("username", null);
       String password=sharedPreferences.getString("password", null);
        userinfodisplay.setText("Name:"+username);
        userSessionPreference=new UserSessionPreference(this);
        if(!userSessionPreference.userLoggedIn()){
            logout();
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  logout();
            }
        });
        viewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),view_users.class));
            }
        });
    }

    private void logout() {
        userSessionPreference.setUserLoggedIn(false);
        finish();
        SharedPreferences preferences=getApplicationContext().getSharedPreferences("userApp", MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.remove("username");
        editor.remove("password");
        editor.commit();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        userinfodisplay.setText("");
    }
}
