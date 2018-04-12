package com.example.vanessa.badmintonapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ImageList extends AppCompatActivity {
    DatabaseHelper myDatabaseHelper;
    public ListView listView;
    ArrayList<MyData> listData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        listView = (ListView) findViewById(R.id.myList);
        myDatabaseHelper = new DatabaseHelper(this);
        populateView();
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_activity, listData);
        listView.setAdapter(adapter);
    }

    private void populateView() {
        Cursor data = myDatabaseHelper.getData();
        while(data.moveToNext())
        {
            String name = data.getString(1);
            byte[] image = data.getBlob(2);
            listData.add(new MyData(name, image));
        }
    }

    public class CustomAdapter extends BaseAdapter
    {
        private Context context;
        private int layout;
        ArrayList<MyData> textList;

        public CustomAdapter(Context context, int layout, ArrayList<MyData> textList)
        {
            this.context = context;
            this.layout = layout;
            this.textList = textList;
        }

        @Override
        public int getCount()
        {
            return textList.size();
        }

        @Override
        public Object getItem(int position)
        {
            return textList.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        private class ViewHolder
        {
            ImageView imageView1;
            TextView textName;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            View row = view;
            ViewHolder holder;

            if(row == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(layout, null);
                holder = new ViewHolder();
                holder.textName =(TextView) row.findViewById(R.id.tbCustom);
                holder.imageView1 = (ImageView) row.findViewById(R.id.imgView);
                row.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) row.getTag();
            }

            final MyData ranks = textList.get(position);
            holder.textName.setText(ranks.GetName());
            byte[] rankImage = ranks.GetImage();
            final Bitmap bitmap = BitmapFactory.decodeByteArray(rankImage, 0, rankImage.length);
            holder.imageView1.setImageBitmap(bitmap);
            row.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                 Cursor data = myDatabaseHelper.getItemID(ranks.GetName());
                    int itemID = -1;
                    String rankWeek = "";
                    byte[] rankImage = null;

                    while(data.moveToNext())
                    {
                     itemID = data.getInt(0);
                        rankWeek = data.getString(1);
                        rankImage = data.getBlob(2);
                        Intent editIntent= new Intent(ImageList.this, EditData.class);
                        editIntent.putExtra("ID", itemID);
                        editIntent.putExtra("name", ranks.GetName());
                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                        editIntent.putExtra("byteArray", bs.toByteArray());
                        startActivity(editIntent);
                    }
                    if (itemID > -1)
                    {
                        ToastMessage("On item click: the ID is " + itemID + " " + rankWeek + " " + rankImage);
                    }
                    else
                    {
                        ToastMessage("No Data");
                    }
                }
            });
            return row;
        }
    }
    private void ToastMessage(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
