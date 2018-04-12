package com.example.vanessa.badmintonapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Info extends AppCompatActivity {
    ImageButton btnFacebook, btnTwitter, btnUUSU;
    //information screen to show info about the club and to access clubs social media pages
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btnFacebook=(ImageButton)findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/uujbadminton/"));
                startActivity(intent);
            }
     });

        btnTwitter=(ImageButton)findViewById(R.id.btnTwitter);
        btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/uususport"));
                startActivity(intent);
            }
        });

        btnUUSU=(ImageButton)findViewById(R.id.btnUUSU);
        btnUUSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://uususport.org/clubs/badminton-club/"));
                startActivity(intent);
            }
        });

    }
}

