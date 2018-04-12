package com.example.vanessa.badmintonapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Vanessa.
 */

public class EditData extends AppCompatActivity
{
    private TextView editText;
    ImageView editImage;
    int SelectID;
    String selectedName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        editText = (TextView) findViewById(R.id.editName);
        editImage = (ImageView) findViewById(R.id.editImage);
        Intent intent = getIntent();
        SelectID = intent.getIntExtra("ID", -1);
        selectedName = intent.getStringExtra("name");
        if (getIntent().hasExtra("byteArray"))
        {
            Bitmap bitmap = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"), 0,getIntent().getByteArrayExtra("byteArray").length);
            editImage.setImageBitmap(bitmap);
        }
        editText.setText(selectedName);
    }
}
