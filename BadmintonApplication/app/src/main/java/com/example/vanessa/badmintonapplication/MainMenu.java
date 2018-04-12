package com.example.vanessa.badmintonapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    Button btnProg, btnInfo, btnUlster, btnRanks, btnCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnProg=(Button)findViewById(R.id.btnProgress);
        btnProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,coachProgress.class );
                startActivity(intent);
            }
        }); //access progress page for coach

        btnInfo=(Button)findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,Info.class );
                startActivity(intent);
            }
        }); //access info page

        btnUlster=(Button)findViewById(R.id.btnUlster);
        btnUlster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,UlsterLeague.class );
                startActivity(intent);
            }
        }); //access ulster league info

        btnRanks=(Button)findViewById(R.id.btnRanks);
        btnRanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,Ranks.class );
                startActivity(intent);
            }
        }); //access rankings page for coach

        btnCalendar=(Button)findViewById(R.id.btnCalender);
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Calendar.class);
                startActivity(intent);
            }
        });//access calendar

    }
}
