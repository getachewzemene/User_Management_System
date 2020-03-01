package com.getch.user_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class About_Developer extends AppCompatActivity {
         ImageView imageView;
         TextView about,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__developer);
        imageView=findViewById(R.id.id_developer_image);
        about=findViewById(R.id.id_aboutdevtv);
        email=findViewById(R.id.id_email);
    }
}
