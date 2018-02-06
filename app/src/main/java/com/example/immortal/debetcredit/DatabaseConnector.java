package com.example.immortal.debetcredit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnector {
    private static final String DATABASE_NAME = "FinanceContacts";
    private static final String TABLE_DEBET_NAME = "Debet";
    private static final String TABLE_CREDIT_NAME = "Credit";
    private SQLiteDatabase database;
    private DatabaseOpenHelper databaseOpenHelper;

    public DatabaseConnector(Context context) {
        databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    }

    public void open() throws SQLException {
        database = databaseOpenHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null)
            database.close();
    }


    //методи для роботи з доходами
    public void insertDebet(String title, double summ) {
        ContentValues newContact = new ContentValues();
        newContact.put("title", title);
        newContact.put("summ", summ);

        open();
        database.insert(TABLE_DEBET_NAME, null, newContact);
        close();
    }

    public void updateDebet(long id, String title, double summ) {
        ContentValues editContact = new ContentValues();
        editContact.put("title", title);
        editContact.put("summ", summ);

        open();
        database.update(TABLE_DEBET_NAME, editContact, "_id=" + id, null);
        close();
    }

    public Cursor getAllDebets() {
        database = databaseOpenHelper.getWritableDatabase();
        return database.rawQuery("select * from "+TABLE_DEBET_NAME , null);
    }

    public void deleteDebet(long id) {
        open();
        database.delete(TABLE_DEBET_NAME, "_id=" + id, null);
        close();
    }
    //методи для роботи з доходами


    //методи для роботи з розходами
    public void insertCredit(String title, double summ) {
        ContentValues newContact = new ContentValues();
        newContact.put("title", title);
        newContact.put("summ", summ);

        open();
        database.insert(TABLE_CREDIT_NAME, null, newContact);
        close();
    }

    public void updateCredit(long id, String title, double summ) {
        ContentValues editContact = new ContentValues();
        editContact.put("title", title);
        editContact.put("summ", summ);

        open();
        database.update(TABLE_CREDIT_NAME, editContact, "_id=" + id, null);
        close();
    }

    public Cursor getAllCredits() {
        database = databaseOpenHelper.getWritableDatabase();
        return database.rawQuery("select * from "+TABLE_CREDIT_NAME , null);
    }

    public void deleteCredit(long id) {
        open();
        database.delete(TABLE_CREDIT_NAME, "_id=" + id, null);
        close();
    }
    //методи для роботи з розходами


    //методи для підрахунку сум
    public Double getSummDebets() {
        Cursor cursor = database.rawQuery("SELECT SUM(summ) as summa FROM " + TABLE_DEBET_NAME, null);

        if( cursor != null && cursor.moveToFirst() ){
            return cursor.getDouble(cursor.getColumnIndexOrThrow("summa"));
        }
        return 0.0;
    }

    public Double getSummCredits() {
        Cursor cursor = database.rawQuery("SELECT SUM(summ) as summa FROM " + TABLE_CREDIT_NAME, null);

        if( cursor != null && cursor.moveToFirst() ){
            return cursor.getDouble(cursor.getColumnIndexOrThrow("summa"));
        }
        return 0.0;
    }

    public Double getSummMoney() {
        return getSummDebets() - getSummCredits();
    }
    //методи для підрахунку сум


    private class DatabaseOpenHelper extends SQLiteOpenHelper{
        public DatabaseOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String create_debet_query = "CREATE TABLE " + TABLE_DEBET_NAME + " (_id integer primary key autoincrement, " +
                    "title TEXT, summ REAL);";
            String create_credit_query = "CREATE TABLE " + TABLE_CREDIT_NAME + " (_id integer primary key autoincrement, " +
                    "title TEXT, summ REAL);";
            db.execSQL(create_debet_query);
            db.execSQL(create_credit_query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}