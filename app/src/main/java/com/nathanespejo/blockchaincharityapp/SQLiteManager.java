package com.nathanespejo.blockchaincharityapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class SQLiteManager extends SQLiteOpenHelper {
    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "CharityDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String USER_TABLE_NAME = "users";

    private static final String ID_FIELD = "UserID";
    private static final String FIRST_NAME_FIELD = "Fname";
    private static final String LAST_NAME_FIELD = "Lname";
    private static final String EMAIL_FIELD = "Email";
    private static final String PASSWORD_FIELD = "Password";

    private static String DATABASE_PATH = "";
    private final Context context;

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }

    public static synchronized SQLiteManager instanceOfDatabase(Context context) {
        if (sqLiteManager == null) {
            sqLiteManager = new SQLiteManager(context);
            try {
                sqLiteManager.createDatabase();
            } catch (IOException e) {
                throw new RuntimeException("Error creating database", e);
            }
        }
        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // No need to implement for existing databases
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // No need to implement for existing databases
    }

    private void createDatabase() throws IOException {
        boolean dbExists = checkDatabase();
        if (!dbExists) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        try {
            String path = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // Database does not exist yet.
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null;
    }

    private void copyDatabase() throws IOException {
        InputStream input = context.getAssets().open(DATABASE_NAME);
        String outputFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream output = new FileOutputStream(outputFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        input.close();
    }

    public void populateUserListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + USER_TABLE_NAME, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int userID = result.getInt(result.getColumnIndex(ID_FIELD));
                    String firstName = result.getString(result.getColumnIndex(FIRST_NAME_FIELD));
                    String lastName = result.getString(result.getColumnIndex(LAST_NAME_FIELD));
                    String email = result.getString(result.getColumnIndex(EMAIL_FIELD));
                    String password = result.getString(result.getColumnIndex(PASSWORD_FIELD));
                    User user = new User(userID, firstName, lastName, email, password);
                    User.userArrayList.add(user);
                }
            }
        }
    }
}
