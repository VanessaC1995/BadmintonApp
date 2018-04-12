package com.example.vanessa.badmintonapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UlsterLeague extends AppCompatActivity {
    ImageButton btnBadminton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulster_league);

        btnBadminton=(ImageButton)findViewById(R.id.btnBadminton);
        btnBadminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ulsterbadmintonleague.btck.co.uk/"));
                startActivity(intent);
            }//view ulster league website
        });
    }
}
