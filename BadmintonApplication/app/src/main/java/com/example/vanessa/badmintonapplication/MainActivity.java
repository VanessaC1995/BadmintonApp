package com.example.vanessa.badmintonapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLog, btnReg;
    EditText ed1, ed2;
    DatabaseHelper db;
    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        btnLog = (Button) findViewById(R.id.btnLogin);
        ed1 = (EditText) findViewById(R.id.tbUsername);
        ed2 = (EditText) findViewById(R.id.tbConfirm);

        btnReg = (Button) findViewById(R.id.btnReg);
        tx1 = (TextView) findViewById(R.id.tvNum);
        tx1.setVisibility(View.GONE);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = ed1.getText().toString();
                String password = ed2.getText().toString();
                Boolean checkuserpass = db.userpass(username, password);
                if(checkuserpass==true)
                    Toast.makeText(getApplicationContext(),"Successful Login", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();


                if (ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    //user brought to main menu if credentials are correct
                    Intent in = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(in);

                } else
                    {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    //credentials wrong and so error message appears
                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));
                    //count of how many times credentials are entered wrong
                    if (counter == 0)
                        {
                         btnLog.setEnabled(false);
                        }

                     }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Register.class);
                startActivity(in);
            }
        });
        /*b1 = (Button) findViewById(R.id.btnLogin);
        ed1 = (EditText) findViewById(R.id.tbUsername);
        ed2 = (EditText) findViewById(R.id.tbPassword);

        b2 = (Button) findViewById(R.id.btnReg);
        tx1 = (TextView) findViewById(R.id.tvNum);
        tx1.setVisibility(View.GONE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    //bringing user to main menu
                    Intent in = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(in);

                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    //wrong username or password enetered

                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }

                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
*/

    }

    //public void loginScreen (View view){
    //  Intent in = new Intent(this, MainMenu.class);
    //startActivity(in);
    //this.finish();
    //}
}

