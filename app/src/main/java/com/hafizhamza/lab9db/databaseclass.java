package com.hafizhamza.lab9db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class databaseclass extends SQLiteOpenHelper {

    public databaseclass(Context context) {
        super(context, "registration.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
sqLiteDatabase.execSQL("create table registration(id integer primary key unique ,name varchar(50),fathername varchar(50),address varchar(50),state varchar(50),city varchar(50),dob varchar(50),email varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void Registration(String name,String fathername,String address,String state,String city,String dob,String email)
    {
        SQLiteDatabase dtabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        //Table Column
        contentValues.put("name",name);
        contentValues.put("fathername",fathername);
        contentValues.put("address",address);
        contentValues.put("state",state);
        contentValues.put("city",city);
        contentValues.put("dob",dob);
        contentValues.put("email",email);
        //Table Name
        dtabase.insert("registration",null,contentValues);
    }
    //Update Data Method
    public void Update(int id,String name,String fathername,String address,String state,String city,String dob,String email)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("fathername",fathername);
        contentValues.put("address",address);
        contentValues.put("state",state);
        contentValues.put("city",city);
        contentValues.put("dob",dob);
        contentValues.put("email",email);
        database.update("registration",contentValues,"id=?",new String[]{Integer.toString(id)});
    }
    //Delete Data Method
    public void Delete(int id)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        database.delete("registration","id=?",new String[]{Integer.toString(id)});
    }


/////////////////Select Data Method///////////////////////////////
    public ArrayList<String> getSpecificRecord(int id)
    {

        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from registration where id='"+id+"'", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex("name")));
            array_list.add(res.getString(res.getColumnIndex("fathername")));
            array_list.add(res.getString(res.getColumnIndex("city")));
            array_list.add(res.getString(res.getColumnIndex("state")));
            array_list.add(res.getString(res.getColumnIndex("email")));
            array_list.add(res.getString(res.getColumnIndex("state")));
            array_list.add(res.getString(res.getColumnIndex("dob")));


            res.moveToNext();
        }
        return array_list;

    }

    public ArrayList<String> getAllRecord()
    {

        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from registration", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex("name")));
            array_list.add(res.getString(res.getColumnIndex("fathername")));
            array_list.add(res.getString(res.getColumnIndex("city")));
            array_list.add(res.getString(res.getColumnIndex("state")));
            array_list.add(res.getString(res.getColumnIndex("email")));
            array_list.add(res.getString(res.getColumnIndex("state")));
            array_list.add(res.getString(res.getColumnIndex("dob")));


            res.moveToNext();
        }
        return array_list;

    }
}
