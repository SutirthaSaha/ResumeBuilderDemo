package com.example.dell.resumebuilderdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 14-Jul-17.
 */

public class ResumeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="resumes";
    public ResumeDatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(id integer primary key autoincrement,name text,email text,phone text,address text,maritial text,gender text,city text,state text,country text,objective text,education text,work text,skills text,achievements text,hobbies text,languages text)");
    }

    public Cursor insertData(String name,String email,String phone,String address,String maritial,String gender,String city,String state,String country,String objective,String education,String work,String skills,String achievements,String hobbies,String languages,SQLiteDatabase db){
        Cursor cursor=db.rawQuery("insert into users(name,email,phone,address,maritial,gender,city,state,country,objective,education,work,skills,achievements,hobbies,languages) values ('"+name+"','"+email+"','"+phone+"','"+address+"','"+maritial+"','"+gender+"','"+city+"','"+state+"','"+country+"','"+objective+"','"+education+"','"+work+"','"+skills+"','"+achievements+"','"+hobbies+"','"+languages+"')",null);
        return cursor;
    }

    public Cursor viewData(String name,String email,SQLiteDatabase db){
        Cursor cursor=db.rawQuery("select * from users where name='"+name+"' and email='"+email+"'",null);
        return cursor;
    }

    public Cursor viewNames(SQLiteDatabase db){
        Cursor cursor=db.rawQuery("select * from users",null);
        return cursor;
    }
    public Cursor searchEmail(String email,SQLiteDatabase db){
        Cursor cursor=db.rawQuery("select * from users where email='"+email+"'",null);
        return cursor;
    }
    public Cursor updateData(String name,String email,String phone,String address,String maritial,String gender,String city,String state,String country,String objective,String education,String work,String skills,String achievements,String hobbies,String languages,SQLiteDatabase db){
        Cursor cursor=db.rawQuery("update users set name='"+name+"',phone='"+phone+"',address='"+address+"',maritial='"+maritial+"',gender='"+gender+"',city='"+city+"',state='"+state+"',country='"+country+"',objective='"+objective+"',education='"+education+"',work='"+work+"',skills='"+skills+"',achievements='"+achievements+"',hobbies='"+hobbies+"',languages='"+languages+"' where email='"+email+"'",null);
        return cursor;
    }
    public int deleteData(String name,SQLiteDatabase db){
        String where="name=?";
        String[] whereArgs=new String[]{String.valueOf(name)};
        db.delete("users",where,whereArgs);
        return  1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
