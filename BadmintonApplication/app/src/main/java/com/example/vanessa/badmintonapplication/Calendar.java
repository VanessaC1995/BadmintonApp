package com.example.vanessa.badmintonapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Calendar extends AppCompatActivity {

    CompactCalendarView calendarView;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        final ActionBar actBar = getSupportActionBar();
        actBar.setDisplayHomeAsUpEnabled(false);
        actBar.setTitle(null);

        //assigning views from layout folder
        calendarView = (CompactCalendarView) findViewById(R.id.compactCalendarView);
        calendarView.setUseThreeLetterAbbreviation(true);

        //Set an event for Ulster league
        Event ulsterLeagueCupFinal = new Event(Color.BLUE, 1525114800000L, "Ulster League Cup Final");
        //timestamp 1525114800000L to set time and date of match
        //epoch converter used to get timestamp
        calendarView.addEvent(ulsterLeagueCupFinal);

        Event badmintonTrainingStart = new Event(Color.BLUE, 1537894800000L, "First Training Session");
        calendarView.addEvent(badmintonTrainingStart);

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if(dateClicked.toString().compareTo("Mon April 30 00:00:00: AST 2018") == 0)
                {
                    Toast.makeText(context, "Ulster League Cup Final", Toast.LENGTH_SHORT).show();
                }
                else if (dateClicked.toString().compareTo("Tue May 08 00:00:00 AST 2018" ) == 0)
                {
                    Toast.makeText(context, "Last Training Session of the year", Toast.LENGTH_SHORT).show();
                }
                else if(dateClicked.toString().compareTo("Tue Sept 25 00:00:00 AST 2018") == 0)
                {
                    Toast.makeText(context, "First Training Session of new academic year", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(context, "No events planned today!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actBar.setTitle(dateFormat.format(firstDayOfNewMonth));
            }
        });

    }
}
