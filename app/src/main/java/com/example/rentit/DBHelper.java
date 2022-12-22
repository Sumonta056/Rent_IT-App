package com.example.rentit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    // database file
    public static final String DBNAME = "login.db" ;
    // database file

    // open file
    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }
    // open file

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table users(username TEXT primary key , password TEXT , email TEXT, address TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");

    }

    // Registration Part

    public Boolean insertData(String username, String pass, String email,String address) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username" , username);
        values.put("password",pass);
        values.put("email",email);
        values.put("address",address);


        long result = db.insert("users",null,values);
        if(result == -1) return false ;
        else return true;
    }
    // Registration Part

    // checks duplicate username in registration
    public Boolean checkusername(String username)
    {
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});


        if(cursor.getCount() > 0){

            return true;
        }
        else return false;
    }
    // checks duplicate username in registration


    // login part check username passs and database
    public Boolean checkusernamepassword(String username,String pass)
    {
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?",new String[]{username,pass});

        if(cursor.getCount() > 0){

            return true;
        }
        else return false;
    }

    // login part check username pass and database



}
