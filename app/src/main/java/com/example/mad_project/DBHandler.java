package com.example.mad_project;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "bb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "users";
    private static final String NAME_COL = "name";
    private static final String USER_NAME = "uname";
    private static final String PASSWORD = "pswd";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + NAME_COL + " TEXT,"
                + USER_NAME + " TEXT,"
                + PASSWORD + " TEXT)";
        db.execSQL(query);
    }

    public void addNewUser(String name, String uname, String pswd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(USER_NAME, uname);
        values.put(PASSWORD, pswd);

        db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<UserModal> readdb() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorUser
                = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<UserModal> courseModalArrayList = new ArrayList<>();
        if (cursorUser.moveToFirst()) {
            do {
                courseModalArrayList.add(new UserModal(
                        cursorUser.getString(1),
                        cursorUser.getString(2),
                        cursorUser.getString(3)));
            } while (cursorUser.moveToNext());
        }
        System.out.println(courseModalArrayList);
        //cursorCourses.close();
        return courseModalArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
