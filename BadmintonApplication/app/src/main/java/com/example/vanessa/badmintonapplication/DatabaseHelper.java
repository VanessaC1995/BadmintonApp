package com.example.vanessa.badmintonapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Vanessa.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context con;
    public DatabaseHelper(Context context) {
        super(context, "MyDatabase", null, 1);
        con=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable="create table tblProgress(xValues INTEGER, yValues INTEGER);";
        db.execSQL(createTable);
        Toast.makeText(con, "Table Created", Toast.LENGTH_SHORT).show();

        String createTable2="create table tblRanks(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, newimage blob);";
        db.execSQL(createTable2);

        String createTable3="create table tblReg(ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, username TEXT, password TEXT);";
        db.execSQL(createTable3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists tblRanks");
        onCreate(db);

        db.execSQL("Drop table if exists tblReg");
        onCreate(db);
    }

    public void insertData(int x, int y)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("xValues", x);
        contentValues.put("yValues", y);

        db.insert("tblProgress", null,contentValues);
        Toast.makeText(con, "Data Inserted", Toast.LENGTH_SHORT).show();

    }

    public boolean addData(String name, byte[] img)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("newimage", img);
        long result = db.insert("tblRanks", null, contentValues);
        if(result== -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from tblRanks";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from tblRanks" + "Where Name" + "='" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean insert(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long ins = db.insert("tblReg", null, contentValues);
        if(ins == -1) return false;
        else return true;
    }
    //checking if username exists
    public Boolean chkusername(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from tblReg where username =?", new String[]{username});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    //checking the username and password;
    public Boolean userpass(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tblReg where username =? and password =?", new String[]{username, password});
        if (cursor.getCount()>0) return true;
        else return false;
    }


}
