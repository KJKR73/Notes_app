package com.KJS.basicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user_login";
    public static final String COL_1 = "id";
    public static final String COL_2 = "user_name";
    public static final String COL_3 = "password";
    public static final String COL_4 = "note";
    private Object ContentValues;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, user_name TEXT, password TEXT, note TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String user_name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user_name);
        contentValues.put(COL_3, password);
        long result = db.insert(TABLE_NAME, null , contentValues);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean save_text(String user_name, String text){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_4, text);
        db.update(TABLE_NAME, contentValues, "user_name = ?", new String[] {user_name});
        return true;
    }

    public Cursor text_query(String user_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select note from " + TABLE_NAME + " where user_name='" + user_name + "'", null);
        return res;
    }

    public Cursor name_query(String user_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select user_name from " + TABLE_NAME + " where user_name='" + user_name + "'", null);
        return res;
    }

    public Cursor login_data(String user_name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select user_name, password from " + TABLE_NAME + " where user_name='" + user_name + "' and password='" + password + "'", null );
        return res;
    }

}
