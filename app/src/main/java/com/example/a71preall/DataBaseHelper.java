package com.example.a71preall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String LOST_AND_FOUND = "LOST_AND_FOUND";
    public static final String COLUMN_NOTE_TITLE = "NOTE_TITLE";
    public static final String COLUMN_NOTE_DESC = "NOTE_DESC";
    public static final String COLUMN_NOTE_DATE = "NOTE_DATE";
    public static final String COLUMN_NOTE_LOCATION = "NOTE_LOCATION";
    public static final String COLUMN_NOTE_ISLOST = "NOTE_ISLOST";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "lostandfound.db", null, 1);
    }

    //first call on access of db, functionally a constructor(?)
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + LOST_AND_FOUND + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOTE_TITLE + " TEXT, " + COLUMN_NOTE_DESC + " TEXT, " + COLUMN_NOTE_DATE + " TEXT, " + COLUMN_NOTE_LOCATION + " TEXT, " + COLUMN_NOTE_ISLOST + " BOOL )";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addOne(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOTE_TITLE, item.getTitle());
        cv.put(COLUMN_NOTE_DESC, item.getDescription());
        cv.put(COLUMN_NOTE_DATE, item.getDate());
        cv.put(COLUMN_NOTE_LOCATION, item.getLocation());
        cv.put(COLUMN_NOTE_ISLOST, item.getIslost());

        long insert = db.insert(LOST_AND_FOUND, null , cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }


        public List<Item> getAllItems() {
        List<Item> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + LOST_AND_FOUND;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                int itemId = cursor.getInt(0);
                String itemTitle = cursor.getString(1);
                String itemDescription = cursor.getString(2);
                String itemDate = cursor.getString(3);
                String itemLocation = cursor.getString(4);
                boolean itemIsLost = cursor.getInt(5) == 1 ? true: false;

                Item newItem = new Item(itemId, itemTitle, itemDescription, itemDate, itemLocation, itemIsLost);
                returnList.add(newItem);


            } while (cursor.moveToNext());
        }
        else{
            //ERR
        }

        cursor.close();
        db.close();
        return returnList;

    }

        public boolean deleteOne(Item item){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + LOST_AND_FOUND + " WHERE " + COLUMN_ID + " = " + item.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
             return true;
        }
        else
        {
            return false;
        }


    }
}
