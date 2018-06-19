package com.example.tanya.sqlitetask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SqliteDatabase extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "StudentDB";
    public static final String TABLE_NAME = "Student";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "LNAME";
    public static final String COL_4 = "BRANCH";

    public SqliteDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("Create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME STRING, LNAME STRING , BRANCH STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    public boolean insertData(String name, String lname, String branch)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("NAME",name);
        contentValues.put("LNAME",lname);
        contentValues.put("BRANCH",branch);

        long res = db.insert(TABLE_NAME,null,contentValues);

        if(res == -1)
            return false;
        else
            return true;
    }

    public ArrayList<ListItem> getData()
    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ListItem> list;
        Cursor data = db.rawQuery("Select * from "+ TABLE_NAME,null);

        list = new ArrayList<>();

        if (data.moveToFirst())
        {
            do {
                String i = data.getString(data.getColumnIndex(COL_1));
                String n = data.getString(data.getColumnIndex(COL_2));
                String l = data.getString(data.getColumnIndex(COL_3));
                String b = data.getString(data.getColumnIndex(COL_4));

                ListItem list1 = new ListItem(i,n,l,b);

                list.add(list1);

            }while (data.moveToNext());

        return list;
        }
        return null;
    }

    public boolean deleteData(String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.delete(TABLE_NAME,"id = ?", new String[] {id});

        if (res > 0)
            return true;
        else
            return false;

    }

    public boolean updateData(String name, String lname, String branch, String pos)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,lname);
        contentValues.put(COL_4,branch);

        Log.i(TAG, "updateData: "+ pos);
        db.update(TABLE_NAME,contentValues,"ID = ?", new String[] {pos});

        return true;
    }

}
