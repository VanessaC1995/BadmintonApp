package com.example.vanessa.badmintonapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Ranks extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    DatabaseHelper myDatabaseHelper;
    Button btnAdd, btnChoose;
    EditText tbAddText;
    ImageView myImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranks);
        //set id's
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        myImg = (ImageView) findViewById(R.id.imgRanks);
        tbAddText = (EditText) findViewById(R.id.tbAddName);
        myDatabaseHelper = new DatabaseHelper(this);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions
                        (Ranks.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
            }
        }); //access permissions for gallery

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String NewEntry = tbAddText.getText().toString();
                byte[] NewEntryImg = imageViewTobyte(myImg);
                if (tbAddText.length() != 0) {
                    AddData(NewEntry, NewEntryImg);

                    Intent intent = new Intent(Ranks.this,ImageList.class);
                    startActivity(intent);
                }//access ranks
                else
                {
                    Toast("Field Empty");
                }
            }

            private byte[] imageViewTobyte(ImageView image) {
                Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode == REQUEST_CODE_GALLERY)
        {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "You don't have permissions to access this file", Toast.LENGTH_SHORT).show();
            } //permission for gallery
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Uri uri = data.getData();
        try
        {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            myImg.setImageBitmap(bitmap);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
            private void AddData(String newEntry, byte[] newEntryImg) {
                boolean insertData = myDatabaseHelper.addData(newEntry, newEntryImg);
                if(insertData)
                {
                    Toast("Data Inserted");
                }//data inserted into database
                else
                {
                    Toast("Data not inserted");
                }
            }
            private void Toast(String s)
            {
                Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
            }
    }

