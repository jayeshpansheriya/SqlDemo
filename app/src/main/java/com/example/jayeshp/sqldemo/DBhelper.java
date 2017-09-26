package com.example.jayeshp.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Contacts;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jayesh P on 23-Mar-17.
 */

public class DBhelper extends SQLiteOpenHelper {
    public static int DB_version=1;
    public static String DB_name="JP";
    public static String DB_id="id";
    public static String DB_user="name";
    public static String DB_phone="phone";
    public static String DB_email="email";
    public static String DB_address="address";
    public static String TB_name="ContectDetail";
    public DBhelper(Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String quary="create table "+TB_name+"("+DB_id+" integer primary key, "+DB_user+" text, "+DB_phone+" text, "+DB_email+" text, "+DB_address+" text)";
        db.execSQL(quary);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists"+TB_name);
        onCreate(db);
    }
    public void AddData(Contact contact){
        ContentValues values=new ContentValues();
        SQLiteDatabase database=this.getWritableDatabase();
        values.put(DB_user,contact.getName());
        values.put(DB_phone,contact.getPhone());
        values.put(DB_email,contact.getEmail());
        values.put(DB_address,contact.getAddress());
        database.insert(TB_name,null,values);
        database.close();
    }

    public List<Contact> ReadAlldata(){
        List<Contact>contactList=new ArrayList<>();
        SQLiteDatabase database=this.getReadableDatabase();
        String s="select * from "+TB_name;
        Cursor cursor=database.rawQuery(s,null);
        if (cursor.moveToFirst())
        {
            do {
                Contact contact=new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setAddress(cursor.getString(4));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }return contactList;
    }
    public int Update(Contact contact){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DB_user,contact.getName());
        values.put(DB_phone,contact.getPhone());
        values.put(DB_email,contact.getEmail());
        values.put(DB_address,contact.getAddress());

        int n=database.update(TB_name,values,DB_id+" =?",new String[]{String.valueOf(contact.getId())});
        return n;
    }

    public void delete(Contact contact)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(TB_name,DB_id+" =?",new String[]{String.valueOf(contact.getId())});
        database.close();
    }
}

