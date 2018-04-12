package com.example.vanessa.badmintonapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu2 extends AppCompatActivity {
    Button btnRanks, btnInfo, btnUlster, btnCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu2);

        btnRanks=(Button)findViewById(R.id.btnRanks);
        btnRanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu2.this, ImageList.class);
                startActivity(intent);

                }
            }); //access rankings for members

        btnInfo=(Button)findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu2.this,Info.class );
                startActivity(intent);
            }
        }); //access info page

        btnUlster=(Button)findViewById(R.id.btnUlster);
        btnUlster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu2.this,UlsterLeague.class );
                startActivity(intent);
            }
        }); //access ulster league info page

        btnCalendar=(Button)findViewById(R.id.btnCalender);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu2.this, Calendar.class);
                startActivity(intent);
            }
        });//access calendar
    }
}
