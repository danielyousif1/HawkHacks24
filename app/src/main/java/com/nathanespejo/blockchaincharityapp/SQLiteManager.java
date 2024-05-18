package com.nathanespejo.blockchaincharityapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "database";
    private static final int DATABASE_VERSION = 1;
    private static final String USER_TABLE_NAME = "User";

    private static final String ID_FIELD = "UserID";
    private static final String FIRST_NAME_FIELD = "Fname";
    private static final String LAST_NAME_FIELD = "Lname";
    private static final String PASSWORD_FIELD = "Email";
    private static final String EMAIL_FIELD = "Password";


    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context) {
        if (sqLiteManager == null) {
            sqLiteManager = new SQLiteManager(context);
        }

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void populateUserListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + USER_TABLE_NAME, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int userID = result.getInt(1);
                    String firstName = result.getString(2);
                    String lastName = result.getString(3);
                    String email = result.getString(4);
                    String password = result.getString(5);
                    User user = new User(userID, firstName, lastName, email, password);
                    User.userArrayList.add(user);
                }
            }
        }

    }
}
